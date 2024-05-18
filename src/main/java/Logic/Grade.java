package Logic;

public record Grade(int grade, int weight) {
    public Grade(int grade, int weight) {
        if (grade > 0 && grade < 6) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade must be between 1 and 5");
        }
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "|g=" + grade + ";w=" + weight + "|";
    }
}
