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

    @Column(name = "EMAIL", length = 254)
    private String email;

    @Column(name = "STATUS", length = 64)
    private String status;

    @Column(name = "TEL", length = 64)
    private String tel;

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
}
