package Logic;

import java.io.*;
import java.util.ArrayList;


import java.util.Arrays;

public class Manager {
    private ArrayList<Subject> subjectArray;

    public Manager(ArrayList<Subject> subjectArray) {
        this.subjectArray = subjectArray;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "subjectArray=" + subjectArray +
                '}';
    }

    /**
     * Adds subject into subjectArray if it isn't already present
     *
     * @param subjectName is users input for the subject name
     */
    public void addSubject(String subjectName) {
        subjectName = subjectName.toLowerCase();
        boolean isPresent = false;
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.getName())) {
                isPresent = true;
                break;
            }
        }

        if (!isPresent && subjectName.matches("^[a-zA-Z]+$")) {
            subjectArray.add(new Subject(subjectName, new ArrayList<>()));
            System.out.println(subjectName + " was added");
        } else {
            System.out.println(subjectName + " is already present or contains forbidden characters");
        }
    }

    /**
     * Lists all available commands
     */
    public void help() {
        String[] commands = {
                "list",
                "list files",
                "subject add (name)",
                "subject remove (name)",
                "subject (name) mark add (grade) (weight)",
                "subject (name) mark remove (index)",
                "subject (name) graph",
                "",
                "create (file name)",
                "delete (file name)",
                "save (file name)",
                "load (file name)",
                "quit"
        };

        for (String command : commands) {
            System.out.println(command);
        }
    }

    /**
     * Lists all subjects in subjectArray with their grades, checks honors and shows marks
     */
    public void list() {
        if(subjectArray.size() > 0){
            String output;
            boolean hasHonors = true;
            int numberOf = 0;
            double grade = 0;
            for (Subject sub : subjectArray) {
                grade += sub.calculateMark();
                numberOf++;
                if (!sub.honors()) {
                    hasHonors = false;
                }
            }
            if (grade / numberOf > 1.5) {
                hasHonors = false;
            }
            if (hasHonors) {
                output = "You will get honors" + "\n";
            } else {
                output = "You won't get honors" + "\n";
            }

            for (Subject sub : subjectArray) {
                output = output + sub.getName() + "(" + sub.calculateMark() + ")" + sub.getGradeArray().toString() + "\n";
            }
            System.out.println(output);
        } else{
            System.out.println("There are no subjects");
        }
    }

    /**
     * Removes subject if it exists based on users input
     *
     * @param subjectName is users input
     */
    public void removeSubject(String subjectName) {
        subjectName = subjectName.toLowerCase();
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.getName())) {
                subjectArray.remove(sub);
                System.out.println(sub.getName() + " was removed");
                break;
            }
        }
    }

    /**
     * Adds a mark to a specific subject based on users input
     *
     * @param subjectName is the name of the subject
     * @param grade       is the grade
     * @param weight      is the weight
     */
    public void addMark(String subjectName, int grade, int weight) {
        if (grade > 0 && weight > 0) {
            subjectName = subjectName.toLowerCase();
            for (Subject sub : subjectArray) {
                if (subjectName.equals(sub.getName())) {
                    sub.getGradeArray().add(new Grade(grade, weight));
                    System.out.println("Mark was added");
                    break;
                }
            }
        } else {
            System.out.println("Grade and weight has to be a positive number");
        }
    }

    /**
     * Removes a mark from a specific subject based on users input
     *
     * @param subjectName is the name of the subject
     * @param index       is the position of the grade in the grade array of the subject
     */
    public void removeMark(String subjectName, int index) {
        subjectName = subjectName.toLowerCase();
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.getName())) {
                if (index < sub.getGradeArray().size() && index >= 0) {
                    sub.getGradeArray().remove(index);
                    System.out.println("Mark was removed");
                } else {
                    System.out.println("Index is out of bounds");
                }
                return;
            }
        }
        System.out.println("Subject not found");
    }

    /**
     * Searches for a subject in array of subjects and calls createGraph()
     *
     * @param subjectName is name of the subject
     */
    public void graph(String subjectName) {
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.getName())) {
                sub.createGraph();
                return;
            }
        }
        System.out.println("No such subject found");
    }

    /**
     * Saves current subjects and their grade arrays into a text file
     *
     * @param fileName is the name of the save file
     * @throws IOException because it's writing into a file
     */
    public void save(String fileName) throws IOException {
        File file = new File("src/main/java/SaveFiles/" + fileName + ".txt");
        if (file.exists() && !subjectArray.isEmpty()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Subject sub : subjectArray) {
                String line = "";
                line += sub.getName() + ",";
                for (Grade grade : sub.getGradeArray()) {
                    line += grade.getGrade() + "," + grade.getWeight() + ",";
                }
                bw.write(line);
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
        File file = new File("src/main/java/SaveFiles/" + fileName + ".txt");
        if (file.exists() && file.length() > 0) {
            subjectArray = new ArrayList<>();
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
                subjectArray.add(new Subject(subjectName, grades));
            }
            System.out.println("File loaded successfully");
        } else {
            System.out.println("No such file exists or there is nothing to load");
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
            System.out.println(Arrays.toString(files));
        } else {
            System.out.println("No files found");
        }
    }

    /**
     * Quits the application
     */
    public void quit() {
        System.exit(0);
    }
}