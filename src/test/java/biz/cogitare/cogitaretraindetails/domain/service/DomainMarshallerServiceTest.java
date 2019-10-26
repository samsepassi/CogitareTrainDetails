/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.cogitare.cogitaretraindetails.domain.service;

import biz.cogitare.cogitaretraindetails.domain.Train;
import biz.cogitare.cogitaretraindetails.infrastructure.DomainMarshallerServiceImplementation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class DomainMarshallerServiceTest {

    private final DomainMarshallerService marshallerService = new DomainMarshallerServiceImplementation();
    private final List<String> rawData = new ArrayList<>();

    private final String trainID1 = "156A";
    private final String trainID2 = "201V";

    private Map<String, Train> processedData = new HashMap<>();

    @Before
    public void setup() {
        rawData.add("156A-10-50");
        rawData.add("156A-20-100");

        rawData.add("201V-20-60");
        rawData.add("201V-40-100");
        rawData.add("201V-45-200");

        processedData = new HashMap<>();
        processedData = marshallerService.process(rawData);
    }

    @Test
    public void isEmptyTest() {
        assertThat(processedData.isEmpty()).isEqualTo(false);
    }

    @Test
    public void containsTest() {
        assertThat(processedData.containsKey(trainID1)).isEqualTo(true);
        assertThat(processedData.containsKey(trainID2)).isEqualTo(true);
    }
    
    @Test
    public void countTest(){
        assertThat(processedData.size()).isEqualTo(2);
    }
    
    @Test
    public void singleElementTest(){
        Train Train1 = processedData.get("156A");
        Train Train2 = processedData.get("201V");
        
        assertThat(Train1).isNotNull();
        assertThat(Train2).isNotNull();
        
        assertThat(Train1.getSpeedEnergy().size()).isEqualTo(2);
        assertThat(Train2.getSpeedEnergy().size()).isEqualTo(3);
    }
}
