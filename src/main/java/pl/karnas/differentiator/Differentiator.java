package pl.karnas.differentiator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Differentiator {

    private static final String[] hexSymbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final int BITS_PER_HEX_DIGIT = 4;

    enum Msgs {
        MESSAGE_OK("--- File extension matches file content. ---", 0),
        FILE_NOT_FOUND("--- File not found: ", 404),
        EXTENSION_LIES("Extension is %s, while actually it's a %s.", 500),
        FILE_IS_EMPTY("--- File is empty ---", 200);

        private String msgText;
        private int errorCode;

        Msgs(String msgText, int errorCode) {
            this.msgText = msgText;
            this.errorCode = errorCode;
        }

        public String getMsgText() {
            return msgText;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }

    String checkFile(String filePath) {

        File givenFile = new File(filePath);
        if (!givenFile.exists()) {
            return Msgs.FILE_NOT_FOUND.getMsgText() + filePath;
        }

        if (givenFile.length() == 0) {
            return Msgs.FILE_IS_EMPTY.getMsgText();
        }

        FileType fileTypeByExtension = getFileTypeByExtension(givenFile);
        FileType fileTypeByContent = getFileTypeByContent(filePath);

        if (fileTypeByExtension.equals(fileTypeByContent)) {
            return Msgs.MESSAGE_OK.getMsgText();
        }
        return String.format(Msgs.EXTENSION_LIES.getMsgText(), fileTypeByExtension.getExtension(), fileTypeByContent.getExtension());
    }

    private FileType getFileTypeByContent(String givenFile) {
        String hexSignatureFromFile = getHexSignature(givenFile);
        for (FileType fileType : FileType.values()) {
            List<String> hesSignatures = fileType.getHexSignatures();
            for (String hexSignature : hesSignatures) {
                if (hexSignatureFromFile.startsWith(hexSignature)) {
                    return fileType;
                }
            }
        }
        throw new FileContentTypeNotRecognizedException(hexSignatureFromFile);
    }

    private String getHexSignature(String givenFile) {
        try {
            InputStream in = new FileInputStream(givenFile);
            //read only 10 bytes
            byte[] bytes = new byte[10];
            in.read(bytes);
            // there are 2 hex digits per byte
            StringBuilder hexBuilder = new StringBuilder(bytes.length * 2);
            // for each byte, convert it to hex and append it to the buffer
            for (byte b : bytes) {
                byte leftSymbol = (byte) ((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
                byte rightSymbol = (byte) (b & 0x0f);
                hexBuilder.append(hexSymbols[leftSymbol]).append(hexSymbols[rightSymbol]);
                hexBuilder.append(" ");
            }
            return hexBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private FileType getFileTypeByExtension(File givenFile) {
        String givenExtension = getFileExtension(givenFile);
        for (FileType fileType : FileType.values()) {
            if (fileType.getExtension().equalsIgnoreCase(givenExtension)) {
                return fileType;
            }
        }
        throw new FileExtensionNotRecognizedException(givenExtension);
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null;
        }
        return name.substring(lastIndexOf + 1);
    }
}
