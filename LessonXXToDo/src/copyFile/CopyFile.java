package copyFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        File file = new File("LessonXXToDo/src/copyFile/file.txt");
        if (!(file.exists() && file.isFile())) throw new RuntimeException("its not a file!");
        WriteFile(file);
        readFile(file);
    }

    static void WriteFile(File file) throws IOException {
        List<String> input = Arrays.asList("Cat", "Dog", "Fox", "Wolf", null);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
          //  bw.write("Hello, world!");
            for (int i = 0; i < input.size(); i++) {
                if (!(input.get(i) == null)) bw.write(String.valueOf(input.get(i)) + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFile(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
                System.out.println(line);
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/*Чтение из файла:
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
Запись в файл:
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello, world!");
} catch (IOException e) {
    e.printStackTrace();
}

 */