package nz.co.smartpay.controller;

import nz.co.smartpay.service.QuoteService;
import nz.co.smartpay.validator.QuoteRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value="/quote")
public class QuoteController {

    private QuoteService quoteService;
    private QuoteRequestValidator quoteRequestValidator;

    @Autowired
    public QuoteController(QuoteService quoteService, QuoteRequestValidator quoteRequestValidator) {
        this.quoteService = quoteService;
        this.quoteRequestValidator = quoteRequestValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BigDecimal getQuote(@RequestParam String industry, @RequestParam Long transactionCount, @RequestParam BigDecimal transactionVolume) {
        quoteRequestValidator.validate(industry, transactionCount, transactionVolume);
        return quoteService.getQuote(industry, transactionCount, transactionVolume);
    }

}
