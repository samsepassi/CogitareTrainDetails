package biz.cogitare.cogitaretraindetails.domain.service;

import biz.cogitare.cogitaretraindetails.domain.Train;
import java.util.List;
import java.util.Map;

/**
 * Marshalling a list of raw data to a list of domain objects.
 * @author Sam
 */
public interface DomainMarshallerService {

    /**
     * Transforming a list of string to a map containing domain object.
     * @param rawData - a list of strings to be marshalled.
     * @return
     */
    public Map<String,Train> process(List<String> rawData);
}
