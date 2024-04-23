package Logic;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;


public class Manager {
    private ArrayList<Subject> subjectArray;

    public Manager(ArrayList<Subject> subjectArray) {
        this.subjectArray = subjectArray;
    }

    public ArrayList<Subject> getSubjectArray() {
        return subjectArray;
    }

    public void setSubjectArray(ArrayList<Subject> subjectArray) {
        this.subjectArray = subjectArray;
    }

    public void addSubject(String subject){
        subject = subject.toLowerCase();
        boolean isPresent = false;
        for (Subject var: subjectArray) {
            if(subject.equals(var.getName())){
                isPresent = true;
            }
        }

        if(!isPresent && subject.matches("^[a-zA-Z]+$")){
            subjectArray.add(new Subject(subject, new ArrayList<>()));
            System.out.println(subject + " was added");
        } else{
            System.out.println(subject + " is already present or contains forbidden characters");
        }
    }

    public void help(){
        System.out.println("list");
        System.out.println("subject add (name)");
        System.out.println("subject remove (name)");
        System.out.println("subject (name) mark add (grade) (weight)");
        System.out.println("subject (name) mark remove (index)");
    }

    public void list(){
        String output = "";
        for (Subject var : subjectArray) {
            output = output + var.getName() + var.getGradeArray().toString() + "\n";
        }
        System.out.println(output);
    }

    @Override
    public String toString() {
        return "Logic.Manager{" +
                "subjectArray=" + subjectArray +
                '}';
    }

    public void removeSubject(String subjectName) {
        subjectName = subjectName.toLowerCase();
        for (Subject var : subjectArray) {
            if(subjectName.equals(var.getName())){
                subjectArray.remove(var);
                System.out.println(var.getName() + " was removed");
                break;
            }
        }
    }

    public void addMark(String subjectName, int grade, int weight){
        if(grade > 0 && weight > 0){
            subjectName = subjectName.toLowerCase();
            for (int i = 0; i < subjectArray.size(); i++){
                if(subjectName.equals(subjectArray.get(i).getName())){
                    subjectArray.get(i).getGradeArray().add(new Grade(grade, weight));
                    System.out.println("Mark was added");
                    break;
                }
            }
        } else {
            System.out.println("Logic.Grade and weight has to be a positive number");
        }
    }

    public void removeMark(String subjectName, int index){
        subjectName = subjectName.toLowerCase();
        for (int i = 0; i < subjectArray.size(); i++){
            if(subjectName.equals(subjectArray.get(i).getName())){
                if(index < subjectArray.get(i).getGradeArray().size() && index >= 0){
                    subjectArray.get(i).getGradeArray().remove(index);
                    System.out.println("Mark was removed");
                } else {
                    System.out.println("Index is out of bounds");
                }
                return;
            }
        }
        System.out.println("Logic.Subject not found");
    }

    public void graph(String subjectName){
        ArrayList<Grade> grades = null;
        for (Subject subject : subjectArray) {
            if (subjectName.equals(subject.getName())) {
                grades = subject.getGradeArray();
            }
        }

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
                "Grade History",
                "Exam",
                "Average Grade",
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
    }
}
