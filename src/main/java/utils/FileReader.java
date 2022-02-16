package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader implements Reader {

    private Scanner scanner;
    private int n;

    public FileReader(String path) throws FileNotFoundException {
        scanner = new Scanner(new File(path));
        System.out.println("Выбран файловый режим");
    }

    @Override
    public int readN() throws RuntimeException{
        n = Integer.parseInt(scanner.nextLine());
        if (n<=0){
            throw new RuntimeException("N должен быть больше 0");
        }
        System.out.println("N прочитано");
        return n;
    }

    @Override
    public double[][] readMatrix() throws RuntimeException{

        if (n<=0){
            throw new RuntimeException("N должен быть больше 0");
        }

        double[][] matrix = new double[n][n+1];
        double[] row;
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            row = Arrays.stream(line.split("\\s"))
                    .map(arg->arg.replace(',','.'))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            if (row.length!=(n+1)){
                System.err.printf("В %d-ой строке %d аргументов, а ожидалось %d\n", i+1, row.length, n+1);
                throw new RuntimeException("Выполнение программы далее невозможно");
            }
            for (int j = 0; j < n+1; j++) {
                matrix[i][j] = row[j];
            }
        }
        System.out.println("Матрица прочитана\n");
        return matrix;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
