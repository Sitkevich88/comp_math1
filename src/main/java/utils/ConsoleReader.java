package utils;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleReader implements Reader{

    private Scanner scanner;
    private int n;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
        System.out.println("Выбран консольный режим");
    }

    @Override
    public int readN() throws RuntimeException{
        System.out.print("Введите n: ");
        n = Integer.parseInt(scanner.nextLine());
        System.out.println();
        if (n<=0){
            throw new RuntimeException("N должен быть больше 0");
        }
        return n;
    }

    @Override
    public double[][] readMatrix() throws RuntimeException{

        if (n<=0){
            throw new RuntimeException("N должен быть больше 0");
        }

        double[][] matrix = new double[n][n+1];
        System.out.printf("Введите матрицу размером %d на %d\n", n, n+1);
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            double[] row;
            do{
                row = Arrays.stream(line.split("\\s"))
                        .map(arg->arg.replace(',','.'))
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                if (row.length!=(n+1)){
                    System.out.printf("Введите %d-ую строку заново\nдолжно быть %d аргументов через пробел\n", i+1, n+1);
                }
            }while (row.length != (n+1));

            for (int j = 0; j < row.length; j++) {
                matrix[i][j] = row[j];
                //System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
        return matrix;
    }

    @Override
    public void close() {
        scanner.close();
    }

}
