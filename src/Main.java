import java.io.*;
import java.util.Scanner;
import java.nio.channels.*;
import java.nio.file.*;

public class Main {
    static Scanner s = new Scanner(System.in);
    static String pathIn = "";
    static String pathOut = "";

    public static Boolean checking() {
        System.out.println("Перезаписать? Напишите да/нет");
        return s.nextLine().equalsIgnoreCase("да");
    }

    public static boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Выберите способ копирования файла");
            System.out.println("1 - пакет io");
            System.out.println("2 - пакет nio");
            System.out.println("3 - пакет nio2");
            int num = s.nextInt();
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

            FileCopyStrategy copyStrategy;
            switch (num) {
                case 1: copyStrategy = new IoCopyStrategy(); break;
                case 2: copyStrategy = new NioCopyStrategy(); break;
                case 3: copyStrategy = new Nio2CopyStrategy(); break;
                default: continue;
            }
            copyStrategy.copy(pathIn, pathOut);
        }
    }
}
// C:\Users\fedye\OneDrive\Рабочий стол\input.txt