package Logic;

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
import java.util.ArrayList;

public class Subject {
    private final String name;
    private final ArrayList<Grade> gradeArray;

    public Subject(String name, ArrayList<Grade> gradeArray) {
        this.name = name;
        this.gradeArray = gradeArray;
    }

    //region SET&GET
    public String getName() {
        return name;
    }

    public ArrayList<Grade> getGradeArray() {
        return gradeArray;
    }
    //endregion

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", gradeArray=" + gradeArray +
                '}';
    }

    /**
     * Takes in grades and weights from a subject, uses weighted average to calculate final mark, rounds off final mark to 2 decimal points
     *
     * @return final mark
     */
    public double calculateMark() {
        float value = 0, weight = 0;
        for (Grade grade : gradeArray) {
            value += grade.getGrade() * grade.getWeight();
            weight += grade.getWeight();
        }
        float mark = (value / weight);
        return Math.round(mark * 100.0) / 100.0;
    }

    /**
     * Creates a graph using a library JFreeChart of subject's grades
     */
    public void createGraph() {
        if (gradeArray != null && !gradeArray.isEmpty()) {
            XYSeries series = new XYSeries("Grades");

            double sum = 0.0;
            double totalWeight = 0.0;
            double minGrade = Double.MAX_VALUE;
            for (int i = 0; i < gradeArray.size(); i++) {
                Grade grade = gradeArray.get(i);
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
     * Takes final mark and rounds it .49 down and .5 up and returns information about honors
     *
     * @return if this subject has honors (1, 2 = yes; else = no)
     */
    public boolean honors() {
        double mark = calculateMark();
        double round = mark - (int) mark;

        if (round >= 0.5) {
            mark = mark - round;
            mark++;
        } else {
            mark = mark - round;
        }
        return !(mark > 2);
    }
}
