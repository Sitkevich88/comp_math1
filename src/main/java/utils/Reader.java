package utils;

public interface Reader {
    public int readN() throws RuntimeException;
    public double[][] readMatrix() throws RuntimeException;
    public void close();
}
