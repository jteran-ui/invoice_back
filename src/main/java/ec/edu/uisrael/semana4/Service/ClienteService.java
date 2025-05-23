package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.*;
import ec.edu.uisrael.semana4.Repository.ClienteRepository;
import ec.edu.uisrael.semana4.Repository.FacturaRepository;
import ec.edu.uisrael.semana4.Repository.ProductoRepository;
import ec.edu.uisrael.semana4.util.NotFoundException;
import ec.edu.uisrael.semana4.util.ReferencedWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoRepository productRepository;
    @Autowired
    FacturaRepository facturaRepository;


    @Override
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll(Sort.by("id"));
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }
    @Override
    public ClienteDTO findById(final Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .orElseThrow(NotFoundException::new);
    }
    @Override
    public Long createCliente(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return clienteRepository.save(cliente).getId();
    }
    @Override
    public void updateCliente(final Long id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
    }
    @Override
    public void deleteCliente(final Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        return clienteDTO;
    }

    private Cliente mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setNombre(clienteDTO.getNombre());
        return cliente;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        ReferencedWarning referencedWarning = new ReferencedWarning();
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        Cliente facturaCliente = facturaRepository.findFirstByCliente(cliente);
        if (facturaCliente != null) {
            referencedWarning.setKey("cliente.factura.cliente.referenced");
            referencedWarning.addParam(facturaCliente.getId());
            return referencedWarning;
        }
        return null;
    }

}
