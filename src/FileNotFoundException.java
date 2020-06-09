import java.io.IOException;

public class FileNotFoundException extends IOException /* TODO: fix signature */ {

    public FileNotFoundException(IOException e) {
        super(e.getMessage(), e.getCause());
    }

    public FileNotFoundException(String message, String path) {
        super(message + path);
    }
}
