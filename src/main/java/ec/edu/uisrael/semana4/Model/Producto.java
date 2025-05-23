package ec.edu.uisrael.semana4.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 250)
    private String nombre;

    private float precio;

    private String color;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
