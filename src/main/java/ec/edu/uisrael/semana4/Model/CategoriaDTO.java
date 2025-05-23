package ec.edu.uisrael.semana4.Model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoriaDTO {

    private Long id;

    @Size(max = 255)
    private String nombre;

}
