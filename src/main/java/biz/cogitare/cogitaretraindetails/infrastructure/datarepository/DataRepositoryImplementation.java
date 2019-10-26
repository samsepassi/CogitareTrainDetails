/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.infrastructure.datarepository;

import biz.cogitare.cogitaretraindetails.domain.service.DomainMarshallerService;
import biz.cogitare.cogitaretraindetails.application.exceptions.FileReaderException;
import biz.cogitare.cogitaretraindetails.application.service.FileReaderService;
import biz.cogitare.cogitaretraindetails.domain.Train;
import biz.cogitare.cogitaretraindetails.infrastructure.DomainMarshallerServiceImplementation;
import biz.cogitare.cogitaretraindetails.infrastructure.FileReaderServiceImplementation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import biz.cogitare.cogitaretraindetails.application.service.DataRepositoryService;

/**
 *
 * @author Sam
 */
public class DataRepositoryImplementation implements DataRepositoryService, Runnable {

    private static final Logger LOG = Logger.getLogger(DataRepositoryImplementation.class.getName());
    //A visible data store holding all the marshalled records
    private transient Map<String, Train> dataStore = new HashMap<>();
    //Lock to control the threads access
    private final Lock lock = new ReentrantLock();
    //Service to read the file contents
    private final FileReaderService fileReaderService = new FileReaderServiceImplementation();
    //Marshalling the list of raw data to Map containing Train objects.
    private final DomainMarshallerService marshallerService = new DomainMarshallerServiceImplementation();
    //Address of the file
    private String resourceURI = new String();
    //Header to be omitted during reading the file contents
    private final String header = "TrainType-Speed(KMPH)-Energy(KWH)";

    //Initiates the data repository and fills the data store
    @Override
    public void init(final String resourceURI) {
        if (getDataStore().isEmpty()) {
            this.resourceURI = resourceURI;
            LOG.log(Level.INFO, "Reading and preparing raw data contents. Please wait ...");
            Thread fileReaderThread = new Thread(this);
            fileReaderThread.start();
        }
    }

    @Override
    public Map<String, Train> getDataStore() {
        //Restricting the access of the consumers thread while the supplier thread is writing.
        try {
            lock.lock();
            return dataStore;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        //Lock the resource while a supplier is writing to the data store
        try {
            lock.lock();
            try {
                //Reading the raw file using the file reader service.
                List<String> fileContent = fileReaderService.readFile(resourceURI, header);
                //Marshalling the raw contents to domain objects.
                dataStore = marshallerService.process(fileContent);
            } catch (FileReaderException ex) {
                LOG.log(Level.INFO,ex.getMessage());
            }
        } finally {
            lock.unlock();
        }

    }
}
