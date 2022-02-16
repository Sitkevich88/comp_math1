package solution;

//Метод Гаусса с выбором главного элемента по столбцам
public class Gaussian implements Solver{

    @Override
    public void solve(double[][] matrix){
        double[][] initialMatrix = matrix;
        System.out.println("Введенная матрица: ");
        printMatrix(matrix);

        matrix = toTriangle(matrix);
        System.out.println("Треугольная матрица: ");
        printMatrix(matrix);

        double determinant = determinateTriangle(matrix);
        System.out.println("Определитель равен: " + determinant);
        System.out.println();

        double[] xArray = getXArrayFromTriangleMatrix(matrix);
        System.out.println("Вектор неизвестных: ");
        printArray(xArray);

        System.out.println();
        double[] rArray = getRArray(initialMatrix, xArray);
        System.out.println("Вектор невязок: ");
        printArray(rArray);
    }

    private void printArray(double[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }

    private double[] getRArray(double[][] matrix, double[] xArray){
        double[] rArray = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < (matrix[i].length-1); j++) {
                sum+=matrix[i][j] * xArray[j];
            }
            rArray[i] = sum - matrix[i][matrix[i].length-1];
        }
        return rArray;
    }

    private double[] getXArrayFromTriangleMatrix(double[][] matrix){
        int len = matrix.length;
        double[] xArray = new double[len];
        for (int i = (len-1); i >= 0; i--) {
            double k = 1.0 / matrix[i][i];
            double x = matrix[i][matrix[i].length-1]*k;
            for (int j = (matrix[i].length-2); j > i; j--) {
                x-= matrix[i][j] * k * xArray[j];
            }
            xArray[i] = x;
        }
        return xArray;
    }

    private double determinateTriangle(double[][] matrix){
        double determinant = 1;
        for (int i = 0; i < matrix.length; i++) {
            determinant*=matrix[i][i];
        }
        return determinant;
    }

    private double[][] toTriangle(double[][] matrix){
        int rows = matrix.length;
        for (int i = 1; i < rows; i++) {
            matrix = changeRows(matrix, i);
            //printMatrix(matrix);
            matrix = subtractRow(matrix, i);
            //printMatrix(matrix);
        }
        return matrix;
    }

    private double[][] subtractRow(double[][] matrix, int rowNumberFromOne){
        int rowNumber = rowNumberFromOne - 1;
        for (int i = rowNumberFromOne; i < matrix.length; i++) {
            double k = - matrix[i][rowNumber] / matrix[rowNumber][rowNumber];
            for (int j = 0; j < matrix.length+1; j++) {
                matrix[i][j]+=matrix[rowNumber][j] * k;
            }
        }
        return matrix;
    }

    private double[][] changeRows(double[][] matrix, int columnNumberFromOne){
        int columnNumber = columnNumberFromOne - 1;
        int maxI = columnNumber;
        for (int i = columnNumber + 1; i < matrix.length; i++) {
            if(Math.abs(matrix[i][columnNumber]) > Math.abs(matrix[maxI][columnNumber])){
                maxI = i;
            }
        }
        if (maxI>columnNumber){
            for (int i = 0; i < matrix[columnNumber].length; i++) {
                double temp = matrix[columnNumber][i];
                matrix[columnNumber][i] = matrix[maxI][i];
                matrix[maxI][i] = temp;
            }
            System.out.println("Был преобразован стобец №" + maxI);
        }
        return matrix;
    }

    private void printMatrix(double[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
