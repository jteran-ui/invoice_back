package ec.edu.uisrael.semana4.Repository;

import ec.edu.uisrael.semana4.Model.Detalle;
import ec.edu.uisrael.semana4.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    List<Detalle> findByFactura(Factura factura);
}
