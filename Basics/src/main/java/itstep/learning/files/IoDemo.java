package itstep.learning.files;

import itstep.learning.ConsoleColors;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoDemo {
    public void run() {

        String name = "results.txt";
        Path path = Paths.get(name);

        if(Files.exists(path)) {

            try(InputStream inputStream = Files.newInputStream(path)) {

                StringBuilder sb = new StringBuilder();
                int code;

                while((code = inputStream.read()) != -1) {
                    sb.append((char) code);
                }

                System.out.println(ConsoleColors.YELLOW + "File content:" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + sb.toString() + ConsoleColors.RESET);
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        else {

            try(FileWriter writer = new FileWriter(name)){
                writer.write("Some results: \nLine 1\nLine 2");
                writer.flush();
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

            System.out.println(ConsoleColors.YELLOW + "File created" + ConsoleColors.RESET);
        }
    }
}
