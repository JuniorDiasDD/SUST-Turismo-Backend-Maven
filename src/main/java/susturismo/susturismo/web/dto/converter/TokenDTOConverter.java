package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.web.dto.TokenDTO;
import org.springframework.stereotype.Component;

@Component
public class TokenDTOConverter {

    public TokenDTO convertToDTO(String utilizador, String token) {

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setLogin(utilizador);
        return tokenDTO;

    }


}
