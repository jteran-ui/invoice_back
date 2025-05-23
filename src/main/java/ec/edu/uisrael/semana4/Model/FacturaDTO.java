package ec.edu.uisrael.semana4.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private Long id;
    private Double total;
    private Long cliente;
    private String fechaRegistro;
    private List<DetalleDTO> detalles;
}
