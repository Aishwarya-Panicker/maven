package net.gree.mavenexamples.integrationmysql;

public class CalculatorException extends Throwable {
    public CalculatorException(String message, Exception e) {
        super(message, e);
    }
}
