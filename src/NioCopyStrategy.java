import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

class NioCopyStrategy implements FileCopyStrategy {
    @Override
    public void copy(String pathIn, String pathOut) {
        try {
            FileChannel fis = new FileInputStream(pathIn).getChannel();
            FileChannel fos = new FileOutputStream(pathOut).getChannel();
            fos.transferFrom(fis, 0, fis.size());
            System.out.println("Файл успешно скопирован (NioCopyStrategy)");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
