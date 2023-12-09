package susturismo.susturismo.web.dto.converter;

import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.File;
import susturismo.susturismo.web.dto.FileDTO;

@Component
public class FileDTOConverter {

    public FileDTO convertToDTO(File file) {

        FileDTO dto = new FileDTO();

        dto.setId(file.getId());
        dto.setPath(file.getPath());
        dto.setMimeType(file.getMimeType());
        dto.setNome(file.getNome());
        dto.setConteudo(file.getConteudo());
        return dto;

    }

    public File convertToEntity(FileDTO dto) {

        File objt = new File();
        objt.setId(dto.getId());
        objt.setPath(dto.getPath());
        objt.setMimeType(dto.getMimeType());
        objt.setNome(dto.getNome());
        objt.setConteudo(dto.getConteudo());
        return objt;

    }


}
