package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.Categoria;
import ec.edu.uisrael.semana4.Model.Producto;
import ec.edu.uisrael.semana4.Model.ProductoDTO;
import ec.edu.uisrael.semana4.Repository.CategoriaRespository;
import ec.edu.uisrael.semana4.Repository.ProductoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRespository categoriaRespository;

    @Override
    public List<ProductoDTO> getAllProducts() {
        List<Producto> productoList =  productoRepository.findAll(Sort.by("nombre"));

        List<ProductoDTO> productoDTOList = productoList.stream()
                .map(producto ->  mapToProductoDto(producto, new ProductoDTO()))
                .toList();

        return productoDTOList;
    }

    @Override
    public Producto saveProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        mapToProducto(productoDTO, producto);//metodo por referencia
        return  productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(ProductoDTO productoDTO) {

        Producto producto = productoRepository.findById(productoDTO.getId())
                .orElseThrow(()-> new ObjectNotFoundException("Producto no encontrado", Producto.class));
        mapToProducto(productoDTO, producto);

        productoRepository.save(producto);
        return null;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO getProducto(Long id) {
        return productoRepository.findById(id)
                .map(producto -> mapToProductoDto(producto, new ProductoDTO()))
                .orElseThrow(()-> new ObjectNotFoundException("Producto no encontrado", Producto.class));
    }

    private ProductoDTO mapToProductoDto(Producto producto, ProductoDTO productoDTO){
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setCategoria(producto.getCategoria().getId());
        productoDTO.setNombreCategoria(producto.getCategoria().getNombre());
        productoDTO.setColor(producto.getColor());
        return productoDTO;
    }

    private Producto mapToProducto(ProductoDTO productoDTO, Producto producto){
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        Categoria categoria = categoriaRespository.findById(productoDTO.getCategoria())
                .orElseThrow(() -> new ObjectNotFoundException("No se encontro la categoria", Categoria.class));
        producto.setCategoria(categoria);
        producto.setColor(productoDTO.getColor());
        return producto;
    }
}
