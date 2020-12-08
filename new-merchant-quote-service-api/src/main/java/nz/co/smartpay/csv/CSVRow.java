package nz.co.smartpay.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CSVRow {

    public enum RowType {
        TERMINAL, TRANSACTION_COUNT, TRANSACTION_VOLUME,
    }

    @CsvBindByName(column = "Industry")
    private String industry;

    @CsvBindByName(column = "Type")
    private String type;

    @CsvBindByName(column = "Value")
    private BigDecimal value;

    @CsvBindByName(column = "Price")
    private BigDecimal price;

    public RowType getRowType() {
        return RowType.valueOf(type.trim());
    }

}
