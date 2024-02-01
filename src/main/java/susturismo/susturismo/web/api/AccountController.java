package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.UserRepository;
import susturismo.susturismo.service.AccountService;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.converter.AccountDTOConverter;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@RestController
public class AccountController implements AccountApi {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDTOConverter accountDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<ResponseDTOList<AccountDTO>> findAll(RequestDTOList<AccountDTO> request) {
        List<AccountDTO> dtoList=new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<AccountDTO> response;
        List<Account> list = accountService.findAll();

        list.forEach(account->{

            Optional<User> userOptional=userRepository.findByUsername(account.getLogin());
            AtomicReference<String> perfil= new AtomicReference<>("");
            if(!userOptional.get().getAuthorities().isEmpty()){
                userOptional.get().getAuthorities().forEach(e->{
                    perfil.set(e.getAuthority());
                });

                perfil.set(perfil.get().replace("ROLE_", ""));

            }

            dtoList.add(accountDTOConverter.convertToDTOPerfil(account,perfil.get()));

        });


        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Accounts Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Accounts", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<ResponseDTO<AccountDTO>> update(RequestDTO<AccountDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<AccountDTO> response;
        Account value=accountService.update(accountDTOConverter.convertToEntity(request.getRequest()),request.getRequest().getRole());
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        AccountDTO dto= accountDTOConverter.convertToDTO(accountService.findById(value.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> active(RequestDTO<AccountDTO> request) {
        ResponseDTO<AccountDTO> response;
        HttpHeaders headers = new HttpHeaders();

        if(accountService.updateStatus(request.getRequest().getId(),"Active")){
            throw new HttpUpdateFailedException("Error to active Account");
        }
        response = responseDTOConverter.createResponse(request, null, "Active", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTO<AccountDTO> request) {
        ResponseDTO<AccountDTO> response;
        HttpHeaders headers = new HttpHeaders();

        if(accountService.updateStatus( request.getRequest().getId(),"Disable")){
            throw new HttpUpdateFailedException("Error to disable Account");
        }
        response = responseDTOConverter.createResponse(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

   @Override
    public ResponseEntity<Object> remove(RequestDTO<AccountDTO> request) {
       ResponseDTO<AccountDTO> response;
       HttpHeaders headers = new HttpHeaders();
        request.getRequest().getPortfolios().forEach(value->{
            if(!accountService.removePortfolio(request.getRequest().getId(),value.getId())){
                throw new HttpUpdateFailedException("Error to remove portfolio to account");
            }
        });

       response = responseDTOConverter.createResponse(request, null, "Remove sucess", true);


       return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<AccountDTO>> findByID(UUID id, RequestDTO<AccountDTO> request) {
        AccountDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<AccountDTO> response;

        Optional<Account> value = accountService.findById(id);



        if (value.isPresent()) {
            Optional<User> userOptional=userRepository.findByUsername(value.get().getLogin());
            AtomicReference<String> perfil= new AtomicReference<>("");
            if(!userOptional.get().getAuthorities().isEmpty()){
                userOptional.get().getAuthorities().forEach(e->{
                    perfil.set(e.getAuthority());
                });

                perfil.set(perfil.get().replace("ROLE_", ""));

            }

            dto = accountDTOConverter.convertToDTOPerfil(value.get(),perfil.get());

        }else{
            throw new HttpElementNotFoundExeption("Account not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
