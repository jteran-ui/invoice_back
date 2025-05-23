package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.ClienteDTO;
import ec.edu.uisrael.semana4.util.ReferencedWarning;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(Long id);
    Long createCliente(ClienteDTO clienteDTO);
    void updateCliente( Long id, ClienteDTO categoryDTO);
    void deleteCliente( Long id);
    ReferencedWarning getReferencedWarning(final Long id);
}
