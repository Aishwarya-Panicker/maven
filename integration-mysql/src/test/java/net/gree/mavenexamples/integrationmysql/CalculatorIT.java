package net.gree.mavenexamples.integrationmysql;

import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;

public class CalculatorIT {

    @Test
    public void testAdd() throws ClassNotFoundException, SQLException, CalculatorException, IntegerOperationBuildException {
        String mysqlPort = System.getProperty("mysql.port");

        Class.forName("com.mysql.jdbc.Driver");

        Connection connect = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:" + mysqlPort + "/test?user=root&password=root");
        connect.setAutoCommit(false);

        Statement setupStatement = connect.createStatement();
        setupStatement.execute("CREATE TABLE additions (a int not null, b int not null)");
        setupStatement.close();

        IntegerOperation add = new IntegerOperationBuilder().connection(connect).add();

        assertEquals(3, add.apply(1, 2));

        Statement statement = connect.createStatement();

        ResultSet countResultSet = statement.executeQuery("select count(*) from additions");

        countResultSet.next();

        assertEquals(1, countResultSet.getInt(1));

        ResultSet selectionResultSet = statement.executeQuery("select * from additions");

        selectionResultSet.next();

        int a = selectionResultSet.getInt(1);
        int b = selectionResultSet.getInt(2);

        assertEquals(1, a);
        assertEquals(2, b);

        connect.commit();
        connect.close();
    }
}
