package susturismo.susturismo.web.dto.converter;

import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Pais;
import susturismo.susturismo.web.dto.PaisDTO;

@Component
public class PaisDTOConverter {


    public PaisDTO convertToDTO(Pais pais) {

        PaisDTO dto = new PaisDTO();
        dto.setId(pais.getId());
        dto.setName(pais.getName());
        dto.setDescription(pais.getDescription());


        return dto;
    }




}
