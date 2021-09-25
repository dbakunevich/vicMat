package personal.bakunevich.second;

import java.util.Random;
import java.util.Scanner;

class Func {
    private final double l;
    private final double h;
    private final double e;
    private final double[] roots;
    private final Random random;
    private int count;

    Func(double l, double h, double e) {
        this.l = l;
        this.h = h;
        this.e = e;
        random = new Random(12345L);
        roots = new double[2];
        count = 1;
    }

    public double funcX(double x) {
        return h * x - l * Math.sin(x);
    }

    public double derivative(double x) {
        return h - l * Math.cos(x);
    }

    public void count() {
        double prevX = 1;
        double currX = prevX -funcX(prevX) / derivative(prevX);
        while (Math.abs(currX - prevX) > e || Math.abs(currX - prevX) == 0){
            if (Math.abs(currX - prevX) <= e) {
                currX = random.nextDouble() * count;
                count++;
            }
            prevX = currX;
            currX = prevX -funcX(prevX) / derivative(prevX);
        }
        roots[0] = currX;
        prevX = -0.1e-10;
        currX = prevX - funcX(prevX) / derivative(prevX);
        while (Math.abs(currX - prevX) > e || -1 > currX || 0 < currX) {
            if (Math.abs(currX - prevX) > e)
                currX = -random.nextDouble();
            prevX = currX;
            currX = prevX - funcX(prevX) / derivative(prevX);
        }
        roots[1] = currX;

    }

    public void print() {
        System.out.println("first root is: 0");
        for (var root: roots)
            System.out.println(root);

    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double l = scanner.nextDouble();
        double h = scanner.nextDouble();
        double e = scanner.nextDouble();

        if (l == 0 && h == 0) {
            System.out.println("Inf roots");
        }
        else if (l == 0 ||
                (l < 0 && h < 0 && h <= l) ||
                (l > 0 && h > 0 && h >= l)) {
            System.out.println("root is: 0");
        }
        else if (h == 0) {
            System.out.println("k * pi, k is include in Z");
        }
        else {
            Func func = new Func(l, h, e);
            func.count();
            func.print();
        }
    }
}
