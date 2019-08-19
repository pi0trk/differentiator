package pl.karnas.differentiator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.karnas.differentiator.Differentiator.Msgs.EXTENSION_LIES;
import static pl.karnas.differentiator.Differentiator.Msgs.FILE_IS_EMPTY;
import static pl.karnas.differentiator.Differentiator.Msgs.FILE_NOT_FOUND;
import static pl.karnas.differentiator.Differentiator.Msgs.MESSAGE_OK;

public class DifferentiatorTest {

    private Differentiator differentiator = new Differentiator();

    @Test(expected = FileExtensionNotRecognizedException.class)
    public void shouldThrowFileExtensionNotRecognizedException() {
        differentiator.checkFile("src/test/resources/file/notknown.notknown");
    }

    @Test(expected = FileContentTypeNotRecognizedException.class)
    public void shouldThrowFileContentTypeNotRecognizedException() {
        differentiator.checkFile("src/test/resources/file/notagif.gif");
    }

    @Test
    public void shouldReturnFileIsEmptyMessage() {
        String filePath = "src/test/resources/file/empty.extension";
        assertEquals(FILE_IS_EMPTY + filePath, differentiator.checkFile(filePath));
    }

    @Test
    public void shouldReturnFileNotFoundMessage() {
        String filePath = "/notknown.notknown";
        assertEquals(FILE_NOT_FOUND + filePath, differentiator.checkFile(filePath));
    }

    @Test
    public void shouldReturnFileExtensionIsCorrectMessage() {
        assertEquals(MESSAGE_OK.toString(), differentiator.checkFile("src/test/resources/file/calculator.gif"));
    }

    @Test
    public void shouldReturnFileExtensionLiesMessage() {
        assertEquals(String.format(EXTENSION_LIES.toString(), FileType.TXT.getExtension(), FileType.GIF.getExtension()),
                differentiator.checkFile("src/test/resources/file/gifwithtxtextension.txt"));
    }
}