package susturismo.susturismo.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/config")
public interface ConfigApi {

    @GetMapping(path = "/category")
    ResponseEntity<Object> gerarCategory();
    @GetMapping(path = "/parceiro")
    ResponseEntity<Object> gerarParceiro();
    @DeleteMapping(path = "/parceiro")
    ResponseEntity<Object> deleteParceiro();
    @GetMapping(path = "/formation")
    ResponseEntity<Object> gerarFormation();
    @GetMapping(path = "/events")
    ResponseEntity<Object> gerarEvents();
    @GetMapping(path = "/universidade")
    ResponseEntity<Object> gerarUniversidade();
    @GetMapping(path = "/pais")
    ResponseEntity<Object> gerarPais();
    @GetMapping(path = "/noticia")
    ResponseEntity<Object> gerarNoticia();
    @GetMapping(path = "/equipa")
    ResponseEntity<Object> gerarEquipa();
}
