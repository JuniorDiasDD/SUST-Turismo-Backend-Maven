package susturismo.susturismo.web.api;


import susturismo.susturismo.domain.Account;
import susturismo.susturismo.secutiry.TokenService;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.UserRepository;
import susturismo.susturismo.service.AccountService;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.AuthenticationDTO;
import susturismo.susturismo.web.dto.TokenDTO;
import susturismo.susturismo.web.dto.converter.AccountDTOConverter;
import susturismo.susturismo.web.dto.converter.TokenDTOConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class AuthenticationController implements AuthenticationApi {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    TokenDTOConverter tokenDTOConverter;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDTOConverter accountDTOConverter;

    @Override
    public ResponseEntity<Object> createAuthenticationToken(HttpServletRequest request, AuthenticationDTO authenticationRequest, HttpHeaders headers) {
        TokenDTO tokenDto=null;
        String username="";
        String password="";
        if(authenticationRequest.getGoogleId()!=null){
            Optional <Account> userGoogleId= accountService.checkGoogle(authenticationRequest.getGoogleId());
            if(userGoogleId.isEmpty()){
                throw new HttpElementNotFoundExeption("Google Id not found");
            }else{
              username=userGoogleId.get().getLogin();
              password="PShrgan21#P";
            }
        }else{
           username=authenticationRequest.getLogin();
           password=authenticationRequest.getPassword();
        }

        var usernamePassword=new UsernamePasswordAuthenticationToken(username,password);
        var auth= authenticationManager.authenticate(usernamePassword);
        if(!accountService.checkStatus(username)){
            throw new HttpElementNotFoundExeption("User is Disable");
        }

        UUID id= accountService.findByUserId(username);
        Optional<User> userOptional=userRepository.findByUsername(username);
        var token=tokenService.generateToken((User)auth.getPrincipal());
        AtomicReference<String> perfil= new AtomicReference<>("");

        userOptional.get().getAuthorities().forEach(e->{
            perfil.set(e.getAuthority());
        });

        if(!perfil.get().isEmpty()){
            perfil.set(perfil.get().replace("ROLE_", ""));
        }
        tokenDto=tokenDTOConverter.convertToDTO(username,token,id,perfil.get());

      return ResponseEntity.ok(tokenDto);
    }

    @Override
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> register(HttpServletRequest request, AccountDTO data, HttpHeaders headers) {

        if(this.userRepository.findByLogin(data.getLogin())!=null) {
         throw new HttpInsertFailedException("Username exist");
        }

        accountService.insert(accountDTOConverter.convertToEntity(data),data.getPassword(),data.getRole(),data.getGoogleId());
        return ResponseEntity.ok().build();
    }


}