/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.infrastructure;

import biz.cogitare.cogitaretraindetails.application.exceptions.FileReaderException;
import biz.cogitare.cogitaretraindetails.application.service.FileReaderService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Sam
 */
public class FileReaderServiceImplementation implements FileReaderService {

    private static final Logger LOG = Logger.getLogger(FileReaderServiceImplementation.class.getName());

    @Override
    public List<String> readFile(final String resourceURI, final String header) throws FileReaderException {
        try {
            String respath = "/" + resourceURI;
            InputStream inputStream = getClass().getResourceAsStream(respath);
            try (Stream<String> streamLines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
                List<String> detailRecords = new ArrayList<>();
                Stream<String> records = streamLines;
                detailRecords = records.
                        filter(eachRecord -> !eachRecord.equalsIgnoreCase(header))
                        .collect(Collectors.toList());
                return detailRecords;
            } finally {
                inputStream.close();
            }

        } catch (IOException exception) {
            LOG.log(Level.SEVERE, "Error occured during processing file. Reason : {0}", exception.getMessage());
            throw new FileReaderException(exception.getMessage());
        } catch (NullPointerException nullPointerException) {
            LOG.log(Level.SEVERE, "Error occured during processing file. Reason : {0}", nullPointerException.getMessage());
            LOG.log(Level.SEVERE, "Cannot open the file or the file name is invalid : {0}", resourceURI);
            throw new FileReaderException(nullPointerException.getMessage());
        }
    }
}
