package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.*;
import ec.edu.uisrael.semana4.Repository.ClienteRepository;
import ec.edu.uisrael.semana4.Repository.DetalleRepository;
import ec.edu.uisrael.semana4.Repository.FacturaRepository;
import ec.edu.uisrael.semana4.Repository.ProductoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FacturaService implements IFacturaService{

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    DetalleRepository detalleRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<FacturaDTO> getAllFacturas() {
        List<Factura> facturaList = facturaRepository.findAll();
        List<FacturaDTO> facturaDTOList = facturaList.stream()
                .map(factura -> mapToFacturaDto(factura, new FacturaDTO()))
                .toList();
        return facturaDTOList;
    }

    @Transactional
    @Override
    public Factura saveFactura(FacturaDTO facturaDTO) {
        final Factura factura = new Factura();
        mapToEntity(facturaDTO, factura);
        return this.createFacturaWithDetails(factura, facturaDTO);
    }

    @Override
    public Factura updateFactura(FacturaDTO facturaDTO) {
        final Factura factura = facturaRepository.findById(facturaDTO.getId())
                .orElseThrow(()-> new ObjectNotFoundException("No se encontro la factura", Factura.class));
        mapToEntity(facturaDTO, factura);
        this.createFacturaWithDetails(factura, facturaDTO);
        return null;
    }

    @Override
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public FacturaDTO getFacturaById(Long id) {
        return facturaRepository.findById(id)
                .map(factura -> mapToFacturaDto(factura, new FacturaDTO()))
                .orElseThrow( ()-> new ObjectNotFoundException("Factura no encontrado", Factura.class));
    }



    private Factura createFacturaWithDetails(Factura factura, FacturaDTO facturaDTO) {
        Cliente cliente = clienteRepository.findById(facturaDTO.getCliente()).orElseThrow();
        //Factura factura = new Factura();
        factura.setCliente(cliente);
        factura = facturaRepository.save(factura);

        for (DetalleDTO detailDTO : facturaDTO.getDetalles()) {
            Producto producto = productoRepository.findById(detailDTO.getProducto()).orElseThrow();
            Detalle detalle = new Detalle();
            detalle.setFactura(factura);
            detalle.setProducto(producto);
            detalle.setCantidad(detailDTO.getCantidad());
            detalleRepository.save(detalle);
        }
        return factura;
    }

    private FacturaDTO mapToFacturaDto(Factura factura, FacturaDTO facturaDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaRegistro = factura.getFechaRegistro();
        List<Detalle> detalleList = detalleRepository.findByFactura(factura);
        List<DetalleDTO> detalleDTOList = detalleList.stream()
                        .map(detail -> mapToDetalleDto(detail, new DetalleDTO()))
                        .toList();
        facturaDTO.setId(factura.getId());
        facturaDTO.setTotal(factura.getTotal());
        facturaDTO.setDetalles(detalleDTOList);
        facturaDTO.setCliente(factura.getCliente().getId());
        facturaDTO.setFechaRegistro(fechaRegistro.format(formatter));
        return facturaDTO;
    }

    private DetalleDTO mapToDetalleDto(Detalle detalle, DetalleDTO detalleDTO){
        detalleDTO.setId(detalle.getId());
        detalleDTO.setCantidad(detalle.getCantidad());
        detalleDTO.setProducto(detalle.getProducto().getId());
        return detalleDTO;
    }

    private Factura mapToEntity(final FacturaDTO facturaDTO, final Factura factura) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        factura.setFechaRegistro(LocalDateTime.now());
        factura.setTotal(facturaDTO.getTotal());
        return factura;
    }
}
