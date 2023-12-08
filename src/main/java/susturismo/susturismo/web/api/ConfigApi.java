package susturismo.susturismo.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/config")
public interface ConfigApi {

    @GetMapping(path = "/category")
    ResponseEntity<Object> gerarCategory();
    @GetMapping(path = "/parceiro")
    ResponseEntity<Object> gerarParceiro();
    @GetMapping(path = "/formation")
    ResponseEntity<Object> gerarFormation();
}
