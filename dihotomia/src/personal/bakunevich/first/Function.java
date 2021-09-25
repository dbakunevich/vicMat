package personal.bakunevich.first;

import java.util.ArrayList;
import java.util.Scanner;

public class Function {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private double[] rootsDerivative;
    private final ArrayList<Double> rootsFunction;
    private double shift;
    private int shiftPow = 1;
    private int countMore = 1;
    private int countLess = 1;


    Function(Double a, Double b, Double c, Double d, Double e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.shift = this.e / 10;
        rootsFunction = new ArrayList<>();
    }

    Function() {
        System.out.println("""
                            Please write this nums: a, b, c, d and e
                            In this func a*x^3+b*x^2+c*x+d=0 and a > 0
                            e is area of value root
                            """);

        Scanner scanner = new Scanner(System.in);
        this.a = scanner.nextDouble();
        this.b = scanner.nextDouble();
        this.c = scanner.nextDouble();
        this.d = scanner.nextDouble();
        this.e = scanner.nextDouble();
        this.shift = this.e / 10;
        rootsFunction = new ArrayList<>();

    }

    public void count() {
        Derivative derivative = new Derivative(3 * a, 2 * b, c);
        rootsDerivative = derivative.getRoots();
        findRoots();

    }

    private void findRoots(){
        if (rootsDerivative.length == 0)
            findRootSimple(0);
        else if (rootsDerivative.length == 2)
            findRootHard();
    }

    private void findRootHard() {
        double min = Math.min(rootsDerivative[0], rootsDerivative[1]);
        double max = Math.max(rootsDerivative[0], rootsDerivative[1]);
        if (findFuncResult(max) < -e &&
            findFuncResult(min) < -e)
            findRootSimple(min);
        else if (findFuncResult(max) > e &&
                findFuncResult(min) > e)
            findRootSimple(max);
        else if (Math.abs(findFuncResult(min)) < e &&
                Math.abs(findFuncResult(max)) < e) {
            rootsFunction.add(min);
            rootsFunction.add(max);
        }
        else if (Math.abs(findFuncResult(max)) < e &&
                findFuncResult(min) < -e){
            rootsFunction.add(max);
            findRootSimple(min);
        }
        else if (Math.abs(findFuncResult(min)) < e &&
                findFuncResult(max) > e){
            rootsFunction.add(min);
            findRootSimple(max);
        }
        else {
            findRootSimple(min);
            findThirdRoot((min + max) / 2);
            findRootSimple(max);
        }
    }

    private void findRootSimple(double startPoint) {
        double value = findFuncResult(startPoint);
        while (Math.abs(value) > e) {
            if (value < -e) {
                shift = Math.abs(shift);
                countLess++;
            }
            else {
                shift = -Math.abs(shift);
                countMore++;
            }
            shiftPow = Math.min(countMore, countLess);
            startPoint += shift / shiftPow;
            value = findFuncResult(startPoint);
        }
        rootsFunction.add(startPoint);
    }

    private void findThirdRoot(double startPoint){
        double value = findFuncResult(startPoint);
        while (Math.abs(value) > e) {
            if (value < -e) {
                shift = -Math.abs(shift);
                countMore++;
            }
            else {
                shift = Math.abs(shift);
                countLess++;
            }
            shiftPow = Math.min(countMore, countLess);
            startPoint += shift / shiftPow;
            value = findFuncResult(startPoint);
        }
        rootsFunction.add(startPoint);

    }

    private double findFuncResult(double root) {
        return a * Math.pow(root, 3) + b * Math.pow(root, 2)
                + c * root + d;
    }

    public void printResults() {
        for (var x: rootsFunction) {
            System.out.printf("root is: %f \ne is: %f\n", x, e);
        }
    }
}
