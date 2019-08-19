package pl.karnas.differentiator;


import java.util.Arrays;
import java.util.List;

enum FileType {

    GIF(Arrays.asList("47 49 46 38 37 61", "47 49 46 38 39 61"), 0, "gif"),
    JPG(Arrays.asList("ff d8 ff e0 00 10 4a 46 49 46"), 0, "jpg"),
    TXT(Arrays.asList("73 63 76 64 66 65 76", "4e 6f 74 20 65 6d 70 74 79 20", "54 65 78 74 20 64 6f 63 75 6d"), 0, "txt");

    private final List<String> hexSignatures;
    private final int offset;
    private final String extension;

    FileType(List<String> hexSignatures, int offset, String extension) {
        this.hexSignatures = hexSignatures;
        this.offset = offset;
        this.extension = extension;
    }

    public List<String> getHexSignatures() {
        return hexSignatures;
    }

    public int getOffset() {
        return offset;
    }

    public String getExtension() {
        return extension;
    }

}
