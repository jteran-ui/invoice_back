package ec.edu.uisrael.semana4.Service;

import ec.edu.uisrael.semana4.Model.Factura;
import ec.edu.uisrael.semana4.Model.FacturaDTO;

import java.util.List;

public interface IFacturaService {
    List<FacturaDTO> getAllFacturas();
    Factura saveFactura(FacturaDTO facturaDTO);
    Factura updateFactura(FacturaDTO facturaDTO);
    void deleteFactura(Long id);
    FacturaDTO getFacturaById(Long id);
}
