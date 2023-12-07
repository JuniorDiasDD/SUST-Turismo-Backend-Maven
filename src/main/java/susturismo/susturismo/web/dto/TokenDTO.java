package susturismo.susturismo.web.dto;

public class TokenDTO implements DTO {

    private String token;
    private String login;

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


    @Override
    public String toString() {
        return "TokenDTO{" +
                "token:'" + token + '\'' +
                ", login:'" + login + '\'' +
                '}';
    }
}
