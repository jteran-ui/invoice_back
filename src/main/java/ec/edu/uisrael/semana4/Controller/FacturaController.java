package ec.edu.uisrael.semana4.Controller;

import ec.edu.uisrael.semana4.Model.FacturaDTO;
import ec.edu.uisrael.semana4.Service.IFacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    IFacturaService iFacturaService;

    @GetMapping("/all")
    public ResponseEntity<List<FacturaDTO>> getAllFacturas(){
        List<FacturaDTO> invoiceList = iFacturaService.getAllFacturas();
        return   ResponseEntity.ok(invoiceList);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable("invoiceId")Long invoiceId){
        FacturaDTO invoiceDTO = iFacturaService.getFacturaById(invoiceId);
        return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFactura(@Valid @RequestBody FacturaDTO facturaDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos",HttpStatus.BAD_REQUEST);
        iFacturaService.saveFactura(facturaDTO);
        return new ResponseEntity<>("Factura creada correctamente",HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateFactura(@Valid @RequestBody FacturaDTO facturaDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos",HttpStatus.BAD_REQUEST);
        iFacturaService.updateFactura(facturaDTO);
        return new ResponseEntity<>("Actualizado correctamente",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{invoiceId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("invoiceId")Long invoiceId){
        iFacturaService.deleteFactura(invoiceId);
        return ResponseEntity.noContent().build();
    }


}
