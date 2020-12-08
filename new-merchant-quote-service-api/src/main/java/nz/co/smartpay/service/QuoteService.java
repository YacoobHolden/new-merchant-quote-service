package nz.co.smartpay.service;

import nz.co.smartpay.orm.model.TerminalPricing;
import nz.co.smartpay.orm.repository.TerminalPricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private TerminalPricingRepository terminalPricingRepository;

    @Autowired
    public QuoteService(TerminalPricingRepository terminalPricingRepository) {
        this.terminalPricingRepository = terminalPricingRepository;
    }

    public Long getQuote(String industry, Long transactionCount, Long transactionVolume) {
        long price = getTerminalPricing(industry);
        price += getTransactionCountPricing(industry, transactionCount);
        price += getTransactionVolumePricing(industry, transactionVolume);

        return price;
    }

    public Long getTerminalPricing(String industry) {
        TerminalPricing pricing = terminalPricingRepository.findByIndustry(industry);
        if (pricing == null) {
            // @todo - handle errors
        }
        return pricing.getPrice();
    }

    public Long getTransactionCountPricing(String industry, Long transactionCount) {
        // @todo - this
        return 0L;
    }

    public Long getTransactionVolumePricing(String industry, Long transactionVolume) {
        // @todo - this
        return 0L;
    }
}
