package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Parceiro;
import susturismo.susturismo.web.dto.ParceiroDTO;
import org.springframework.stereotype.Component;

@Component
public class ParceiroDTOConverter {



    public ParceiroDTO convertToDTO(Parceiro parceiro) {

        ParceiroDTO dto = new ParceiroDTO();

        dto.setId(parceiro.getId());
        dto.setDescription(parceiro.getDescription());
        dto.setStatus(parceiro.getStatus());
        dto.setName(parceiro.getName());
        dto.setImage(parceiro.getImage());

        return dto;
    }

    public Parceiro convertToEntity(ParceiroDTO dto) {

        Parceiro objt = new Parceiro();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setStatus(dto.getStatus());
        objt.setName(dto.getName());
        objt.setImage(dto.getImage());
        return objt;

    }


}
