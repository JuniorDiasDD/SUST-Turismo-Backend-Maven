package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.CategoryService;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.NoticiaDTO;
import susturismo.susturismo.web.dto.converter.CategoryDTOConverter;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CategoryController implements CategoryApi{

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryDTOConverter categoryDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Override
    public ResponseEntity<ResponseDTOList<CategoryDTO>> findAllCategorys(RequestDTOList<CategoryDTO> request) {
        List<CategoryDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<CategoryDTO> response;
        List<Category> list = categoryService.findAll();

        dtoList = list.stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Categories Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Categories", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<CategoryDTO>> insertCategory(RequestDTO<CategoryDTO> request) {

        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<CategoryDTO> response;

        Category category=categoryService.insert(categoryDTOConverter.convertToEntity(request.getRequest()));

        if(category==null) {
            throw new HttpInsertFailedException("Error to save");

        }

        CategoryDTO dto=categoryDTOConverter.convertToDTO(categoryService.findByName(category.getName()).get());
        response = responseDTOConverter.createResponse(request, dto, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO<CategoryDTO>> updateCategory(RequestDTO<CategoryDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<CategoryDTO> response;

        Category category=categoryService.update(categoryDTOConverter.convertToEntity(request.getRequest()));

        if(category==null) {
            throw new HttpUpdateFailedException("Error to update");
        }
        CategoryDTO dto=categoryDTOConverter.convertToDTO(categoryService.findByName(category.getName()).get());
        response = responseDTOConverter.createResponse(request, dto, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disableCategory(RequestDTOList<UUID> request) {
        ResponseDTOList<CategoryDTO> response;
        HttpHeaders headers = new HttpHeaders();
        request.getRequest().forEach(value->{
            if(categoryService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("UUID: "+value+" not exist");
            }
        });

        response = responseDTOConverter.createResponseWithList(request, null, "Active", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> activeCategory(RequestDTOList<UUID> request) {
        ResponseDTOList<CategoryDTO> response;
        HttpHeaders headers = new HttpHeaders();
        request.getRequest().forEach(value->{
            if(categoryService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("UUID: "+value+" not exist");
            }
        });

        response = responseDTOConverter.createResponseWithList(request, null, "Active", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
