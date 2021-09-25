package personal.bakunevich.first;

import java.util.Random;
import java.util.Scanner;

class Func {
    private final double a;
    private final double e;
    private final double[] roots;
    private final Random random;

    Func(double a, double e) {
        this.a = a;
        this.e = e;
        random = new Random(12345L);
        roots = new double[2];
    }

    public double funcX(double x) {
        if (x <= -1)
            return -Double.MAX_VALUE;
        return x - a - Math.log(1 + x);
    }

    public double derivative(double x) {
        double value = 1 - 1 / (1 + x);
        if (value == 0 && x < 0)
            return -0.1e-10;
        else if (value == 0 && x >= 0)
            return 0.1e-10;
        else return value;
    }

    public void count() {
        double prevX = 0.1e-10;
        double currX = prevX -funcX(prevX) / derivative(prevX);
        while (Math.abs(currX - prevX) > e){
            prevX = currX;
            currX = prevX -funcX(prevX) / derivative(prevX);
        }
        roots[0] = currX;
        prevX = -0.1e-10;
        currX = prevX - funcX(prevX) / derivative(prevX);
        while (Math.abs(currX - prevX) > e || -1 > currX || 0 < currX) {
            if (Math.abs(currX - prevX) <= e)
                currX = -random.nextDouble();
            prevX = currX;
            currX = prevX - funcX(prevX) / derivative(prevX);
        }
        roots[1] = currX;

    }

    public void print() {
        for (var root: roots)
            System.out.println("root is: " + root);

    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double e = scanner.nextDouble();

        if (a < 0) {
            System.out.println("Without roots");
        }
        else if (a == 0) {
            System.out.println("root is: 0");
        }
        else {
            Func func = new Func(a, e);
            func.count();
            func.print();
        }
    }
}
