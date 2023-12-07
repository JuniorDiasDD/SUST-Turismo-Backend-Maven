package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.*;
import susturismo.susturismo.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PortfolioDTOConverter {

    @Autowired
    RoleDTOConverter roleDTOConverter;

    public PortfolioDTO convertToDTO(Portfolio portfolio) {

        PortfolioDTO dto = new PortfolioDTO();

        dto.setId(portfolio.getId());
        dto.setDescription(portfolio.getDescription());
        dto.setStatus(portfolio.getStatus());
        dto.setName(portfolio.getName());

        Set<RoleDTO> roleDTOS = portfolio.getRoles().stream().map(roleDTOConverter::convertToDTO).collect(Collectors.toSet());
        dto.setRoles(roleDTOS);
        return dto;

    }

    public Portfolio convertToEntity(PortfolioDTO dto) {

        Portfolio portfolio = new Portfolio();
        portfolio.setId(dto.getId());
        portfolio.setDescription(dto.getDescription());
        portfolio.setStatus(dto.getStatus());

        portfolio.setName(dto.getName());

        if(dto.getRoles()!=null &&!dto.getRoles().isEmpty()){
            Set<Role> roles = dto.getRoles().stream().map(roleDTOConverter::convertToEntity).collect(Collectors.toSet());
            portfolio.setRoles(roles);
        }
        return portfolio;

    }


}
