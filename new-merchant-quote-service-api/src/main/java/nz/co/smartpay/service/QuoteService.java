package nz.co.smartpay.service;

import nz.co.smartpay.orm.model.TerminalPricing;
import nz.co.smartpay.orm.model.TransactionCountPricing;
import nz.co.smartpay.orm.model.TransactionVolumePricing;
import nz.co.smartpay.orm.repository.TerminalPricingRepository;
import nz.co.smartpay.orm.repository.TransactionCountPricingRepository;
import nz.co.smartpay.orm.repository.TransactionVolumePricingRepository;
import nz.co.smartpay.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class QuoteService {

    private TerminalPricingRepository terminalPricingRepository;
    private TransactionCountPricingRepository transactionCountPricingRepository;
    private TransactionVolumePricingRepository transactionVolumePricingRepository;

    @Autowired
    public QuoteService(TerminalPricingRepository terminalPricingRepository,
                        TransactionCountPricingRepository transactionCountPricingRepository,
                        TransactionVolumePricingRepository transactionVolumePricingRepository) {
        this.terminalPricingRepository = terminalPricingRepository;
        this.transactionCountPricingRepository = transactionCountPricingRepository;
        this.transactionVolumePricingRepository = transactionVolumePricingRepository;
    }

    public BigDecimal getQuote(String industry, Long transactionCount, BigDecimal transactionVolume) {
        BigDecimal price = getTerminalPricing(industry);
        price = price.add(getTransactionCountPricing(industry, transactionCount));
        price = price.add(getTransactionVolumePricing(industry, transactionVolume));

        return price.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal getTerminalPricing(String industry) {
        TerminalPricing pricing = terminalPricingRepository.findByIndustry(industry);
        if (pricing == null) {
            // @todo - handle errors
        }
        return pricing.getPrice();
    }

    public BigDecimal getTransactionCountPricing(String industry, Long transactionCount) {
        TransactionCountPricing lowerPricing = transactionCountPricingRepository.getPricingLessThanOrEqual(industry, transactionCount);
        if (lowerPricing != null && transactionCount.equals(lowerPricing.getTransactionCount())) {
            return lowerPricing.getPrice();
        }
        TransactionCountPricing upperPricing = transactionCountPricingRepository.getPricingGreaterThanOrEqual(industry, transactionCount);
        if (lowerPricing == null && upperPricing == null) {
            // @todo - handle error
        } else if (lowerPricing == null) {
            // Assumption 1 - If lower than lowest value - use lowest known value for that industry
            return upperPricing.getPrice();
        } else if (upperPricing == null) {
            // Assumption 1 - If higher than greatest value - use greatest known value for that industry
            return lowerPricing.getPrice();
        }

        return MathUtils.linearInterpolate(new BigDecimal(transactionCount),
                new BigDecimal(lowerPricing.getTransactionCount()),
                lowerPricing.getPrice(),
                new BigDecimal(upperPricing.getTransactionCount()),
                upperPricing.getPrice());
    }

    public BigDecimal getTransactionVolumePricing(String industry, BigDecimal transactionVolume) {
        TransactionVolumePricing lowerPricing = transactionVolumePricingRepository.getPricingLessThanOrEqual(industry, transactionVolume);
        if (lowerPricing != null && transactionVolume.equals(lowerPricing.getTransactionVolume())) {
            return lowerPricing.getPrice();
        }
        TransactionVolumePricing upperPricing = transactionVolumePricingRepository.getPricingGreaterThanOrEqual(industry, transactionVolume);
        if (lowerPricing == null && upperPricing == null) {
            // @todo - handle error
        } else if (lowerPricing == null) {
            // Assumption 1 - If lower than lowest value - use lowest known value for that industry
            return upperPricing.getPrice();
        } else if (upperPricing == null) {
            // Assumption 1 - If higher than greatest value - use greatest known value for that industry
            return lowerPricing.getPrice();
        }

        return MathUtils.linearInterpolate(transactionVolume,
                lowerPricing.getTransactionVolume(),
                lowerPricing.getPrice(),
                upperPricing.getTransactionVolume(),
                upperPricing.getPrice());
    }
}
