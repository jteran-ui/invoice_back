package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.CategoriaDTO;
import ec.edu.uisrael.semana4.util.ReferencedWarning;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaDTO> findAll();
    CategoriaDTO findById( Long id);
    Long createCategory(CategoriaDTO categoryDTO);
    void updateCategory( Long id, CategoriaDTO categoryDTO);
    void deleteCategory( Long id);
    ReferencedWarning getReferencedWarning(final Long id);
}
