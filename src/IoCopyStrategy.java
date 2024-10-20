import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class IoCopyStrategy implements FileCopyStrategy {
    @Override
    public void copy(String pathIn, String pathOut) {
        try {
            FileInputStream fis = new FileInputStream(pathIn);
            FileOutputStream fos = new FileOutputStream(pathOut);
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, lengthRead);
                fos.flush();
            }
            System.out.println("Файл успешно скопирован (IoCopyStrategy)");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
