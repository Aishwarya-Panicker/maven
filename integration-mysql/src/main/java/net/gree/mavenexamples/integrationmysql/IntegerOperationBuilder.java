package net.gree.mavenexamples.integrationmysql;

import java.sql.Connection;

public class IntegerOperationBuilder {
    private final Connection connection;

    public IntegerOperationBuilder() {
        this(null);
    }

    public IntegerOperationBuilder(final Connection connection) {
        this.connection = connection;
    }

    public IntegerOperationBuilder connection(final Connection connection) {
        return new IntegerOperationBuilder(connection);
    }

    public IntegerOperation add() throws IntegerOperationBuildException {
        validateOrThrow();

        return new Add(connection);
    }

    private void validateOrThrow() throws IntegerOperationBuildException {
        if (connection == null) {
            throw new IntegerOperationBuildException("A connection is required to build an integer operation, but it was null.");
        }
    }
}
