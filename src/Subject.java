import java.util.ArrayList;

public class Subject {
    private String name;
    private ArrayList<Grade> gradeArray;
    private float mark;

    public Subject(String name, ArrayList<Grade> gradeArray, float mark) {
        this.name = name;
        this.gradeArray = gradeArray;
        this.mark = mark;
    }

    //region SET&GET
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Grade> getGradeArray() {
        return gradeArray;
    }

    public void setGradeArray(ArrayList<Grade> gradeArray) {
        this.gradeArray = gradeArray;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    //endregion

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", gradeArray=" + gradeArray +
                ", mark=" + mark +
                '}';
    }

    private void calculateMark(){
        int value = 0, weight = 0;
        for (int i = 0; i < gradeArray.size(); i++) {
            value += gradeArray.get(i).getGrade() * gradeArray.get(i).getWeight();
            weight += gradeArray.get(i).getWeight();
        }
        mark = value/weight;
    }

}
