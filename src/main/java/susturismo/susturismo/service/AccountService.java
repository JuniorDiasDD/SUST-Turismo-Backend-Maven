package susturismo.susturismo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.PortfolioRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    UserRepository userRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    public UUID findByUserId(String login){
        Optional<Account>optional= accountRepository.findByLogin(login);
        return optional.get().getId();
    }
    @Transactional
    public Account insert(Account account, String password, UserRole role,String googleId){

        String passwordChange="";
        if(googleId!=null){

            passwordChange="PShrgan21#P";
        }else{
            passwordChange=password;

        }
        String passwordValue= new BCryptPasswordEncoder().encode(passwordChange);
        User newUser=new User(account.getLogin(),passwordValue,role);
        User savedUser=userRepository.save(newUser);
        account.setStatus("Active");
        account.setAuth(savedUser.getId());
        if(account.getPortfolios()!=null && !account.getPortfolios().isEmpty()){
            account.getPortfolios().forEach(v->{
                Optional<Portfolio> optionalPortfolio=portfolioRepository.findById(v.getId());
                if(optionalPortfolio.isEmpty()){
                    throw new HttpElementNotFoundExeption("Portfolio: "+v.getId()+" not exist");
                }
            });
        }

        return accountRepository.save(account);
    }

    public Account update(Account account){

        Optional<Account> optional=accountRepository.findById(account.getId());
        if(optional.isEmpty()){
            throw  new HttpElementNotFoundExeption("Account not exist");
        }

       if(account.getName()!=null && !account.getName().isEmpty()){
           optional.get().setName(account.getName());
       }
        if(account.getEmail()!=null && !account.getEmail().isEmpty()){
            optional.get().setEmail(account.getEmail());
        }
        if(account.getTel()!=null && !account.getTel().isEmpty()){
            optional.get().setTel(account.getTel());
        }
        if(account.getPortfolios()!=null && !account.getPortfolios().isEmpty()){
            account.getPortfolios().forEach(v->{
                Optional<Portfolio> optionalPortfolio=portfolioRepository.findById(v.getId());
                if(optionalPortfolio.isEmpty()){
                    throw new HttpElementNotFoundExeption("Portfolio: "+v.getId()+" not exist");
                }
                if(!optional.get().getPortfolios().contains(optionalPortfolio.get())){
                    optional.get().getPortfolios().add(optionalPortfolio.get());
                }
            });
        }


        return accountRepository.save(optional.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Account> value = accountRepository.findById(uuid);

        if(value.isPresent()){
            value.get().setStatus(status);
            accountRepository.save(value.get());

            return !accountRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Account id:" +uuid);
        }


    }

    public boolean checkStatus(String login){
        Optional<Account> optional=accountRepository.findByLogin(login);

        if(optional.isEmpty()){
            throw new HttpElementNotFoundExeption("User not exist");
        }
        return Objects.equals(optional.get().getStatus(), "Active");

    }
    public Optional<Account> checkGoogle(String googleId){
        return accountRepository.findByGoogleId(googleId);

    }
    public Optional<Account> findById(UUID id){
        return accountRepository.findById(id);
    }
    public boolean removePortfolio(UUID uuid,UUID portfolioId){

        Optional<Account> optionalAccount=accountRepository.findById(uuid);
        if(optionalAccount.isPresent()){

            Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(portfolioId);
            if(optionalPortfolio.isPresent()){

                Set<Portfolio> portfolioSet=new HashSet<>();
                optionalAccount.get().getPortfolios().forEach(value->{
                    if(value.getId()!=portfolioId){
                        portfolioSet.add(value);
                    }
                });
                optionalAccount.get().setPortfolios(portfolioSet);
                accountRepository.save(optionalAccount.get());
            }else{
                throw new HttpElementNotFoundExeption("Not exist this Portfolio id:" +portfolioId);
            }
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Account id:" +uuid);
        }
        return true;
    }
}
