package susturismo.susturismo.web.dto;


import susturismo.susturismo.domain.Portfolio;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class PortfolioDTO {

    private UUID id;
    private String name;
    private String description;
    private String status;
    Set<RoleDTO> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
