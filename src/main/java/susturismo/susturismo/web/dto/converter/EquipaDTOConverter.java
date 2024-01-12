package susturismo.susturismo.web.dto.converter;

import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Equipa;
import susturismo.susturismo.web.dto.EquipaDTO;

@Component
public class EquipaDTOConverter {


    public EquipaDTO convertToDTO(Equipa equipa) {

        EquipaDTO dto = new EquipaDTO();

        dto.setId(equipa.getId());
        dto.setEmail(equipa.getEmail());
        dto.setCv(equipa.getCv());
        dto.setFacebook(equipa.getFacebook());
        dto.setName(equipa.getName());
        dto.setInstagram(equipa.getInstagram());
        dto.setOffice(equipa.getOffice());
        dto.setTwitter(equipa.getTwitter());
        dto.setWhere(equipa.getWhere());


        return dto;

    }

    public Equipa convertToEntity(EquipaDTO dto) {

        Equipa objt = new Equipa();
        objt.setId(dto.getId());
        objt.setEmail(dto.getEmail());
        objt.setCv(dto.getCv());
        objt.setFacebook(dto.getFacebook());
        objt.setName(dto.getName());
        objt.setInstagram(dto.getInstagram());
        objt.setOffice(dto.getOffice());
        objt.setTwitter(dto.getTwitter());
        objt.setWhere(dto.getWhere());

        return objt;

    }


}
