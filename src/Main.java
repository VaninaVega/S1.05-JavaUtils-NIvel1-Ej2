
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a directory path as an argument.");
        } else {
            String directoryPath = args[0];
            listContent(new File(directoryPath), " ");
        }
    }

    public static void listContent(File directory, String tabulador) {
        tabulador += "   ";

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Specified directory does not exist or is invalid: " + directory.getAbsolutePath());
            return;
        }

        File[] content = directory.listFiles();

        if (content == null || content.length == 0) {
            System.out.println("Directory is empty: " + directory.getAbsolutePath());
        } else {
            // Ordenamos alfabéticamente por nombre
            Arrays.sort(content, Comparator.comparing(File::getName));

            // Formato de fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            for (File f : content) {
                String lastModified = dateFormat.format(f.lastModified());

                if (f.isDirectory()) {
                    System.out.println(tabulador + "[D] " + f.getName() + " (Last modified: " + lastModified + ")");
                    listContent(f, tabulador);  // Llamada recursiva para subdirectorios
                } else {
                    System.out.println(tabulador + "[F] " + f.getName() + " (Last modified: " + lastModified + ")");
                }
            }
        }
    }
}

