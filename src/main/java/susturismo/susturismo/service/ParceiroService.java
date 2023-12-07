package susturismo.susturismo.service;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.Parceiro;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.ParceiroRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParceiroService {

    @Autowired
    ParceiroRepository parceiroRepository;
    @Autowired
    UserRepository userRepository;

    public List<Parceiro> findAll(){
        return parceiroRepository.findAll();
    }
    public List<Parceiro> findAllStatus(String status){
        return parceiroRepository.findAllParceirosActive(status);
    }
    public Parceiro insert(Parceiro parceiro){
        if(parceiroRepository.findByName(parceiro.getName()).isPresent()){
            throw new HttpInsertFailedException("This Parceiro already exists");
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        parceiro.setCriadoPor(id);
        parceiro.setAlteradoPor(id);
    parceiro.setStatus("Active");
        return parceiroRepository.save(parceiro);
    }

    public Parceiro update(Parceiro parceiro){
        Optional<Parceiro> optionalParceiro= parceiroRepository.findById(parceiro.getId());
        if(optionalParceiro.isEmpty()){
            throw new HttpUpdateFailedException("Not exist this Parceiro");
        }

        if(parceiro.getName()!=null){
            optionalParceiro.get().setName(parceiro.getName());
        }
        if(parceiro.getDescription()!=null){
            optionalParceiro.get().setDescription(parceiro.getDescription());
        }

        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalParceiro.get().setAlteradoPor(id);

        return parceiroRepository.save(optionalParceiro.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Parceiro> parceiro = parceiroRepository.findById(uuid);

        if(parceiro.isPresent()){
            parceiro.get().setStatus(status);
            parceiroRepository.save(parceiro.get());

            return !parceiroRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Parceiro");
        }


    }

}
