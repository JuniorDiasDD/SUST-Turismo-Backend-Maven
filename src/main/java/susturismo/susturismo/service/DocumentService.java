package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.Document;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.DocumentRepository;
import susturismo.susturismo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Document> findAll() {
        List<Document> list = documentRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));
        list.forEach(v -> {
            Optional<Account> optional = accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
        });
        return list;
    }

    public Optional<Document> findById(UUID id) {
        return documentRepository.findById(id);
    }

    public Document insert(Document document) {
        if (document.getTitle().isEmpty()) {
            throw new HttpInsertFailedException("Title is empty");
        }
        if (document.getLink().isEmpty()) {
            throw new HttpInsertFailedException("Link is empty");
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id = userRepository.findByUsername(userName).get().getId();
        document.setCriadoPor(id);
        document.setAlteradoPor(id);
        return documentRepository.save(document);
    }
    public Document edit(Document document) {

        if(document.getId()!=null){
            throw new HttpElementNotFoundExeption("Not send id document");
        }
        Optional<Document> optionalDocument=documentRepository.findById(document.getId());

        if(optionalDocument.isEmpty()){
            throw new HttpElementNotFoundExeption("ID document not exist");
        }
        Document documentUpdate=optionalDocument.get();

        if (document.getTitle()!=null && !document.getTitle().isEmpty()) {
           documentUpdate.setTitle(document.getTitle());
        }
        if (document.getLink()!=null && !document.getLink().isEmpty()) {
           documentUpdate.setLink(document.getLink());
        }
        if (document.getDescription()!=null && !document.getDescription().isEmpty()) {
            documentUpdate.setDescription(document.getDescription());
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id = userRepository.findByUsername(userName).get().getId();

        document.setAlteradoPor(id);
        return documentRepository.save(documentUpdate);
    }

    public boolean delete(UUID id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty()) {
            throw new HttpElementNotFoundExeption("ID not exist");
        }
        documentRepository.delete(document.get());
        return true;
    }


}
