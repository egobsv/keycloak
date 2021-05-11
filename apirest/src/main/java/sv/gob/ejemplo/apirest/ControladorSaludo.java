package sv.gob.ejemplo.apirest;


import java.util.concurrent.atomic.AtomicLong;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ControladorSaludo {

    private static final String plantilla = "Hola, %s!";
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/saludar")
    public Saludo saludar(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
        return new Saludo(contador.incrementAndGet(), String.format(plantilla, nombre));
    }

}