package susturismo.susturismo.service;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Portfolio;
import susturismo.susturismo.domain.Role;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.repository.PortfolioRepository;
import susturismo.susturismo.repository.RoleRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<Portfolio> findAll(){
        return portfolioRepository.findAll();
    }

    public Portfolio insert(Portfolio portfolio){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        portfolio.setCriadoPor(id);
        portfolio.setAlteradoPor(id);
        portfolio.setStatus("Active");
        if(portfolio.getRoles()!=null && !portfolio.getRoles().isEmpty()) {
            portfolio.getRoles().forEach(v -> {
                Optional<Role> optionalRole = roleRepository.findById(v.getId());
                if (optionalRole.isEmpty()) {
                    throw new HttpElementNotFoundExeption("Role: " + v.getId() + " not exist");
                }
            });
        }

        return portfolioRepository.save(portfolio);
    }

    public Portfolio update(Portfolio portfolio){

        Optional<Portfolio> optionalPortfolio=portfolioRepository.findById(portfolio.getId());
        if(optionalPortfolio.isEmpty()){
            throw  new HttpElementNotFoundExeption("Portfolio not exist");
        }

       if(portfolio.getName()!=null && !portfolio.getName().isEmpty()){
           optionalPortfolio.get().setName(portfolio.getName());
       }
        if(portfolio.getDescription()!=null && !portfolio.getDescription().isEmpty()){
            optionalPortfolio.get().setDescription(portfolio.getDescription());
        }
        if(portfolio.getRoles()!=null && !portfolio.getRoles().isEmpty()){
            portfolio.getRoles().forEach(v->{
                Optional<Role> optionalRole=roleRepository.findById(v.getId());
                if(optionalRole.isEmpty()){
                    throw new HttpElementNotFoundExeption("Role: "+v.getId()+" not exist");
                }
                if(!optionalPortfolio.get().getRoles().contains(optionalRole.get())){
                    optionalPortfolio.get().getRoles().add(optionalRole.get());
                }
            });
        }
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalPortfolio.get().setAlteradoPor(id);

        return portfolioRepository.save(optionalPortfolio.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Portfolio> value = portfolioRepository.findById(uuid);

        if(value.isPresent()){
            value.get().setStatus(status);
            portfolioRepository.save(value.get());

            return !portfolioRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Portfolio id:" +uuid);
        }


    }

    public boolean removeRole(UUID uuid,UUID role){

        Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(uuid);

        if(optionalPortfolio.isPresent()){

            Optional<Role> optionalRole=roleRepository.findById(role);
            if(optionalRole.isPresent()){

                Set<Role> roleSet=new HashSet<>();
                optionalPortfolio.get().getRoles().forEach(value->{
                    if(value.getId()!=role){
                        roleSet.add(value);
                    }
                });
                optionalPortfolio.get().setRoles(roleSet);
                 portfolioRepository.save(optionalPortfolio.get());
            }else{
                throw new HttpElementNotFoundExeption("Not exist this Role id:" +role);
            }

        }else{
            throw new HttpElementNotFoundExeption("Not exist this Portfolio id:" +uuid);
        }
        return true;
    }

    public Optional<Portfolio> findById(UUID id){
        return portfolioRepository.findById(id);
    }
}
