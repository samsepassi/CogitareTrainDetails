package biz.cogitare.cogitaretraindetails.application.service;

import biz.cogitare.cogitaretraindetails.domain.Train;
import java.util.Map;

/**
 * This class provides the access to the data records.
 * @author Sam
 */
public interface DataRepositoryService {

    /**
     * Must be called to initiate a session to access the data records. 
     * @param resourceURI
     */
    public void init(String resourceURI);

    /**
     * Returns the data store.
     * @return
     */
    public Map<String, Train> getDataStore();
}
