/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.infrastructure;

import biz.cogitare.cogitaretraindetails.domain.service.DomainMarshallerService;
import biz.cogitare.cogitaretraindetails.domain.Train;
import biz.cogitare.cogitaretraindetails.domain.TrainSpeedEnergy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sam
 */
public class DomainMarshallerServiceImplementation implements DomainMarshallerService {

    @Override
    public Map<String, Train> process(List<String> bulkData) {
        final Map<String, Train> domainMap = new HashMap<>();
        bulkData.forEach(eachRow -> {
            final String[] infoDetails = eachRow.split("-", 3);
            final String trainId = infoDetails[0];
            final int speed = Integer.parseInt(infoDetails[1]);
            final int energy = Integer.parseInt(infoDetails[2]);
            Train train = domainMap.getOrDefault(trainId, new Train(infoDetails[0]));
            train.getSpeedEnergy()
                    .add(new TrainSpeedEnergy(speed, energy));
            domainMap.put(train.getID(), train);
        });
        return domainMap;
    }

}
