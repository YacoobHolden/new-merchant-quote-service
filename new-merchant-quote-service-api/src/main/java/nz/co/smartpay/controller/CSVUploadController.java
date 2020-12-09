package nz.co.smartpay.controller;

import nz.co.smartpay.service.CSVService;
import nz.co.smartpay.validator.CSVValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping(value="/csv")
public class CSVUploadController {

    private CSVService csvService;
    private CSVValidator csvValidator;

    @Autowired
    public CSVUploadController(CSVService csvService, CSVValidator csvValidator) {
        this.csvService = csvService;
        this.csvValidator = csvValidator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity updateCSV(@RequestParam("file") MultipartFile file) throws Exception {
        csvValidator.validate(file);
        csvService.updateCSV(file);
        return ResponseEntity.ok().build();
    }

}
