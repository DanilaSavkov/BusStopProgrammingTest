package file;

import java.io.Closeable;
import java.io.IOException;

public interface AutoClosable {
    default void autoClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
