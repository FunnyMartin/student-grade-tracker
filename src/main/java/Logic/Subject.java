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

public record Subject(String name, ArrayList<Grade> gradeArray) {

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
            value += grade.grade() * grade.weight();
            weight += grade.weight();
        }
        float mark = (value / weight);
        return Math.round(mark * 100.0) / 100.0;
    }

    //THIS METHOD WAS CREATED USING CHATGPT
    /**
     * Creates a graph using a library JFreeChart of subject's grades
     */
    public void createGraph() {
        int bestGrade = 1, worstGrade = 5;
        if (gradeArray != null && gradeArray.size() > 1) {
            XYSeriesCollection dataset = getXySeriesCollection();

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
            range.setRange(bestGrade, worstGrade);

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

    //THIS METHOD WAS CREATED USING CHATGPT
    /**
     * Creates data for graph based on grades
     *
     * @return dataset for the graph
     */
    private XYSeriesCollection getXySeriesCollection() {
        XYSeries series = new XYSeries("Grades");

        double sum = 0.0;
        double totalWeight = 0.0;
        double minGrade = Double.MAX_VALUE;
        for (int i = 0; i < gradeArray.size(); i++) {
            Grade grade = gradeArray.get(i);
            sum += grade.grade() * grade.weight();
            totalWeight += grade.weight();
            series.add(i + 1, sum / totalWeight);
            minGrade = Math.min(minGrade, grade.grade());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
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

    /**
     * Takes in desired average for a specific subject and returns a needed grade to get closer to the average
     *
     * @param desiredAverage is users input
     * @return needed grade
     */
    public String calculateRequiredGrade(double desiredAverage) {
        int totalWeight = 0;
        int weightedSum = 0;

        for (Grade grade : gradeArray) {
            weightedSum += grade.grade() * grade.weight();
            totalWeight += grade.weight();
        }

        for (int newGrade = 1; newGrade <= 5; newGrade++) {
            for (int newWeight = 1; newWeight <= 10000; newWeight++) {
                int newWeightedSum = weightedSum + newGrade * newWeight;
                int newTotalWeight = totalWeight + newWeight;

                double newAverage = (double) newWeightedSum / newTotalWeight;

                if (Math.abs(newAverage - desiredAverage) < 1e-4) {
                    return "Grade: " + newGrade + " weight: " + newWeight;
                }
            }
        }

        return "Only 1 decimal point can be calculated OR the weight of grade would be above 10000";
    }
}