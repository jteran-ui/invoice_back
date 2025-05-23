package ec.edu.uisrael.semana4.Controller;

import ec.edu.uisrael.semana4.Model.ProductoDTO;
import ec.edu.uisrael.semana4.Service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    IProductoService iProductoService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductoDTO>> getAllProducts(){
        List<ProductoDTO> productoDTOList = iProductoService.getAllProducts();
        return  ResponseEntity.ok(productoDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductoDTO productoDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos enviados", HttpStatus.BAD_REQUEST);
        iProductoService.saveProducto(productoDTO);
        return new ResponseEntity<>("Producto creado correctamente", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductoDTO productoDTO){

        iProductoService.updateProducto(productoDTO);
        return new ResponseEntity<>("Producto actualizado correctamente", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") Long id){
        iProductoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProduct(@PathVariable("id") Long id){
        ProductoDTO productoDTO = iProductoService.getProducto(id);
        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }
}
