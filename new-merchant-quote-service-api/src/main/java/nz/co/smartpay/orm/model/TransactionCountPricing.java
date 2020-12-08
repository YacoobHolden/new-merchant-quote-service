package nz.co.smartpay.orm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "transaction_count_pricing")
public class TransactionCountPricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "industry")
    private String industry;

    @Column(name = "transaction_count")
    private Integer transactionCount;

    @Column(name = "price")
    private Long price;
}
