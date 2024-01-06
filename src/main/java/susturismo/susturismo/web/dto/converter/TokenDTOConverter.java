package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.web.dto.TokenDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenDTOConverter {

    public TokenDTO convertToDTO(String utilizador, String token, UUID id,String perfil) {

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setLogin(utilizador);
        tokenDTO.setId(id);
        tokenDTO.setPerfil(perfil);
        return tokenDTO;

    }


}
