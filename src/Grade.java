public class Grade {
    private int grade, weight;

    public Grade(int grade, int weight) {
        this.grade = grade;
        this.weight = weight;
    }

    //region SET&GET
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    //endregion

    @Override
    public String toString() {
        return "|" + grade + "-" + weight + "|";
    }
}
