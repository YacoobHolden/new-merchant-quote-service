package nz.co.smartpay.validator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class QuoteRequestValidator {

    public void validate(String industry, Long transactionCount, BigDecimal transactionVolume) {
        // todo - validate
    }
}
