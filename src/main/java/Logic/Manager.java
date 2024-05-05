package Logic;

import java.io.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
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
     * Lists all subjects in subjectArray with their grades
     */
    public void list() {
        String output = "";
        for (Subject sub : subjectArray) {
            output = output + sub.getName() + sub.getGradeArray().toString() + "\n";
        }
        System.out.println(output);
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
     * Creates a graph using a library JFreeChart of specific subject's grades
     *
     * @param subjectName is the name of the subject
     */
    public void graph(String subjectName) {
        ArrayList<Grade> grades = null;
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.getName())) {
                grades = sub.getGradeArray();
            }
        }

        if (grades != null && !grades.isEmpty()) {
            XYSeries series = new XYSeries("Grades");

            double sum = 0.0;
            double totalWeight = 0.0;
            double minGrade = Double.MAX_VALUE;
            for (int i = 0; i < grades.size(); i++) {
                Grade grade = grades.get(i);
                sum += grade.getGrade() * grade.getWeight();
                totalWeight += grade.getWeight();
                series.add(i + 1, sum / totalWeight);
                minGrade = Math.min(minGrade, grade.getGrade());
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Grade Evolution",
                    "Exam",
                    "Grade Average",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(minGrade, range.getUpperBound());

            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
            plot.setOutlineVisible(false);
            plot.getRenderer().setSeriesPaint(0, new Color(0, 114, 187));

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            chartPanel.setMouseZoomable(false);

            JFrame frame = new JFrame();
            frame.getContentPane().add(chartPanel);
            frame.pack();
            frame.setVisible(true);
        } else {
            System.out.println("There is nothing to show in a graph");
        }
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
            System.out.println("Listing " + files.length + " files");
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