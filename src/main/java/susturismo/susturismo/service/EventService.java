package susturismo.susturismo.service;

import jakarta.persistence.Transient;
import org.springframework.data.domain.Sort;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.CategoryRepository;
import susturismo.susturismo.repository.EventRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    GaleryService galeryService;

    public List<Event> findAll(){
        List<Event> list=eventRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            if(v.getPrice()==null){
                v.setPrice((float) 0);
            }

            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);

        });

        return list;
    }
    public List<Event> findAllUser(){

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        List<Event> list=eventRepository.findAllByUser(id);

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            if(v.getPrice()==null){
                v.setPrice((float) 0);
            }
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });
        return list;
    }
    public List<Event> findAllStatus(String status){
        List<Event> list=eventRepository.findAllEventsActive(status);

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            if(v.getPrice()==null){
                v.setPrice((float) 0);
            }
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });
        return list;
    }
    public List<Event> findAllLimit(){
        List<Event> list=eventRepository.findAllLimit("Active");

        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            if(v.getPrice()==null){
                v.setPrice((float) 0);
            }
            List<Galery> galeryList=galeryService.findAllByReference(v.getId());
            Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
            v.setGalery(galeryListString);
        });
        return list;
    }

    @Transient
    public Event insert(Event event){
        if(event.getCategory()!=null && !event.getCategory().isEmpty()) {
            event.getCategory().forEach(v -> {
                Optional<Category> category = categoryRepository.findById(v.getId());
                if (category.isEmpty()) {
                    throw new HttpElementNotFoundExeption("Category: " + v.getId() + " not exist");
                }
            });
        }
        if(event.getPrice()==null){
            event.setPrice((float) 0);
        }
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional=userRepository.findByUsername(userName);
        UUID id= userOptional.get().getId();
        event.setCriadoPor(id);
        event.setAlteradoPor(id);

        userOptional.get().getAuthorities().forEach(e->{
            if(e.getAuthority().equals("ROLE_ADMIN")){
                event.setStatus("Active");
            }else{
                event.setStatus("Pendente");
            }
        });
        if(event.getImage()==null){
            event.setImage("/img/sobrenos1.png");
        }


        Event eventSaved=eventRepository.save(event);

        if(eventSaved!=null){
            if(!event.getGalery().isEmpty()){
                galeryService.insert(eventSaved.getId(),event.getGalery(),id);
            }
        }

        return eventSaved;
    }

    public Event update(Event event){

        Optional<Event> optionalEvent=eventRepository.findById(event.getId());
        if(optionalEvent.isEmpty()){
            throw  new HttpElementNotFoundExeption("Event not exist");
        }

        if(event.getCategory()!=null && !event.getCategory().isEmpty()){
            event.getCategory().forEach(v->{
                Optional<Category> category=categoryRepository.findById(v.getId());
                if(category.isEmpty()){
                    throw new HttpElementNotFoundExeption("Category: "+v.getId()+" not exist");
                }
                if(!optionalEvent.get().getCategory().contains(category.get())){
                    optionalEvent.get().getCategory().add(category.get());
                }
            });
        }

       if(event.getTitle()!=null && !event.getTitle().isEmpty()){
           optionalEvent.get().setTitle(event.getTitle());
       }
        if(event.getDescription()!=null && !event.getDescription().isEmpty()){
            optionalEvent.get().setDescription(event.getDescription());
        }
        if(event.getOrganizer()!=null ){
            optionalEvent.get().setOrganizer(event.getOrganizer());
        }
        if(event.getImage()!=null && !event.getImage().isEmpty()){
            optionalEvent.get().setImage(event.getImage());
        }
        if(event.getDate_finish()!=null){
            optionalEvent.get().setDate_finish(event.getDate_finish());
        }
        if(event.getDate_init()!=null){
            optionalEvent.get().setDate_init(event.getDate_init());
        }
        if(event.getHour_finish()!=null){
            optionalEvent.get().setHour_finish(event.getHour_finish());
        }
        if(event.getHour_init()!=null){
            optionalEvent.get().setHour_init(event.getHour_init());
        }
        if(event.getTags()!=null){
            optionalEvent.get().setTags(event.getTags());
        }

        if(event.getLocal()!=null && !event.getLocal().isEmpty()){
            optionalEvent.get().setLocal(event.getLocal());
        }

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalEvent.get().setAlteradoPor(id);

        return eventRepository.save(optionalEvent.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Event> event = eventRepository.findById(uuid);

        if(event.isPresent()){
            event.get().setStatus(status);
            eventRepository.save(event.get());

            return !eventRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this event id:" +uuid);
        }
    }
    public Optional<Event> findById(UUID id){
        Optional<Event> event= eventRepository.findById(id);
        Optional<Account>optional=accountRepository.findAccountByAuth(event.get().getCriadoPor());
        optional.ifPresent(event.get()::setAccount);
        List<Galery> galeryList=galeryService.findAllByReference(id);
        Set<String> galeryListString = galeryList.stream().map(Galery::getImage).collect(Collectors.toSet());
        event.get().setGalery(galeryListString);

        if(event.get().getPrice()==null){
            event.get().setPrice((float) 0);
        }
        Set<Event> list=new HashSet<>();
        if(event.get().getCategory()!=null && !event.get().getCategory().isEmpty()){
            event.get().getCategory().forEach(v->{
                List<Event> eventList=eventRepository.findAllSemelhante("Active",v.getId(),id);
                if(!eventList.isEmpty()){
                    list.addAll(eventList);
                }
            });
        }

            event.get().setSemelhantes(list);


        return event;
    }
}
