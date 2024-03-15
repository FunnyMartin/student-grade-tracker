import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "Manager{" +
                "subjectArray=" + subjectArray +
                '}';
    }
}
