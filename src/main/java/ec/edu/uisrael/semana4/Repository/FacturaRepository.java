package ec.edu.uisrael.semana4.Repository;

import ec.edu.uisrael.semana4.Model.Categoria;
import ec.edu.uisrael.semana4.Model.Cliente;
import ec.edu.uisrael.semana4.Model.Factura;
import ec.edu.uisrael.semana4.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Cliente findFirstByCliente(Cliente cliente);
}
