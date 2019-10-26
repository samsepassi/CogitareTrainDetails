
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.domain;

import biz.cogitare.cogitaretraindetails.infrastructure.TrainRepositoryImplementation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import biz.cogitare.cogitaretraindetails.application.service.DataRepositoryService;

/**
 *
 * @author Sam
 */
@RunWith(MockitoJUnitRunner.class)
public class TrainRepositoryTest {

    @Mock
    private DataRepositoryService dataRepository;

    private final TrainRepositoryService trainRepository = new TrainRepositoryImplementation();

    private Map<String, Train> mockDataStore = new HashMap<>();

    private final String trainID1 = "AAA";
    private final String trainID2 = "BBB";

    private final String invalidTrainID = "INVALID";

    @Before
    public void setup() {
        mockDataStore = createMockDataStore();
        when(dataRepository.getDataStore()).thenReturn(mockDataStore);
        trainRepository.init(dataRepository);
    }

    @Test
    public void testCheckValidity() {
        assertThat(trainRepository.isValid(trainID1)).isEqualTo(true);
        assertThat(trainRepository.isValid(trainID2)).isEqualTo(true);
        assertThat(trainRepository.isValid(invalidTrainID)).isEqualTo(false);
    }

    @Test
    public void testGetTheLowestSpeed() {
        assertThat(trainRepository.getTheLowestSpeed(trainID1)).isEqualTo(10);
        assertThat(trainRepository.getTheLowestSpeed(trainID2)).isEqualTo(30);
    }

    @Test
    public void testGetHighestEnergyConsumer() {
        TrainSpeedEnergy trainSpeedEnergy1 = trainRepository.getTheHighestEnergyConsumer(trainID1);
        assertThat(trainSpeedEnergy1.getEnergy()).isEqualTo(100);
        assertThat(trainSpeedEnergy1.getSpeed()).isEqualTo(20);

        TrainSpeedEnergy trainSpeedEnergy2 = trainRepository.getTheHighestEnergyConsumer(trainID2);
        assertThat(trainSpeedEnergy2.getEnergy()).isEqualTo(400);
        assertThat(trainSpeedEnergy2.getSpeed()).isEqualTo(30);
    }

    private Map<String, Train> createMockDataStore() {
        final Map<String, Train> dataStore = new HashMap<>();

        //Adding the first train
        List<TrainSpeedEnergy> train1SpeedEnergy = new ArrayList<>();
        train1SpeedEnergy.add(new TrainSpeedEnergy(10, 50));
        train1SpeedEnergy.add(new TrainSpeedEnergy(20, 100));
        dataStore.put(trainID1, new Train(trainID1, train1SpeedEnergy));

        //Adding the second train
        List<TrainSpeedEnergy> train2SpeedEnergy = new ArrayList<>();
        train2SpeedEnergy.add(new TrainSpeedEnergy(30, 400));
        train2SpeedEnergy.add(new TrainSpeedEnergy(60, 250));
        dataStore.put(trainID2, new Train(trainID2, train2SpeedEnergy));

        return dataStore;
    }

}
