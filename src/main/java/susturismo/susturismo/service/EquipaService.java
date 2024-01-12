package susturismo.susturismo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Equipa;
import susturismo.susturismo.repository.EquipaRepository;
import susturismo.susturismo.repository.UserRepository;
import java.util.List;
import java.util.UUID;

@Service
public class EquipaService {

    @Autowired
    EquipaRepository equipaRepository;
    @Autowired
    UserRepository userRepository;

    public List<Equipa> findAll(){
        return equipaRepository.findAll();
    }


    public Equipa insert(Equipa equipa){

        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        equipa.setCriadoPor(id);
        equipa.setAlteradoPor(id);
        return equipaRepository.save(equipa);
    }


}
