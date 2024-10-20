import java.io.IOException;

interface FileCopyStrategy {
    void copy(String pathIn, String pathOut) throws IOException;
}
