package susturismo.susturismo.service;

import susturismo.susturismo.domain.Role;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.repository.RoleRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role insert(Role role){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        role.setCriadoPor(id);
        role.setAlteradoPor(id);
        role.setStatus("Active");

        return roleRepository.save(role);
    }

    public Role update(Role role){

        Optional<Role> optionalRole=roleRepository.findById(role.getId());
        if(optionalRole.isEmpty()){
            throw  new HttpElementNotFoundExeption("Role not exist");
        }

       if(role.getName()!=null && !role.getName().isEmpty()){
           optionalRole.get().setName(role.getName());
       }
        if(role.getDescription()!=null && !role.getDescription().isEmpty()){
            optionalRole.get().setDescription(role.getDescription());
        }

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalRole.get().setAlteradoPor(id);

        return roleRepository.save(optionalRole.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Role> value = roleRepository.findById(uuid);

        if(value.isPresent()){
            value.get().setStatus(status);
            roleRepository.save(value.get());

            return !roleRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this role id:" +uuid);
        }


    }

    public Optional<Role> findById(UUID id){
        return roleRepository.findById(id);
    }
}
