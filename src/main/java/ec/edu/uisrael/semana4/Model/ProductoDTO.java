package ec.edu.uisrael.semana4.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Float precio;
    private Long categoria;
    private String nombreCategoria;
    private String color;
}
