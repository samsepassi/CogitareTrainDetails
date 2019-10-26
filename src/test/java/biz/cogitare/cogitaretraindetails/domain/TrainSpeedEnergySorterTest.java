package biz.cogitare.cogitaretraindetails.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class TrainSpeedEnergySorterTest {

    private final List<TrainSpeedEnergy> trainSpeedEnergy = new ArrayList<>();

    @Before
    public void setup() {
        trainSpeedEnergy.add(new TrainSpeedEnergy(10, 50));
        trainSpeedEnergy.add(new TrainSpeedEnergy(20, 100));
        trainSpeedEnergy.add(new TrainSpeedEnergy(30, 400));
        trainSpeedEnergy.add(new TrainSpeedEnergy(60, 250));
    }

    @Test
    public void sortBySpeedAscendingTest() {
        Collections.sort(trainSpeedEnergy, TrainSpeedEnergy.BY_SPEED_ASCENDING);
        TrainSpeedEnergy record = trainSpeedEnergy.get(0);
        assertThat(record.getSpeed()).isEqualTo(10);
        assertThat(record.getEnergy()).isEqualTo(50);
    }

    @Test
    public void sortByEnergyDescendingTest() {
        Collections.sort(trainSpeedEnergy, TrainSpeedEnergy.BY_ENERGY_DESCENDING);
        TrainSpeedEnergy record = trainSpeedEnergy.get(0);
        assertThat(record.getSpeed()).isEqualTo(30);
        assertThat(record.getEnergy()).isEqualTo(400);
    }

}
