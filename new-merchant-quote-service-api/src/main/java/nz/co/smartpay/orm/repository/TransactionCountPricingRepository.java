package nz.co.smartpay.orm.repository;

import nz.co.smartpay.orm.model.TransactionCountPricing;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static nz.co.smartpay.utils.CommonStringUtils.joinWithSpace;

@Repository
@Transactional
public class TransactionCountPricingRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(TransactionCountPricing transactionCountPricing) {
        em.persist(transactionCountPricing);
    }

    public TransactionCountPricing save(TransactionCountPricing transactionCountPricing) {
        return em.merge(transactionCountPricing);
    }

    public TransactionCountPricing getPricingLessThanOrEqual(String industry, Long transactionCount) {
        String sql = joinWithSpace("SELECT *",
                "FROM quote.transaction_count_pricing",
                "WHERE industry = :industry",
                "AND transaction_count <= :transactionCount",
                "ORDER BY transaction_count DESC",
                "LIMIT 1");
        Query query = em.createNativeQuery(sql, TransactionCountPricing.class);
        query.setParameter("industry", industry);
        query.setParameter("transactionCount", transactionCount);

        return (TransactionCountPricing) query.getResultList().stream().findFirst().orElse(null);
    }

    public TransactionCountPricing getPricingGreaterThanOrEqual(String industry, Long transactionCount) {
        String sql = joinWithSpace("SELECT *",
                "FROM quote.transaction_count_pricing",
                "WHERE industry = :industry",
                "AND transaction_count >= :transactionCount",
                "ORDER BY transaction_count ASC",
                "LIMIT 1");
        Query query = em.createNativeQuery(sql, TransactionCountPricing.class);
        query.setParameter("industry", industry);
        query.setParameter("transactionCount", transactionCount);

        return (TransactionCountPricing) query.getResultList().stream().findFirst().orElse(null);
    }
}
