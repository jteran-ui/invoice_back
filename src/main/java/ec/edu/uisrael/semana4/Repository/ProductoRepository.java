package ec.edu.uisrael.semana4.Repository;

import ec.edu.uisrael.semana4.Model.Categoria;
import ec.edu.uisrael.semana4.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {

    Producto findFirstByCategoria(Categoria categoria);
}
