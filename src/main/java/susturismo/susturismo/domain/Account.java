package susturismo.susturismo.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity(name = "account")
@Table(name = "account")
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Basic(optional = false)
    @Column(name = "NAME", length = 64)
    private String name;

    @Basic(optional = false)
    @Column(name = "LOGIN", length = 64)
    private String login;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
    @Column(name = "IMAGE", columnDefinition = "TEXT")
    private String image;
    @Column(name = "PAIS", columnDefinition = "TEXT")
    private String pais;
    @Column(name = "UNIVERSIDADE", columnDefinition = "TEXT")
    private String universidade;

    @Column(name = "EMAIL", length = 254)
    private String email;

    @Column(name = "STATUS", length = 64)
    private String status;

    @Column(name = "TEL", length = 64)
    private String tel;

    @Column(name = "GOOGLE_ID", length = 254)
    private String google_id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "ACCOUNT_PORTFOLIO",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "portfolio_id", referencedColumnName = "ID"))
    private Set<Portfolio> portfolios;

    @Column(name = "AUTH", nullable = false)
    private UUID auth;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Set<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Set<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public Account(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UUID getAuth() {
        return auth;
    }

    public void setAuth(UUID auth) {
        this.auth = auth;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
}
