package ec.edu.uisrael.semana4.Controller;

import ec.edu.uisrael.semana4.Model.ClienteDTO;
import ec.edu.uisrael.semana4.Service.IClienteService;
import ec.edu.uisrael.semana4.Service.IClienteService;
import ec.edu.uisrael.semana4.util.ReferencedException;
import ec.edu.uisrael.semana4.util.ReferencedWarning;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private IClienteService clienteService;


    @GetMapping("/all")
    public ResponseEntity<List<ClienteDTO>> getAllCategories() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCategoryBiId(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCategory(@RequestBody @Valid final ClienteDTO clienteDTO) {
        final Long createdId = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCliente(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = clienteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
