package ec.edu.uisrael.semana4.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDTO {
    private Long id;
    private Long cantidad;
    private Long producto;
    private Long factura;
}
