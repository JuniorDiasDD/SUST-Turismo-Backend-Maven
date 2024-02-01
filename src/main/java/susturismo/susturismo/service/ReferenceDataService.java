package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Pais;
import susturismo.susturismo.domain.Universidade;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.PaisRepository;
import susturismo.susturismo.repository.UniversidadeRepository;
import susturismo.susturismo.web.dto.UniversidadeDTO;

import java.util.List;
import java.util.UUID;

@Service
public class ReferenceDataService {

    @Autowired
    UniversidadeRepository universidadeRepository;
    @Autowired
    PaisRepository paisRepository;


    public List<Pais> findAllPais(){
        return paisRepository.findAll();
    }
    public List<Universidade> findAllUniversidade(){
        return universidadeRepository.findAll();
    }


    public Pais insertPais(Pais objt){

        UUID uuid = UUID.randomUUID();
        objt.setId(uuid);
        return paisRepository.save(objt);
    }

    public Universidade insertUniversidade(Universidade objt){

        UUID uuid = UUID.randomUUID();
        objt.setId(uuid);
        return universidadeRepository.save(objt);
    }

}
