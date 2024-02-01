package susturismo.susturismo.web.dto.converter;

import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Pais;
import susturismo.susturismo.domain.Universidade;
import susturismo.susturismo.web.dto.PaisDTO;
import susturismo.susturismo.web.dto.UniversidadeDTO;

@Component
public class UniversidadeDTOConverter {


    public UniversidadeDTO convertToDTO(Universidade universidade) {

        UniversidadeDTO dto = new UniversidadeDTO();
        dto.setId(universidade.getId());
        dto.setName(universidade.getName());
        dto.setDescription(universidade.getDescription());


        return dto;
    }




}
