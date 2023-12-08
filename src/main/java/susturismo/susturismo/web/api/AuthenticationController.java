package susturismo.susturismo.web.api;


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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
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
        var usernamePassword=new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),authenticationRequest.getPassword());
        var auth= authenticationManager.authenticate(usernamePassword);

        if(!accountService.checkStatus(authenticationRequest.getLogin())){
           throw new HttpElementNotFoundExeption("User is Disable");
        }

        var token=tokenService.generateToken((User)auth.getPrincipal());

        TokenDTO tokenDto=tokenDTOConverter.convertToDTO(authenticationRequest.getLogin(),token);

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
        String encryptedPassword= new BCryptPasswordEncoder().encode(data.getPassword());
        accountService.insert(accountDTOConverter.convertToEntity(data),encryptedPassword,data.getRole());
        return ResponseEntity.ok().build();
    }

}