package susturismo.susturismo.service;

import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PastaService {

    @Autowired
    PastaRepository pastaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Pasta> findAll(){
        List<Pasta> list=pastaRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);

        });

        return list;
    }

    public Optional<Pasta> findById(UUID id){

        Optional<Pasta> pasta=pastaRepository.findById(id);

            Optional<Account>optional=accountRepository.findAccountByAuth(pasta.get().getCriadoPor());
            optional.ifPresent(pasta.get()::setAccount);

        return pasta;
    }
    public boolean delete(UUID id){

        Optional<Pasta> pasta=pastaRepository.findById(id);

        if(pasta.isEmpty()){
            throw new HttpElementNotFoundExeption("Pasta not found");
        }
      pastaRepository.deleteById(id);


        return pastaRepository.findById(id).isEmpty();
    }
    @Transient
    public Pasta insert(Pasta pasta){
      if(pasta.getName()==null || pasta.getName().isEmpty()){
          throw new HttpInsertFailedException("Not send Name ");
      }
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional=userRepository.findByUsername(userName);
        UUID id= userOptional.get().getId();
        pasta.setCriadoPor(id);
        pasta.setAlteradoPor(id);



        return pastaRepository.save(pasta);
    }

    @Transient
    public Pasta update(Pasta pasta){

        Optional<Pasta> optional=pastaRepository.findById(pasta.getId());
        if(optional.isEmpty()){
            throw new HttpElementNotFoundExeption("Pasta not exist");
        }
        if(pasta.getName()!=null && !pasta.getName().isEmpty()){
          optional.get().setName(pasta.getName());
        }
        if(pasta.getImage()!=null && !pasta.getImage().isEmpty()){
            optional.get().setImage(pasta.getImage());
        }
        if(pasta.getLink()!=null && !pasta.getLink().isEmpty()){
            optional.get().setLink(pasta.getLink());
        }
        optional.get().setOrder(pasta.getOrder());
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional=userRepository.findByUsername(userName);
        UUID id= userOptional.get().getId();
        optional.get().setAlteradoPor(id);



        return pastaRepository.save(optional.get());
    }


public boolean deleteAll(){
       pastaRepository.deleteAll();
       return true;
}

}
