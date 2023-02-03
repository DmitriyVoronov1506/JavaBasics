package itstep.learning.files;

import itstep.learning.ConsoleColors;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirDemo {
    public void run() {

        String path = "./";

        File dir = new File(path);

        if(dir.exists()) {
            System.out.println(path + " exists");
        }

        if(dir.isFile()) {
            System.out.println(path + " is file");
        }
        else if(dir.isDirectory()) {
            System.out.println(path + " is directory");
        }
        else {
            System.out.println(path + " is neither file nor directory");
        }

        // dir.list() - список файлов в папке (только имена)

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy hh:mm::ss");

        for(File file : dir.listFiles()){

            System.out.print(ConsoleColors.BLUE + (file.canRead() ? "r": "-") + (file.canWrite() ? "w": "-") + (file.canExecute() ? "x  ": "-  ") + ConsoleColors.RESET);

            System.out.print(ConsoleColors.GREEN +  format.format(new Date(file.lastModified())) + "   " + ConsoleColors.RESET);

            if(file.isFile()) {
                System.out.print(file.length() + "\t");
            }
            else {
                System.out.print("<DIR>\t");
            }

            System.out.println(file.getName());
        }
    }
}
