import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class Nio2CopyStrategy implements FileCopyStrategy {
    @Override
    public void copy(String pathIn, String pathOut) {
        try {
            Files.copy(Paths.get(pathIn), Paths.get(pathOut), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован (Nio2CopyStrategy)");
        } catch (IOException e) {
            System.out.println("Ошибка копирования файла: " + e.getMessage());
        }
    }
}
