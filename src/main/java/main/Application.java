package main;

import solution.Solver;
import utils.ConsoleReader;
import utils.FileReader;
import utils.Reader;
import java.io.FileNotFoundException;

//вариант 11
//Метод Гаусса с выбором главного элемента по столбцам
public class Application {

    private final static String PROMPT = "Для запуска файла в режиме чтения с консоли запустите программу с флагом -c\n" +
            "Для запуска файла в режиме чтения с файла запустите программу с флагом -f и аргументом <Путь до файла>";

    private Reader reader;
    private Solver solver;

    public Application(Solver solver){
        this.solver = solver;
    }

    public void run(String[] args) throws FileNotFoundException {
        if (args.length==0){
            System.err.println(PROMPT);
            return;
        }
        switch (args[0]){
            case "-c":
                reader = new ConsoleReader();
                break;
            case "-f":
                try{
                    reader = new FileReader(args[1]);
                }catch (FileNotFoundException e){
                    throw new FileNotFoundException("Файл не найден");
                }
                break;
            default:
                System.err.println(PROMPT);
                break;
        }

        if (reader!=null){
            reader.readN();
            double[][] matrix = reader.readMatrix();
            reader.close();
            solver.solve(matrix);
        }
    }
}
