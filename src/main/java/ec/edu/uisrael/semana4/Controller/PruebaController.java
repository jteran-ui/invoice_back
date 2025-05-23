package ec.edu.uisrael.semana4.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PruebaController {

    @GetMapping("/mensaje")
    public ResponseEntity<String> getMensaje(){
        String respuesta = "Hola Mundo";

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
