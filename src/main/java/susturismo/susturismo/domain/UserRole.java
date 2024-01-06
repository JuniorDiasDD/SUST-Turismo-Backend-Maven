package susturismo.susturismo.domain;

public enum UserRole {

    ADMIN("gestor"),
    USER("user"),
    COOR("coordenador");


    private String role;

    UserRole(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }
}
