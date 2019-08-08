package pl.karnas.differentiator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.karnas.differentiator.Differentiator.Msgs.EXTENSION_LIES;
import static pl.karnas.differentiator.Differentiator.Msgs.FILE_NOT_FOUND;
import static pl.karnas.differentiator.Differentiator.Msgs.MESSAGE_OK;

public class DifferentiatorTest {

    private Differentiator differentiator = new Differentiator();

    @Test(expected = FileExtensionNotRecognizedException.class)
    public void shouldThrowNotKnownExtensionRuntimeException() {
        differentiator.checkFile("src/test/resources/file/notknown.notknown");
    }

    @Test(expected = FileContentTypeNotRecognizedException.class)
    public void shouldThrowNotKnownContentRuntimeException() {
        differentiator.checkFile("src/test/resources/file/notagif.gif");
    }

    @Test
    public void shouldReturnNotFoundMessage() {
        String filePath = "/notknown.notknown";
        assertEquals(FILE_NOT_FOUND.getMsgText() + filePath, differentiator.checkFile(filePath));
    }

    @Test
    public void shouldReturnFileCorrectMessage() {
        assertEquals(MESSAGE_OK.getMsgText(), differentiator.checkFile("src/test/resources/file/calculator.gif"));
    }

    @Test
    public void shouldReturnLieMessage() {
        assertEquals(String.format(EXTENSION_LIES.getMsgText(), FileType.TXT.getExtension(), FileType.GIF.getExtension()),
                differentiator.checkFile("src/test/resources/file/gifwithtxtextension.txt"));
    }
}