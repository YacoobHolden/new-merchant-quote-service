package nz.co.smartpay.orm.repository;

import nz.co.smartpay.orm.model.TransactionVolumePricing;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigDecimal;

import static nz.co.smartpay.utils.CommonStringUtils.joinWithSpace;

@Repository
@Transactional
public class TransactionVolumePricingRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(TransactionVolumePricing transactionVolumePricing) {
        em.persist(transactionVolumePricing);
    }

    public TransactionVolumePricing save(TransactionVolumePricing transactionVolumePricing) {
        return em.merge(transactionVolumePricing);
    }

    public TransactionVolumePricing getPricingLessThanOrEqual(String industry, BigDecimal transactionVolume) {
        String sql = joinWithSpace("SELECT *",
                "FROM quote.transaction_volume_pricing",
                "WHERE industry = :industry",
                "AND transaction_volume <= :transactionVolume",
                "ORDER BY transaction_volume DESC",
                "LIMIT 1");
        Query query = em.createNativeQuery(sql, TransactionVolumePricing.class);
        query.setParameter("industry", industry);
        query.setParameter("transactionVolume", transactionVolume);

        return (TransactionVolumePricing) query.getResultList().stream().findFirst().orElse(null);
    }

    public TransactionVolumePricing getPricingGreaterThanOrEqual(String industry, BigDecimal transactionVolume) {
        String sql = joinWithSpace("SELECT *",
                "FROM quote.transaction_volume_pricing",
                "WHERE industry = :industry",
                "AND transaction_volume >= :transactionVolume",
                "ORDER BY transaction_volume ASC",
                "LIMIT 1");
        Query query = em.createNativeQuery(sql, TransactionVolumePricing.class);
        query.setParameter("industry", industry);
        query.setParameter("transactionVolume", transactionVolume);

        return (TransactionVolumePricing) query.getResultList().stream().findFirst().orElse(null);
    }
}
