import java.util.Arrays;

public class FileFormatException extends Exception /* TODO: fix signature */ {

    public FileFormatException(int i) {
        super("does not have required format in line " + i);
    }
}
