package susturismo.susturismo.service;


import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.File;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;

import java.util.Base64;
import java.util.UUID;

import static susturismo.susturismo.process.File.saveDocInFolder;


@Service
public class FileService {


    public File insert(File file){
        if (null == file.getNome() || file.getNome().isEmpty()) {
            throw new HttpInsertFailedException("Nome not send...");
        }
        if (!file.getNome().contains(".")) {
            throw new HttpElementNotFoundExeption("Document name has no extension");
        }
        if (null == file.getMimeType() || file.getMimeType().isEmpty()) {
            throw new HttpInsertFailedException("Mime Type not send...");
        }
        if (null == file.getConteudo() || file.getConteudo().isEmpty()) {
            throw new HttpInsertFailedException("Conteudo not send...");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(file.getConteudo());
        String[] extensão = file.getNome().split("\\.");
        String nomeDocumentoUpdate = gerarUUID() + "." + extensão[1];
        String saveFile = saveDocInFolder(decodedBytes, nomeDocumentoUpdate);
        if (saveFile != null) {
            file.setPath(saveFile);
        } else {
            throw new HttpInsertFailedException("Unable to save document");
        }

        return file;
    }
    private UUID gerarUUID() {
       return UUID.randomUUID();
    }
}
