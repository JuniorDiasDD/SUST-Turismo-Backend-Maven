package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/category")
public interface CategoryApi {

    @GetMapping(path = "/all")
     ResponseEntity<ResponseDTOList<CategoryDTO>> findAllCategorys(@RequestBody(required = false) RequestDTOList<CategoryDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<CategoryDTO>> insertCategory(@RequestBody(required = false) @Valid RequestDTO<CategoryDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<CategoryDTO>> updateCategory(@RequestBody(required = false) RequestDTO<CategoryDTO> requestBody);

    @PostMapping(path = "/disable")
    ResponseEntity<Object> disableCategory(@RequestBody(required = false) RequestDTOList<UUID> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> activeCategory(@RequestBody(required = false) RequestDTOList<UUID> request);
}
