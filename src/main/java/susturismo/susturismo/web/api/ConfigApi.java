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
    @DeleteMapping(path = "/universidade")
    ResponseEntity<Object> deleteUniversidade();
    @GetMapping(path = "/pais")
    ResponseEntity<Object> gerarPais();
    @DeleteMapping(path = "/pais")
    ResponseEntity<Object> deletePais();
    @DeleteMapping(path = "/pasta")
    ResponseEntity<Object> deletePasta();
    @GetMapping(path = "/noticia")
    ResponseEntity<Object> gerarNoticia();
    @GetMapping(path = "/equipa")
    ResponseEntity<Object> gerarEquipa();
}
