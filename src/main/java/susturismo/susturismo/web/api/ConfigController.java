package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.service.ConfigService;


@RestController
public class ConfigController implements ConfigApi{

    @Autowired
    ConfigService configService;

    @Override
    public ResponseEntity<Object> gerarCategory() {
        configService.gerarCategory();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> gerarParceiro() {
        configService.gerarParceiro();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteParceiro() {

        configService.deleteParceiros();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> gerarFormation() {
        configService.gerarFormation();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> gerarEvents() {
        configService.gerarEvents();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> gerarUniversidade() {
        configService.gerarUniversidades();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteUniversidade() {

        configService.deleteUniversidade();
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Object> deletePais() {

        configService.deletePais();
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Object> deletePasta() {

        configService.deletePastas();
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Object> gerarPais() {
        configService.gerarPais();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> gerarNoticia() {
        configService.gerarNoticia();
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Object> gerarEquipa() {
        configService.gerarEquipa();
        return ResponseEntity.ok().build();
    }
}
