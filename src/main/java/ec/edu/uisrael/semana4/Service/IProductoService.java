package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.Producto;
import ec.edu.uisrael.semana4.Model.ProductoDTO;

import java.util.List;

public interface IProductoService {
    List<ProductoDTO> getAllProducts();
    Producto saveProducto(ProductoDTO productoDTO);
    Producto updateProducto(ProductoDTO productoDTO);
    void deleteProducto(Long id);
    ProductoDTO getProducto(Long id);

}
