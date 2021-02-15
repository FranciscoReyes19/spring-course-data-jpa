package spring.course.datajpa.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String Details;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    //EAGER trae _todo
    //LAZY es modo peresozo
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<ItemInvoice> items;

    //Methods

    public Invoice() {
        this.items = new ArrayList<ItemInvoice>();
    }

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemInvoice> getItems() {
        return items;
    }

    public void setItems(List<ItemInvoice> items) {
        this.items = items;
    }

    public void addItemInvoice(ItemInvoice item){
        this.items.add(item);
    }

    //Custom methods

    public Double getTotal(){
        Double total = 0.0;

        for (ItemInvoice item : items) {
            total = item.calcImport();
        }

//      int size = items.size();
//      for (int i= 0; i< size ; i++){
//          total += items.get(i).calcImport();
//      }
        return total;

    }

}