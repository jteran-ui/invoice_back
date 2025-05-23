package ec.edu.uisrael.semana4.Repository;

import ec.edu.uisrael.semana4.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRespository extends JpaRepository<Categoria, Long> {

}
