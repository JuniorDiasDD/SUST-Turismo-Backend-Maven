package susturismo.susturismo.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTable {

    @CreationTimestamp
    @Column(name = "CRIADO_EM")
    private LocalDateTime criadoEm;

    @Basic(optional = false)
    @CreatedBy
    @Column(name = "CRIADO_POR", nullable = false)
    private UUID criadoPor;

    @UpdateTimestamp
    @Column(name = "ALTERADO_EM", nullable = false)
    private LocalDateTime alteradoEm;

    @Basic(optional = false)
    @LastModifiedBy
    @Column(name = "ALTERADO_POR", nullable = false)
    private UUID alteradoPor;

    @PrePersist
    public void setCriadoEm() {
        if (this.criadoEm == null)  this.criadoEm = LocalDateTime.now();
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public LocalDateTime getAlteradoEm() {
        return alteradoEm;
    }

    public UUID getCriadoPor() {
        return criadoPor;
    }
    public void setCriadoPor(UUID criadoPor) {
        this.criadoPor = criadoPor;
    }

    public UUID getAlteradoPor() {
        return alteradoPor;
    }
    public void setAlteradoPor(UUID alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

  /*    @PrePersist
    public void setCriadoEm() {
        if (this.criadoEm == null)  this.criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void setAlteradoEm() {
        this.alteradoEm = LocalDateTime.now();
    }



  public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public void setAlteradoEm(LocalDateTime alteradoEm) {
        this.alteradoEm = alteradoEm;
    }*/
}
