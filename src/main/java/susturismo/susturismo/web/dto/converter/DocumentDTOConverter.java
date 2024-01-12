package susturismo.susturismo.web.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Document;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.DocumentDTO;

@Component
public class DocumentDTOConverter {
    @Autowired
    AccountDTOConverter accountDTOConverter;

    public DocumentDTO convertToDTO(Document document) {

        DocumentDTO dto = new DocumentDTO();

        dto.setId(document.getId());
        dto.setDescription(document.getDescription());
        dto.setLink(document.getLink());
        dto.setTitle(document.getTitle());
        dto.setCriadoEm(document.getCriadoEm());
        if(document.getAccount()!=null){
            AccountDTO accountDTO=accountDTOConverter.convertToDTO(document.getAccount());
            dto.setAccount(accountDTO);
        }


        return dto;

    }

    public Document convertToEntity(DocumentDTO dto) {

        Document objt = new Document();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setLink(dto.getLink());
        objt.setTitle(dto.getTitle());

        return objt;

    }


}
