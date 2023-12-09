package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Noticia;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoticiaService {

    @Autowired
    NoticiaRepository noticiaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Noticia> findAll(){
        List<Noticia> list=noticiaRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
        });

        return list;
    }
    public List<Noticia> findAllLimit(){
        List<Noticia> list=noticiaRepository.findAllLimit("Active");
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
        });

        return list;
    }
    public List<Noticia> findAllStatus(String status){
        List<Noticia> list= noticiaRepository.findAllNoticiaStatus(status);

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
        });

        return list;
    }
    public Noticia insert(Noticia noticia){
       /* if(noticiaRepository.findByTitle(noticia.getTitle()).isPresent()){
            throw new HttpInsertFailedException("This Noticia already exists");
        }*/
        noticia.getCategory().forEach(v->{
            Optional<Category> category=categoryRepository.findById(v.getId());
            if(category.isEmpty()){
                throw new HttpElementNotFoundExeption("Category: "+v.getId()+" not exist");
            }
        });
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        noticia.setCriadoPor(id);
        noticia.setAlteradoPor(id);
        noticia.setStatus("Active");
        noticia.setImage("https://www.susturismo.com/img/sobrenos1.png");
        return noticiaRepository.save(noticia);
    }

    public Noticia update(Noticia noticia){
        Optional<Noticia> optionalFeed= noticiaRepository.findById(noticia.getId());
        if(optionalFeed.isEmpty()){
            throw new HttpUpdateFailedException("Not exist this Feed");
        }
        if(noticia.getCategory()!=null && !noticia.getCategory().isEmpty()){
            noticia.getCategory().forEach(v->{
                Optional<Category> category=categoryRepository.findById(v.getId());
                if(category.isEmpty()){
                    throw new HttpElementNotFoundExeption("Category: "+v.getId()+" not exist");
                }

                if(!optionalFeed.get().getCategory().contains(category.get())){
                    optionalFeed.get().getCategory().add(category.get());
                }

            });
        }
        if(noticia.getTitle()!=null){
            optionalFeed.get().setTitle(noticia.getTitle());
        }
        if(noticia.getDescription()!=null){
            optionalFeed.get().setDescription(noticia.getDescription());
        }
        if(noticia.getDate_publicacao()!=null){
            optionalFeed.get().setDate_publicacao(noticia.getDate_publicacao());
        }
        if(noticia.getFonte()!=null){
            optionalFeed.get().setFonte(noticia.getFonte());
        }
        if(noticia.getTags()!=null){
            optionalFeed.get().setTags(noticia.getTags());
        }
        if(noticia.getImage()!=null){
            optionalFeed.get().setImage(noticia.getImage());
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalFeed.get().setAlteradoPor(id);

        return noticiaRepository.save(optionalFeed.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Noticia> noticia = noticiaRepository.findById(uuid);

        if(noticia.isPresent()){
            noticia.get().setStatus(status);
            noticiaRepository.save(noticia.get());

            return !noticiaRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Noticia");
        }


    }

    public Optional<Noticia> findById(UUID id){
        Optional<Noticia> noticia= noticiaRepository.findById(id);

        Optional<Account>optional=accountRepository.findAccountByAuth(noticia.get().getCriadoPor());
        optional.ifPresent(noticia.get()::setAccount);

        return noticia;
    }

    public boolean delete(UUID id){
        Optional<Noticia> noticia=noticiaRepository.findById(id);
        if(noticia.isPresent()){
            noticiaRepository.delete(noticia.get());
            return true;
        }
        return false;
    }

}
