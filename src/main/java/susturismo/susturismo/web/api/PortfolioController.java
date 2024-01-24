package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Portfolio;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.PortfolioService;
import susturismo.susturismo.web.dto.FeedDTO;
import susturismo.susturismo.web.dto.PortfolioDTO;
import susturismo.susturismo.web.dto.converter.PortfolioDTOConverter;
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
public class PortfolioController implements PortfolioApi{
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    PortfolioDTOConverter portfolioDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<PortfolioDTO>> findAll(RequestDTOList<PortfolioDTO> request) {
        List<PortfolioDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<PortfolioDTO> response;
        List<Portfolio> list = portfolioService.findAll();

        dtoList = list.stream().map(portfolioDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Portfolio Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Portfolios", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<PortfolioDTO>> insert(RequestDTO<PortfolioDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PortfolioDTO> response;

        Portfolio value=portfolioService.insert(portfolioDTOConverter.convertToEntity(request.getRequest()));

        if(value==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        PortfolioDTO dto= portfolioDTOConverter.convertToDTO(portfolioService.findById(value.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<PortfolioDTO>> update(RequestDTO<PortfolioDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PortfolioDTO> response;
        Portfolio value=portfolioService.update(portfolioDTOConverter.convertToEntity(request.getRequest()));
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        PortfolioDTO dto= portfolioDTOConverter.convertToDTO(portfolioService.findById(value.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<PortfolioDTO> response;
        request.getRequest().forEach(value->{
            if(portfolioService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active Portfolio");
            }
        });
        response = responseDTOConverter.createResponseWithList(request, null, "Ative", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<PortfolioDTO> response;
        request.getRequest().forEach(value->{
            if(portfolioService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable Portfolio");
            }
        });

        response = responseDTOConverter.createResponseWithList(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> remove(RequestDTO<PortfolioDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PortfolioDTO> response;
        request.getRequest().getRoles().forEach(value->{
            if(!portfolioService.removeRole(request.getRequest().getId(),value.getId())){
                throw new HttpUpdateFailedException("Error to disable Portfolio");
            }
        });

        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<PortfolioDTO>> findByID(UUID id, RequestDTO<PortfolioDTO> request) {
        PortfolioDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PortfolioDTO> response;

        Optional<Portfolio> value = portfolioService.findById(id);

        if (value.isPresent()) {
            dto = portfolioDTOConverter.convertToDTO(value.get());

        }else{
            throw new HttpElementNotFoundExeption("Portfolio not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
