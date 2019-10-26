package biz.cogitare.cogitaretraindetails.application.service;

import biz.cogitare.cogitaretraindetails.infrastructure.FileReaderServiceImplementation;
import biz.cogitare.cogitaretraindetails.application.exceptions.FileReaderException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Sam
 */
public class FileReaderServiceTest {

    private final FileReaderService fileReaderService = new FileReaderServiceImplementation();
    private final String resourceURI = "TrainDetailsTest.txt";
    private final String header = "TrainType-Speed(KMPH)-Energy(KWH)";

    @Test
    public void testReadFileWithHeader() throws FileReaderException {
        List<String> fileContents = fileReaderService.readFile(resourceURI, header);
        assertThat(fileContents.size()).isEqualTo(11);
    }

    @Test
    public void testReadFileWithoutHeader() throws FileReaderException {
        List<String> fileContents = fileReaderService.readFile(resourceURI, "");
        assertThat(fileContents.size()).isEqualTo(12);
    }

    @Test
    public void testFileContents() throws FileReaderException {
        String firstRecord = "156A-10-50";
        String lastRecord = "201V-45-200";
        String randomRecord = "157P-40-125";

        List<String> fileContents = fileReaderService.readFile(resourceURI, header);
        assertThat(fileContents.get(0)).isEqualTo(firstRecord);
        assertThat(fileContents.get(fileContents.size() - 1)).isEqualTo(lastRecord);
        assertThat(fileContents.contains(randomRecord)).isEqualTo(true);
    }

    @Test(expected = FileReaderException.class)
    public void testInvalidResourceURI() throws FileReaderException {
        List<String> fileContents = fileReaderService.readFile("INVALID_URI", header);

    }
}
