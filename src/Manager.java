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
        System.out.println("subject add x");
        System.out.println("list");
    }

    public void list(){
        String output = "";
        for (Subject var : subjectArray) {
            output = output + var.getName() + "\n";
        }
        System.out.println(output);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "subjectArray=" + subjectArray +
                '}';
    }
}
