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
        return "Manager{" +
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
            System.out.println("Grade and weight has to be a positive number");
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
        System.out.println("Subject not found");
    }
}
