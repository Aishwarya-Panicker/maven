package net.gree.mavenexamples.integrationmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add implements IntegerOperation {

    private final Connection connection;

    public Add(final Connection connection) {
        this.connection = connection;
    }

    public int apply(int a, int b) throws CalculatorException {
        int result = a + b;
        try {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO additions (a, b) VALUES (?, ?)");
            statement.setInt(1, a);
            statement.setInt(2, b);
            statement.executeUpdate();
        } catch (SQLException e) {
            final String message = String.format("Error while executing add(%d, %d)", a, b);
            throw new CalculatorException(message, e);
        }
        return result;
    }
}
