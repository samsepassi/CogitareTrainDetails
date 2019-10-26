/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.infrastructure;

import biz.cogitare.cogitaretraindetails.domain.TrainSpeedEnergy;
import java.util.Collections;
import java.util.List;
import static biz.cogitare.cogitaretraindetails.domain.TrainSpeedEnergy.BY_SPEED_ASCENDING;
import static biz.cogitare.cogitaretraindetails.domain.TrainSpeedEnergy.BY_ENERGY_DESCENDING;
import biz.cogitare.cogitaretraindetails.domain.TrainRepositoryService;
import biz.cogitare.cogitaretraindetails.application.service.DataRepositoryService;

/**
 *
 * @author Sam
 */
public class TrainRepositoryImplementation implements TrainRepositoryService {

    private DataRepositoryService dataRepository;

    @Override
    public void init(DataRepositoryService dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTheLowestSpeed(final String trainId) {
        List<TrainSpeedEnergy> details = dataRepository.getDataStore().get(trainId).getSpeedEnergy();
        Collections.sort(details, BY_SPEED_ASCENDING);
        return details.get(0).getSpeed();
    }

    @Override
    public TrainSpeedEnergy getTheHighestEnergyConsumer(final String trainId) {
        List<TrainSpeedEnergy> details = dataRepository.getDataStore().get(trainId).getSpeedEnergy();
        Collections.sort(details, BY_ENERGY_DESCENDING);
        return details.get(0);
    }

    @Override
    public boolean isValid(final String trainId) {
        return dataRepository.getDataStore().containsKey(trainId);
    }

}
