package ec.edu.uisrael.semana4.Controller;

import ec.edu.uisrael.semana4.Model.CategoriaDTO;
import ec.edu.uisrael.semana4.Service.ICategoriaService;
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
@RequestMapping(value = "/api/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private  ICategoriaService categoryService;


    @GetMapping("/all")
    public ResponseEntity<List<CategoriaDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoryBiId(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCategory(@RequestBody @Valid final CategoriaDTO categoryDTO) {
        final Long createdId = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCategory(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CategoriaDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = categoryService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
