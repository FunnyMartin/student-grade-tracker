package Logic;

public class Grade {
    private final int grade;
    private final int weight;

    public Grade(int grade, int weight) {
        this.grade = grade;
        this.weight = weight;
    }

    //region GET
    public int getGrade() {
        return grade;
    }

    public int getWeight() {
        return weight;
    }
    //endregion

    @Override
    public String toString() {
        return "|g=" + grade + ";w=" + weight + "|";
    }
}
