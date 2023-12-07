package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.AuthenticationDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthenticationApi {

    @PostMapping("/login")
    ResponseEntity<Object> createAuthenticationToken(HttpServletRequest request, @RequestBody(required=false) @Valid AuthenticationDTO authenticationRequest, @RequestHeader HttpHeaders headers);
    @PostMapping("/logout")
    ResponseEntity<Object> logout(HttpServletRequest request);

    @PostMapping("/register")
    ResponseEntity<Object> register(HttpServletRequest request, @RequestBody(required=false) @Valid AccountDTO data, @RequestHeader HttpHeaders headers);


}
