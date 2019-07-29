package pl.karnas.differentiator;

import org.junit.Test;
import pl.karnas.differentiator.exception.FileContentTypeNotRecognizedRuntimeException;
import pl.karnas.differentiator.exception.FileExtensionNotRecognizedRuntimeException;

import static org.junit.Assert.assertEquals;
import static pl.karnas.differentiator.Differentiator.MESSAGE_EXTENSION_LIES;

public class DifferentiatorTest {

    private Differentiator differentiator = new Differentiator();

    @Test(expected = FileExtensionNotRecognizedRuntimeException.class)
    public void shouldThrowNotKnownExtensionRuntimeException() {
        differentiator.checkFile("src/test/resources/file/notknown.notknown");
    }

    @Test(expected = FileContentTypeNotRecognizedRuntimeException.class)
    public void shouldThrowNotKnownContentRuntimeException() {
        differentiator.checkFile("src/test/resources/file/notagif.gif");
    }

    @Test
    public void shouldReturnNotFoundMessage() {
        String filePath = "/notknown.notknown";
        assertEquals(Differentiator.MESSAGE_FILE_NOT_FOUND + filePath, differentiator.checkFile(filePath));
    }

    @Test
    public void shouldReturnFileCorrectMessage() {
        assertEquals(Differentiator.MESSAGE_OK, differentiator.checkFile("src/test/resources/file/calculator.gif"));
    }

    @Test
    public void shouldReturnLieMessage() {
        assertEquals(String.format(MESSAGE_EXTENSION_LIES, FileType.TXT.getExtension(), FileType.GIF.getExtension()),
                differentiator.checkFile("src/test/resources/file/gifwithtxtextension.txt"));
    }
}