import java.io.*;
import java.util.Scanner;
import java.nio.channels.*;
import java.nio.file.*;

public class Main {
    static Scanner s = new Scanner(System.in);
    static String pathIn = "";

    public static Boolean checking() {
        String check;
        System.out.println("Перезаписать? Напишите да/нет");
        check = s.nextLine();
        return check.equalsIgnoreCase("да");
    }

    public static String checkExcept() throws IOException {
        System.out.println("Введите путь файла");
        pathIn = s.nextLine();
        try {
            Path fis = Paths.get(
                    "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\input5.txt");
        }
        catch (Exception exception) {
            System.out.println("Файла не существует");
            return null;
        }
        return pathIn;
    }
    public static void ioCoping() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        if (!checking()) {
            return;
        }

        try {
            fis = new FileInputStream(
                    "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\input.txt");

            fos = new FileOutputStream(
                    "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\output.txt");

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, lengthRead);
                fos.flush();
            }
            System.out.println("Файл успешно скопирован");
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

    public static void nioCopingChanels() throws IOException {
        if (!checking()) {
            return;
        }
        FileChannel fis = new FileInputStream(
                "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\input.txt").getChannel();

        FileChannel fos = new FileOutputStream(
                "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\output2.txt").getChannel();

        fos.transferFrom(fis, 0, fis.size());
        System.out.println("Файл успешно скопирован");
        fis.close();
        fos.close();
    }

    public static void nio2Coping() throws IOException {
        if (!checking()) {
            return;
        }

        pathIn = checkExcept();
        if (pathIn == null) {
            return;
        }

        Path fis = Paths.get(pathIn);

        Path fos = Paths.get(
                "C:\\Users\\fedye\\OneDrive\\Рабочий стол\\output5.txt");
        Files.copy(fis, fos, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Файл скопирован");
    }

    public static void main(String[] args)
            throws IOException {
        //ioCoping();
        //nioCopingChanels();
        nio2Coping();
    }
}
// C:\Users\fedye\OneDrive\Рабочий стол\input.txt