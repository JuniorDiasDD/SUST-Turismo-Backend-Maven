package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.*;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    GaleryService galeryService;
    public List<Noticia> findAll(){
        List<Noticia> list=noticiaRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });

        return list;
    }

    public List<Noticia> findAllLimit(){
        List<Noticia> list=noticiaRepository.findAllLimit("Active");
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });

        return list;
    }
    public List<Noticia> findAllByUser(){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        List<Noticia> list=noticiaRepository.findAllByUser(id);
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });

        return list;
    }
    public List<Noticia> findAllStatus(String status){
        List<Noticia> list= noticiaRepository.findAllNoticiaStatus(status);

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });

        return list;
    }
    public Noticia insert(Noticia noticia){

        if(noticia.getCategory()!=null && !noticia.getCategory().isEmpty()) {
            noticia.getCategory().forEach(v -> {
                Optional<Category> category = categoryRepository.findById(v.getId());
                if (category.isEmpty()) {
                    throw new HttpElementNotFoundExeption("Category: " + v.getId() + " not exist");
                }
            });
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional=userRepository.findByUsername(userName);
        UUID id= userOptional.get().getId();
        noticia.setCriadoPor(id);
        noticia.setAlteradoPor(id);

        userOptional.get().getAuthorities().forEach(e->{
            if(e.getAuthority().equals("ROLE_ADMIN")){
                noticia.setStatus("Active");
            }else{
                noticia.setStatus("Pendente");
            }
        });
        if(noticia.getImage()==null){
            noticia.setImage("https://www.susturismo.com/img/sobrenos1.png");
        }
        Noticia noticiaSaved= noticiaRepository.save(noticia);


        if(noticiaSaved!=null){
            if(!noticia.getGalery().isEmpty()){
                galeryService.insert(noticiaSaved.getId(),noticia.getGalery(),id);
            }
        }

        return noticiaSaved;
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
        List<Galery> galeryList=galeryService.findAllByReference(id);
        Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
        noticia.get().setGalery(galeryListString);

        Set<Noticia> list=new HashSet<>();
        if(noticia.get().getCategory()!=null && !noticia.get().getCategory().isEmpty()){
            noticia.get().getCategory().forEach(v->{
                List<Noticia> noticiaList=noticiaRepository.findAllSemelhante("Active",v.getId(),id);
                if(!noticiaList.isEmpty()){
                    list.addAll(noticiaList);
                }
            });
        }


        noticia.get().setSemelhantes(list);
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
