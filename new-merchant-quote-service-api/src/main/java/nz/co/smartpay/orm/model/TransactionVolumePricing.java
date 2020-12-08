package nz.co.smartpay.orm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction_volume_pricing")
public class TransactionVolumePricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "industry")
    private String industry;

    @Column(name = "transaction_volume")
    private BigDecimal transactionVolume;

    @Column(name = "price")
    private BigDecimal price;

}
