package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Event;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.EventService;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.converter.EventDTOConverter;
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
public class EventController implements EventApi{
    @Autowired
    EventService eventService;
    @Autowired
    EventDTOConverter eventDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Override
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEvents(RequestDTOList<EventDTO> request) {
        List<EventDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EventDTO> response;
        List<Event> list = eventService.findAll();

        dtoList = list.stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Events Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Events", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllLimit(RequestDTOList<EventDTO> request) {
        List<EventDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EventDTO> response;
        List<Event> list = eventService.findAllLimit();

        dtoList = list.stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Events Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Events", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllByUser(RequestDTOList<EventDTO> request) {
        List<EventDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EventDTO> response;
        List<Event> list = eventService.findAllUser();

        dtoList = list.stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Events Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Events", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEventsApprove(RequestDTOList<EventDTO> request) {
        List<EventDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EventDTO> response;
        List<Event> list = eventService.findAllStatus("Pendente");

        dtoList = list.stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Events Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Events", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEventsActive(RequestDTOList<EventDTO> request) {
        List<EventDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EventDTO> response;
        List<Event> list = eventService.findAllStatus("Active");

        dtoList = list.stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Events Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Events", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<EventDTO>> insertEvent(RequestDTO<EventDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<EventDTO> response;

        Event event=eventService.insert(eventDTOConverter.convertToEntity(request.getRequest()));

        if(event==null) {
            throw new HttpInsertFailedException("Error to save");
        }
        EventDTO dto= eventDTOConverter.convertToDTO(eventService.findById(event.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<EventDTO>> updateEvent(RequestDTO<EventDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<EventDTO> response;

        Event event=eventService.update(eventDTOConverter.convertToEntity(request.getRequest()));

        if(event==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        EventDTO dto= eventDTOConverter.convertToDTO(eventService.findById(event.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> activeEvent(RequestDTO<EventDTO> request) {
        ResponseDTO<EventDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();

        if(eventService.updateStatus(value,"Active")){
            throw new HttpUpdateFailedException("Error to ative event");
        }
        response = responseDTOConverter.createResponse(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    public ResponseEntity<Object> approve(RequestDTO<EventDTO> request) {
        ResponseDTO<EventDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();
        if(eventService.updateStatus(value,"Active")){
            throw new HttpUpdateFailedException("Error to approve event");
        }
        response = responseDTOConverter.createResponse(request, null, "Approve", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disableEvent(RequestDTO<EventDTO> request) {
        ResponseDTO<EventDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();

        if(eventService.updateStatus(value,"Disable")){
            throw new HttpUpdateFailedException("Error to disable event");
        }
        response = responseDTOConverter.createResponse(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<EventDTO>> findByID(UUID eventId, RequestDTO<EventDTO> request) {
        EventDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<EventDTO> response;

        Optional<Event> event = eventService.findById(eventId);

        if (event.isPresent()) {
            dto = eventDTOConverter.convertToDTO(event.get());

        }else{
            throw new HttpElementNotFoundExeption("Event not exist by ID: "+eventId);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
