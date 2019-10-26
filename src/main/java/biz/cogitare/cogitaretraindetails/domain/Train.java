package biz.cogitare.cogitaretraindetails.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A model representing a train containing a list of Speed/Energy properties.
 * @author Sam
 */
@Data
@AllArgsConstructor
public class Train {

    private String ID;
    private List<TrainSpeedEnergy> speedEnergy = new ArrayList<>();

    public Train(String ID) {
        this.ID = ID;
    }
    
    
}
