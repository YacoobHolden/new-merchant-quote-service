package nz.co.smartpay.service;

import nz.co.smartpay.csv.CSVReader;
import nz.co.smartpay.csv.CSVRow;
import nz.co.smartpay.orm.model.TerminalPricing;
import nz.co.smartpay.orm.model.TransactionCountPricing;
import nz.co.smartpay.orm.model.TransactionVolumePricing;
import nz.co.smartpay.orm.repository.TerminalPricingRepository;
import nz.co.smartpay.orm.repository.TransactionCountPricingRepository;
import nz.co.smartpay.orm.repository.TransactionVolumePricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CSVService {

    private CSVReader reader;
    private TerminalPricingRepository terminalPricingRepository;
    private TransactionCountPricingRepository transactionCountPricingRepository;
    private TransactionVolumePricingRepository transactionVolumePricingRepository;

    @Autowired
    public CSVService(CSVReader reader,
                      TerminalPricingRepository terminalPricingRepository,
                      TransactionCountPricingRepository transactionCountPricingRepository,
                      TransactionVolumePricingRepository transactionVolumePricingRepository) {
        this.terminalPricingRepository = terminalPricingRepository;
        this.transactionCountPricingRepository = transactionCountPricingRepository;
        this.transactionVolumePricingRepository = transactionVolumePricingRepository;
        this.reader = reader;
    }

    @Transactional
    public void updateCSV(MultipartFile file) throws Exception {
        List<CSVRow> csvRows = reader.readCSVRowsFromFile(file);
        // @ todo - Insert in one insert statement
        // Assumption 3 - Don't need to handle updates of uploaded values
        for (CSVRow row : csvRows) {
            // Add new terminal entry
            if (CSVRow.RowType.TERMINAL.equals(row.getRowType())) {
                TerminalPricing pricing = TerminalPricing.builder()
                        .industry(row.getIndustry())
                        .price(row.getPrice())
                        .build();
                terminalPricingRepository.save(pricing);
            } else if (CSVRow.RowType.TRANSACTION_COUNT.equals(row.getRowType())) {
                // Add new transaction count entry
                TransactionCountPricing pricing = TransactionCountPricing.builder()
                        .industry(row.getIndustry())
                        .transactionCount(row.getValue().longValue())
                        .price(row.getPrice())
                        .build();
                transactionCountPricingRepository.save(pricing);
            } else if (CSVRow.RowType.TRANSACTION_VOLUME.equals(row.getRowType())) {
                // Add new transaction volume entry
                TransactionVolumePricing pricing = TransactionVolumePricing.builder()
                        .industry(row.getIndustry())
                        .transactionVolume(row.getValue())
                        .price(row.getPrice())
                        .build();
                transactionVolumePricingRepository.save(pricing);
            }
        }
    }
}
