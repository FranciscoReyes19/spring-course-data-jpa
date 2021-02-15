package spring.course.datajpa.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoices_items")
public class ItemInvoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;


    @ManyToOne(fetch = FetchType.LAZY) //ya se crea una llave foranea sin necesidad de usar @JoinColumn
    //@JoinColumn(name = "product_id")
    private Product product;

    //Methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double calcImport(){
        return amount.doubleValue();
    }
}
