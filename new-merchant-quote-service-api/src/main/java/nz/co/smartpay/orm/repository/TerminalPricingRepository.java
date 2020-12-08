package nz.co.smartpay.orm.repository;

import nz.co.smartpay.orm.model.TerminalPricing;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static nz.co.smartpay.utils.CommonStringUtils.joinWithSpace;

@Repository
@Transactional
public class TerminalPricingRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(TerminalPricing terminalPricing) {
        em.persist(terminalPricing);
    }

    public TerminalPricing save(TerminalPricing terminalPricing) {
        return em.merge(terminalPricing);
    }

    public TerminalPricing findByIndustry(String industry) {
        String sql = joinWithSpace("SELECT *",
                "FROM quote.terminal_pricing",
                "WHERE industry = :industry");
        Query query = em.createNativeQuery(sql, TerminalPricing.class);
        query.setParameter("industry", industry);
        return (TerminalPricing) query.getSingleResult();
    }
}
