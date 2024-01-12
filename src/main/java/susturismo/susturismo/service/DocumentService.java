package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.Document;
import susturismo.susturismo.domain.Equipa;
import susturismo.susturismo.domain.Galery;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.DocumentRepository;
import susturismo.susturismo.repository.EquipaRepository;
import susturismo.susturismo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    public List<Document> findAll(){
        List<Document> list=documentRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
        });
        return list;
    }

    public Optional<Document> findById(UUID id){
        return documentRepository.findById(id);
    }
    public Document insert(Document document){
if(document.getTitle().isEmpty()){
    throw new HttpInsertFailedException("Title is empty");
}if(document.getLink().isEmpty()){
            throw new HttpInsertFailedException("Link is empty");
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        document.setCriadoPor(id);
        document.setAlteradoPor(id);
        return documentRepository.save(document);
    }

    public boolean delete(UUID id){
        Optional<Document> document=documentRepository.findById(id);
        if(document.isEmpty()){
            throw  new HttpElementNotFoundExeption("ID not exist");
        }
        documentRepository.delete(document.get());
        return true;
    }


}
