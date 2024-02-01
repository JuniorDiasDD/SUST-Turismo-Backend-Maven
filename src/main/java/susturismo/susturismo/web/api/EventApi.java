package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/event")
public interface EventApi {

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEvents(@RequestBody(required = false) RequestDTOList<EventDTO> request);
    @GetMapping(path = "/limit")
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllLimit(@RequestBody(required = false) RequestDTOList<EventDTO> request);
    @GetMapping(path = "/my")
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllByUser(@RequestBody(required = false) RequestDTOList<EventDTO> request);
    @GetMapping(path = "/aprove")
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEventsApprove(@RequestBody(required = false) RequestDTOList<EventDTO> request);
    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<EventDTO>> findByID(
            @PathVariable(value = "id") UUID eventId,
            @RequestBody(required = false) RequestDTO<EventDTO> request);
    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<EventDTO>> findAllEventsActive(@RequestBody(required = false) RequestDTOList<EventDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<EventDTO>> updateEvent(@RequestBody(required = false) RequestDTO<EventDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> activeEvent(@RequestBody(required = false) RequestDTO<EventDTO> request);
    @PostMapping(path = "/approve")
    ResponseEntity<Object> approve(@RequestBody(required = false) RequestDTO<EventDTO> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disableEvent(@RequestBody(required = false) RequestDTO<EventDTO> request);
    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<EventDTO>> insertEvent(@RequestBody(required = false) @Valid RequestDTO<EventDTO> request);



}
