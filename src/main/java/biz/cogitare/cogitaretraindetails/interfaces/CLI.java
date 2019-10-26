package biz.cogitare.cogitaretraindetails.interfaces;

import biz.cogitare.cogitaretraindetails.infrastructure.datarepository.DataRepositoryImplementation;
import biz.cogitare.cogitaretraindetails.domain.TrainSpeedEnergy;
import biz.cogitare.cogitaretraindetails.infrastructure.TrainRepositoryImplementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import biz.cogitare.cogitaretraindetails.domain.TrainRepositoryService;
import biz.cogitare.cogitaretraindetails.application.service.DataRepositoryService;

/**
 *
 * @author Sam
 */
public class CLI {

    private static final Logger LOG = Logger.getLogger(CLI.class.getName());

    //Injecting the required services.
    private static final DataRepositoryService dataRepositoryService = new DataRepositoryImplementation();
    private static final TrainRepositoryService trainRepositoryService = new TrainRepositoryImplementation();
    private static boolean Running = true;

    public static void main(String[] args) throws URISyntaxException {
        try {
            //Inializing all the services.
            dataRepositoryService.init("TrainDetails.txt");
            trainRepositoryService.init(dataRepositoryService);
            while (Running) {
                //Receiving input from command line.
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please input Train ID to continue. To terminate the program enter 'quit' or 'exit' : ");
                String userInput = reader.readLine();

                if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("exit")) {
                    Running = false;
                } else {
                    if (trainRepositoryService.isValid(userInput)) {
                        System.out.println("Lowest speed for this train mode is : ");
                        System.out.println(trainRepositoryService.getTheLowestSpeed(userInput));
                        TrainSpeedEnergy trainSpeedEnergy = trainRepositoryService.getTheHighestEnergyConsumer(userInput);
                        System.out.println("Highest energy consumption and the speed details : ");
                        System.out.println("Consumes " + trainSpeedEnergy.getEnergy() + " KWH at the speed of " + trainSpeedEnergy.getSpeed() + " KMPH."); //String format
                    } else {
                        System.out.println("Reuqested Train id is invalid or not available!");
                    }
                }
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }

}
