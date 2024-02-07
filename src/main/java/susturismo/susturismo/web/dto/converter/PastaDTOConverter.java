package susturismo.susturismo.web.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Pasta;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.PastaDTO;

@Component
public class PastaDTOConverter {

    @Autowired
    AccountDTOConverter accountDTOConverter;

    public PastaDTO convertToDTO(Pasta pasta) {

        PastaDTO dto = new PastaDTO();
        dto.setId(pasta.getId());
        dto.setName(pasta.getName());
        dto.setImage(pasta.getImage());
        dto.setLink(pasta.getLink());
        dto.setOrder(pasta.getOrder());


        return dto;
    }

    public PastaDTO convertToDTOFull(Pasta pasta) {

        PastaDTO dto = new PastaDTO();
        dto.setId(pasta.getId());
        dto.setName(pasta.getName());
        dto.setImage(pasta.getImage());
        dto.setLink(pasta.getLink());
        AccountDTO accountDTO = accountDTOConverter.convertToDTO(pasta.getAccount());
        dto.setAccount(accountDTO);
        dto.setOrder(pasta.getOrder());

        return dto;
    }

    public Pasta convertToEntity(PastaDTO dto) {

        Pasta objt = new Pasta();
        objt.setId(dto.getId());
        objt.setName(dto.getName());
        objt.setImage(dto.getImage());
        objt.setLink(dto.getLink());
        objt.setOrder(dto.getOrder());

        return objt;
    }


}
