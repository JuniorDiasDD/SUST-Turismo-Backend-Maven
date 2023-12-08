package susturismo.susturismo.service;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.CategoryRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category>findByName(String name){
        return categoryRepository.findByName(name);
    }

    public Category insert(Category category){
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new HttpInsertFailedException("This category already exists");
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        category.setCriadoPor(id);
        category.setAlteradoPor(id);
    category.setStatus("Active");
        UUID uuid = UUID.randomUUID();
        category.setId(uuid);
        return categoryRepository.save(category);
    }

    public Category update(Category category){
        Optional<Category> optionalCategory= categoryRepository.findById(category.getId());
        if(optionalCategory.isEmpty()){
            throw new HttpUpdateFailedException("Not exist this category");
        }

        if(category.getName()!=null){
            optionalCategory.get().setName(category.getName());
        }
        if(category.getDescription()!=null){
            optionalCategory.get().setDescription(category.getDescription());
        }

        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalCategory.get().setAlteradoPor(id);

        return categoryRepository.save(optionalCategory.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Category> category = categoryRepository.findById(uuid);

        if(category.isPresent()){
            category.get().setStatus(status);
            categoryRepository.save(category.get());

            return !categoryRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this category");
        }


    }

}
