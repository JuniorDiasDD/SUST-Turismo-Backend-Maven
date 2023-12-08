package susturismo.susturismo.service;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.domain.Formation;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.CategoryRepository;
import susturismo.susturismo.repository.FormationRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormationService {

    @Autowired
    FormationRepository formationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Formation> findAll(){
        return formationRepository.findAll();
    }
    public List<Formation> findAllStatus(String status){
        return formationRepository.findAllFormationStatus(status);
    }
    public Formation insert(Formation valueFormation){
        if(formationRepository.findByTitle(valueFormation.getTitle()).isPresent()){
            throw new HttpInsertFailedException("This Feed already exists");
        }
        valueFormation.getCategory().forEach(v->{
            Optional<Category> category=categoryRepository.findById(v.getId());
            if(category.isEmpty()){
                throw new HttpElementNotFoundExeption("Category: "+v.getId()+" not exist");
            }
        });
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        valueFormation.setCriadoPor(id);
        valueFormation.setAlteradoPor(id);
        valueFormation.setStatus("Active");
        UUID uuid = UUID.randomUUID();
        valueFormation.setId(uuid);
        return formationRepository.save(valueFormation);
    }

    public Formation update(Formation formation){
        Optional<Formation> optionalFormation= formationRepository.findById(formation.getId());
        if(optionalFormation.isEmpty()){
            throw new HttpUpdateFailedException("Not exist this Formation");
        }
        if(formation.getCategory()!=null && !formation.getCategory().isEmpty()){
            formation.getCategory().forEach(v->{
                Optional<Category> category=categoryRepository.findById(v.getId());
                if(category.isEmpty()){
                    throw new HttpElementNotFoundExeption("Category: "+v.getId()+" not exist");
                }

                if(!optionalFormation.get().getCategory().contains(category.get())){
                    optionalFormation.get().getCategory().add(category.get());
                }

            });
        }
        if(formation.getTitle()!=null){
            optionalFormation.get().setTitle(formation.getTitle());
        }
        if(formation.getDescription()!=null){
            optionalFormation.get().setDescription(formation.getDescription());
        }
        if(formation.getObjective()!=null){
            optionalFormation.get().setObjective(formation.getObjective());
        }
        if(formation.getResponsible()!=null){
            optionalFormation.get().setResponsible(formation.getResponsible());
        }
        if(formation.getLink()!=null){
            optionalFormation.get().setLink(formation.getLink());
        }
        if(formation.getImage()!=null){
            optionalFormation.get().setImage(formation.getImage());
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalFormation.get().setAlteradoPor(id);

        return formationRepository.save(optionalFormation.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Formation> formation = formationRepository.findById(uuid);

        if(formation.isPresent()){
            formation.get().setStatus(status);
            formationRepository.save(formation.get());

            return !formationRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Formation");
        }


    }
    public Optional<Formation> findById(UUID id){
        return formationRepository.findById(id);
    }
}
