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

    public void createFileWithRandomStrings() {

        String name = "random-text.txt";
        Path path = Paths.get(name);

        if(Files.exists(path)) {

            try(FileReader fileReader = new FileReader(name)) {

                StringBuilder sb = new StringBuilder();
                int code;

                while((code = fileReader.read()) != -1) {
                    sb.append((char) code);
                }

                System.out.println(ConsoleColors.YELLOW + "File content:" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + sb.toString() + ConsoleColors.RESET);

                showFileTextInfo(sb);
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        else {

            int numberOfStrings = (int)(1 + Math.random() * 99);

            StringBuilder randomText = new StringBuilder();

            for(int i = 0; i <= numberOfStrings; i++)
            {
                int length = (int)(1 + Math.random() * 99);
                String randResult = getRandomString(length);

                randomText.append( (i == numberOfStrings) ? randResult : randResult + "\n" );
            }

            try(FileWriter writer = new FileWriter(name)){
                writer.write(randomText.toString());
                writer.flush();
            }
            catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

            System.out.println(ConsoleColors.YELLOW + "Random file was created" + ConsoleColors.RESET);
        }
    }

    private String getRandomString(int length) {

        StringBuilder randomStringBuilder = new StringBuilder();

        for(int i = 0; i < length; i++)
        {
            randomStringBuilder.append((char)('A'+ Math.random()*27));
        }

        return randomStringBuilder.toString();
    }

    private void showFileTextInfo(StringBuilder stringBuilder) {

        String[] textArray = stringBuilder.toString().split("\n");

        int maxIndex = 0;
        int maxLength = 0;

        for(int i = 1; i < textArray.length; i++)
        {
            if(textArray[maxIndex].length() < textArray[i].length()) {
                maxLength = textArray[i].length();
                maxIndex = i;
            }
        }

        System.out.println(ConsoleColors.YELLOW + "The Longest string: " + ConsoleColors.BLUE + textArray[maxIndex] + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Length: " + ConsoleColors.BLUE + maxLength + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Number: " + ConsoleColors.BLUE + maxIndex + ConsoleColors.RESET);
    }
}
