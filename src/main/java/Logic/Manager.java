package Logic;

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

    /**
     * Adds subject into subjectArray if it isn't already present
     *
     * @param subjectName is users input for the subject name
     */
    public void addSubject(String subjectName) {
        subjectName = subjectName.toLowerCase();
        boolean isPresent = false;
        for (Subject sub : subjectArray) {
            if (subjectName.equals(sub.name())) {
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
                "subject add (name)",
                "subject remove (name)",
                "subject (name) mark add (grade) (weight)",
                "subject (name) mark remove (index)",
                "subject (name) graph",
                "",
                "list files",
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
        if (!subjectArray.isEmpty()) {
            StringBuilder output;
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
                output = new StringBuilder("You will get honors" + "\n");
            } else {
                output = new StringBuilder("You won't get honors" + "\n");
            }

            for (Subject sub : subjectArray) {
                output.append(sub.name()).append("(").append(sub.calculateMark()).append(")").append(sub.gradeArray().toString()).append("\n");
            }
            System.out.println(output);
        } else {
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
            if (subjectName.equals(sub.name())) {
                subjectArray.remove(sub);
                System.out.println(sub.name() + " was removed");
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
        try {
            if (grade > 0 && weight > 0) {
                subjectName = subjectName.toLowerCase();
                for (Subject sub : subjectArray) {
                    if (subjectName.equals(sub.name())) {
                        sub.gradeArray().add(new Grade(grade, weight));
                        System.out.println("Mark was added");
                        break;
                    }
                }
            } else {
                System.out.println("Grade and weight has to be a positive number");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Grade has to be between 1 and 5");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            if (subjectName.equals(sub.name())) {
                if (index < sub.gradeArray().size() && index >= 0) {
                    sub.gradeArray().remove(index);
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
            if (subjectName.equals(sub.name())) {
                sub.createGraph();
                return;
            }
        }
        System.out.println("No such subject found");
    }

    /**
     * Quits the application
     */
    public void quit() {
        System.exit(0);
    }
}