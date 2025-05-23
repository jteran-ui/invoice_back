package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.Categoria;
import ec.edu.uisrael.semana4.Model.CategoriaDTO;
import ec.edu.uisrael.semana4.Model.Producto;
import ec.edu.uisrael.semana4.Repository.CategoriaRespository;
import ec.edu.uisrael.semana4.Repository.ProductoRepository;
import ec.edu.uisrael.semana4.util.NotFoundException;
import ec.edu.uisrael.semana4.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRespository categoryRepository;
    private final ProductoRepository productRepository;

    public CategoriaService(CategoriaRespository categoryRepository,
                           ProductoRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoriaDTO> findAll() {
        List<Categoria> categories = categoryRepository.findAll(Sort.by("id"));
        return categories.stream()
                .map(category -> mapToDTO(category, new CategoriaDTO()))
                .toList();
    }
    @Override
    public CategoriaDTO findById(final Long id) {
        return categoryRepository.findById(id)
                .map(category -> mapToDTO(category, new CategoriaDTO()))
                .orElseThrow(NotFoundException::new);
    }
    @Override
    public Long createCategory(final CategoriaDTO categoryDTO) {
        final Categoria category = new Categoria();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getId();
    }
    @Override
    public void updateCategory(final Long id, final CategoriaDTO categoryDTO) {
        final Categoria category = categoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }
    @Override
    public void deleteCategory(final Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoriaDTO mapToDTO(final Categoria category, final CategoriaDTO categoryDTO) {
        categoryDTO.setId(category.getId());
        categoryDTO.setNombre(category.getNombre());
        return categoryDTO;
    }

    private Categoria mapToEntity(final CategoriaDTO categoryDTO, final Categoria category) {
        category.setNombre(categoryDTO.getNombre());
        return category;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
         ReferencedWarning referencedWarning = new ReferencedWarning();
         Categoria category = categoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
         Producto categoryProduct = productRepository.findFirstByCategoria(category);
        if (categoryProduct != null) {
            referencedWarning.setKey("category.product.category.referenced");
            referencedWarning.addParam(categoryProduct.getId());
            return referencedWarning;
        }
        return null;
    }

}
