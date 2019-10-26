/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.domain;

import biz.cogitare.cogitaretraindetails.application.service.DataRepositoryService;

/**
 * Class containing the methods to perform domain specific operations on data records
 * @author Sam
 */
public interface TrainRepositoryService {

    /**
     * Initialize the repository object that must be before any operations
     * Acting a session to data source.
     * @param dataRepository - Should be an implementation of data respository.
     */
    public void init(DataRepositoryService dataRepository);

    /**
     * Checks whether the data is available in the repository or not.
     * @param trainId 
     * @return
     */
    public boolean isValid(String trainId);

    /**
     * Find the lowest speed for the train type.
     * @param trainId
     * @return
     */
    public int getTheLowestSpeed(String trainId);
    
    /**
     * Find the highest energy consumption and the speed details.
     * @param trainId
     * @return
     */
    public TrainSpeedEnergy getTheHighestEnergyConsumer(String trainId);
    
}
