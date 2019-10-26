/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.domain;

import java.util.Comparator;
import static java.util.Comparator.comparing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Holding a pair of Speed/Energy detail for a specific train model.
 * @author Sam
 */
@Getter
@Setter
@AllArgsConstructor
public class TrainSpeedEnergy {

    private int Speed;
    private int Energy;

    /**
     * Sorts the details based on the speed in ascending order.
     */
    public static final Comparator<TrainSpeedEnergy> BY_SPEED_ASCENDING = comparing(TrainSpeedEnergy::getSpeed);

    /**
     * Sorts the details based on the energy consumption in descending order.
     */
    public static final Comparator<TrainSpeedEnergy> BY_ENERGY_DESCENDING
            = comparing(TrainSpeedEnergy::getEnergy, (tse1, tse2) -> {
                return tse2.compareTo(tse1);
            });

}
