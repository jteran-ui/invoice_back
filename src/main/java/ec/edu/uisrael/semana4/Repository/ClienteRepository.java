package ec.edu.uisrael.semana4.Repository;


import ec.edu.uisrael.semana4.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
