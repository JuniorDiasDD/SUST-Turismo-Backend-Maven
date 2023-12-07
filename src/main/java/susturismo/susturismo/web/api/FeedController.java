package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.FeedService;
import susturismo.susturismo.web.dto.FeedDTO;
import susturismo.susturismo.web.dto.converter.FeedDTOConverter;
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
public class FeedController implements FeedApi{
    @Autowired
    FeedService feedService;
    @Autowired
    FeedDTOConverter feedDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<FeedDTO>> findAll(RequestDTOList<FeedDTO> request) {
        List<FeedDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<FeedDTO> response;
        List<Feed> list = feedService.findAll();

        dtoList = list.stream().map(feedDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Feeds Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Feeds", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<FeedDTO>> findAllActive(RequestDTOList<FeedDTO> request) {
        List<FeedDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<FeedDTO> response;
        List<Feed> list = feedService.findAllStatus("Active");

        dtoList = list.stream().map(feedDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Feeds Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Feeds", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<FeedDTO>> insert(RequestDTO<FeedDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FeedDTO> response;

        Feed feed=feedService.insert(feedDTOConverter.convertToEntity(request.getRequest()));

        if(feed==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<FeedDTO>> update(RequestDTO<FeedDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FeedDTO> response;
        Feed value=feedService.update(feedDTOConverter.convertToEntity(request.getRequest()));
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(feedService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active feed");
            }
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(feedService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable feed");
            }
        });

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<FeedDTO>> findByID(UUID id, RequestDTO<FeedDTO> request) {
        FeedDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FeedDTO> response;

        Optional<Feed> event = feedService.findById(id);

        if (event.isPresent()) {
            dto = feedDTOConverter.convertToDTO(event.get());

        }else{
            throw new HttpElementNotFoundExeption("Event not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
