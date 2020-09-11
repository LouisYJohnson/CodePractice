package exams.bigo;

public class test1 {
    public int numOfMethods(int steps) {
        if (steps == 1 || steps == 2) {
            return steps;
        }

        return process(steps);
    }

    private int process(int steps) {
        if (steps == 1 || steps == 2) {
            return steps;
        }

        return process(steps - 1) + process(steps - 2);

    }

}
