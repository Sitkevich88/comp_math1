package main;

import solution.Gaussian;
import solution.Solver;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Solver solver = new Gaussian();
        new Application(solver).run(args);
    }

}
