package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.domain.Role;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.FeedDTO;
import susturismo.susturismo.web.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleDTOConverter {



    public RoleDTO convertToDTO(Role role) {

        RoleDTO dto = new RoleDTO();

        dto.setId(role.getId());
        dto.setDescription(role.getDescription());
        dto.setStatus(role.getStatus());
        dto.setName(role.getName());
        return dto;

    }

    public Role convertToEntity(RoleDTO dto) {

        Role objt = new Role();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setStatus(dto.getStatus());
        objt.setName(dto.getName());
        return objt;

    }


}
