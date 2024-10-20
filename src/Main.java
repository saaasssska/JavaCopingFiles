import java.io.*;
import java.util.Scanner;
import java.nio.channels.*;
import java.nio.file.*;

public class Main {
    static Scanner s = new Scanner(System.in);
    static int num;
    static String pathIn = "";
    static String pathOut = "";

    public static Boolean checking() {
        String check;
        System.out.println("Перезаписать? Напишите да/нет");
        check = s.nextLine();
        return check.equalsIgnoreCase("да");
    }

    public static boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static void ioCoping(String pathIn, String pathOut) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(pathIn);

            fos = new FileOutputStream(pathOut);

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, lengthRead);
                fos.flush();
            }
            System.out.println("Файл успешно скопирован");
        }
        catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
    public static void nioCopingChanels(String pathIn, String pathOut) throws IOException {
        FileChannel fis = new FileInputStream(pathIn).getChannel();
        FileChannel fos = new FileOutputStream(pathOut).getChannel();

        try {
            fos.transferFrom(fis, 0, fis.size());
            System.out.println("Файл успешно скопирован");
        }
        catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        finally {
            fis.close();
            fos.close();
        }
    }

    public static void nio2Coping(String pathIn, String pathOut) throws IOException {
        try {
            Files.copy(Paths.get(pathIn), Paths.get(pathOut), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован");
        } catch (IOException e) {
            System.out.println("Ошибка копирования файла: " + e.getMessage());
        }
    }

    public static void main(String[] args)
            throws IOException {
        while (true) {
            System.out.println("Выберите способ копирования файла");
            System.out.println("1 - пакет io");
            System.out.println("2 - пакет nio");
            System.out.println("3 - пакет nio2");
            num = s.nextInt();
            s.nextLine();
            if (!(num == 1 || num == 2 || num == 3)) {
                break;
            }
            System.out.println("Введите путь файла");
            pathIn = s.nextLine();
            if (!fileExists(pathIn)) {
                System.out.println("Файл для копирования не существует: " + pathIn);
                return;
            }

            System.out.println("Введите путь файла, куда копировать");
            pathOut = s.nextLine();
            if (fileExists(pathOut)) {
                if (!checking()) {
                    continue;
                }
            }

            switch (num) {
                case 1: ioCoping(pathIn, pathOut); break;
                case 2: nioCopingChanels(pathIn, pathOut); break;
                case 3: nio2Coping(pathIn, pathOut); break;
            }
        }
    }
}
// C:\Users\fedye\OneDrive\Рабочий стол\input.txt