package Logic;

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
        return "Logic.Subject{" +
                "name='" + name + '\'' +
                ", gradeArray=" + gradeArray +
                '}';
    }

}
