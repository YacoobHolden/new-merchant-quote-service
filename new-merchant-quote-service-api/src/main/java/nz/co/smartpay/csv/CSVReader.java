package nz.co.smartpay.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CSVReader {

    public List<CSVRow> readCSVRowsFromFile(MultipartFile file) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<CSVRow> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVRow.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (IOException ex) {
            throw ex;
        }
    }

}
