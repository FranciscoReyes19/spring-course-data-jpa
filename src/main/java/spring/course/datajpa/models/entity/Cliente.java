package spring.course.datajpa.models.entity;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "el formato no es valido") //estan en archivo messages.properties
    private String name;

    @NotEmpty(message = "el formato no es valido")
    private String surname;

    @NotEmpty(message = "El correo no es valido")
    @Email(message = "El formato del correo no es valido")
    private String email;

    @NotNull(message = "el campo no debe estar vacio")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE )
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private Date createdAt;

    private String photo;

    //no hara el llamado de facturas hasta que se utilze el metodo(FecthType.LAZY)
    //todas las facturas del cliente se eliminaran si se elimina el cliente(CascadeType.ALL)
    //De manera autmatica creara la llave foranea y key para relacionar tablas (mappedBy = "cliente")
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Invoice> invoices;

    public Cliente() {
        invoices = new ArrayList<Invoice>();
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public String getPhoto() {
        return photo;
    }

    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
    }
    //    @PrePersist  //se ejecutara justo antes de guardar el registro en la DB
    //    public void prePersist(){
    //        createdAt = new Date();
    //    }
}
