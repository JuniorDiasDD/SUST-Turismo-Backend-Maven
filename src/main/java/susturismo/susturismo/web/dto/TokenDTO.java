package susturismo.susturismo.web.dto;

import java.util.UUID;

public class TokenDTO implements DTO {

    private String token;
    private String login;
    private UUID id;
    private String perfil;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "token:'" + token + '\'' +
                ", login:'" + login + '\'' +
                '}';
    }
}
