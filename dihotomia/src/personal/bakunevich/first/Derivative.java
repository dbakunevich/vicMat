package personal.bakunevich.first;


public record Derivative(double a, double b, double c) {

    public double[] getRoots() {
        double value = Math.pow(b, 2) - 4 * a * c;
        if (value <= 0)
            return new double[0];
        else {
            double[] roots = new double[2];
            roots[0] = (-b - Math.sqrt(value)) / (2 * a);
            roots[1] = (-b + Math.sqrt(value)) / (2 * a);
            return roots;
        }
    }

}
