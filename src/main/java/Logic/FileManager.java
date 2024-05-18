package Logic;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private final Manager manager;

    public FileManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Saves current subjects and their grade arrays into a text file
     *
     * @param fileName is the name of the save file
     * @throws IOException because it's writing into a file
     */
    public void save(String fileName) throws IOException {
        File file = new File("src/main/java/SaveFiles/" + fileName + ".txt");
        if (file.exists() && !manager.getSubjectArray().isEmpty()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Subject sub : manager.getSubjectArray()) {
                StringBuilder line = new StringBuilder();
                line.append(sub.name()).append(",");
                for (Grade grade : sub.gradeArray()) {
                    line.append(grade.grade()).append(",").append(grade.weight()).append(",");
                }
                bw.write(line.toString());
                bw.newLine();
            }
            System.out.println("File saved successfully");
            bw.flush();
        } else {
            System.out.println("No such file exists or there is nothing to save");
        }
    }

    /**
     * Loads subjects and their grades from a text file
     *
     * @param fileName is the name of the load file
     * @throws IOException because it's reading from a file
     */
    public void load(String fileName) throws IOException {
        try {
            File file = new File("src/main/java/SaveFiles/" + fileName + ".txt");
            if (file.exists() && file.length() > 0) {
                manager.setSubjectArray(new ArrayList<>());
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String subjectName = data[0];
                    ArrayList<Grade> grades = new ArrayList<>();
                    for (int i = 1; i < data.length; i++) {
                        grades.add(new Grade(Integer.parseInt(data[i]), Integer.parseInt(data[i + 1])));
                        i++;
                    }
                    manager.getSubjectArray().add(new Subject(subjectName, grades));
                }
                System.out.println("File loaded successfully");
            } else {
                System.out.println("No such file exists or there is nothing to load");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("File contains invalid grade value");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a text file in SaveFiles package if it doesn't already exist
     *
     * @param fileName is the name of the file
     * @throws IOException because it's creating a new file
     */
    public void createFile(String fileName) throws IOException {
        String filePath = "src/main/java/SaveFiles/" + fileName + ".txt";
        File file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("File " + fileName + " created");
        } else {
            System.out.println("File " + fileName + " already exists or could not be created");
        }
    }

    /**
     * Deletes a file if it is possible
     *
     * @param fileName is the name of the file
     */
    public void deleteFile(String fileName) {
        String filePath = "src/main/java/SaveFiles/" + fileName + ".txt";
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File " + fileName + " deleted");
        } else {
            System.out.println("File " + fileName + " could not be deleted");
        }
    }

    /**
     * Lists all files in the package SaveFiles
     */
    public void listFiles() {
        File directory = new File("src/main/java/SaveFiles");
        File[] files = directory.listFiles();
        if (files != null) {
            System.out.println("Listing " + files.length + " file(s)");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No files found");
        }
    }
}
