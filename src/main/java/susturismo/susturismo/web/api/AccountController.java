package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Account;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AccountController implements AccountApi {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDTOConverter accountDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<AccountDTO>> findAll(RequestDTOList<AccountDTO> request) {
        List<AccountDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<AccountDTO> response;
        List<Account> list = accountService.findAll();

        dtoList = list.stream().map(accountDTOConverter::convertToDTO).collect(Collectors.toList());

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
        Account value=accountService.update(accountDTOConverter.convertToEntity(request.getRequest()));
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(accountService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active Account");
            }
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(accountService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable Account");
            }
        });

        return ResponseEntity.ok().build();
    }

   @Override
    public ResponseEntity<Object> remove(RequestDTO<AccountDTO> request) {

        request.getRequest().getPortfolios().forEach(value->{
            if(!accountService.removePortfolio(request.getRequest().getId(),value.getId())){
                throw new HttpUpdateFailedException("Error to remove portfolio to account");
            }
        });

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<AccountDTO>> findByID(UUID id, RequestDTO<AccountDTO> request) {
        AccountDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<AccountDTO> response;

        Optional<Account> value = accountService.findById(id);

        if (value.isPresent()) {
            dto = accountDTOConverter.convertToDTO(value.get());

        }else{
            throw new HttpElementNotFoundExeption("Account not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
