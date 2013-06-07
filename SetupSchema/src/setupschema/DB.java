package setupschema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Prakriti
 */
public class DB {
    
    private Connection connection;
    private Statement statement;

    public DB(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }
    
    public void dropUserTable() throws SQLException {
        statement.execute("drop table restaurantuser");
    }

    public void createUserTable() throws SQLException {
        statement.execute("CREATE TABLE restaurantuser (id INTEGER NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," 
                + "name CHAR(30), password CHAR(10), email char(30), phone char(10),"
                + " address char(50))");
    }
    
    public void dropMenuItemTable() throws SQLException {
        statement.execute("drop table menuitem");
    }

    public void createMenuItemTable() throws SQLException {
        statement.execute("CREATE TABLE menuitem (id INTEGER NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," 
                + "name CHAR(30), description CHAR(100), price float, category char(20))");
    }

    public void dropOrderTable() throws SQLException {
        statement.execute("drop table restaurantorder");
    }

    public void createOrderTable() throws SQLException {
        statement.execute("CREATE TABLE restaurantorder (id INTEGER NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," 
                + "userid integer references restaurantuser(id), " 
                + "orderdate date, total float)");
    }
    
    public void dropOrderItemTable() throws SQLException {
        statement.execute("drop table orderitem");
    }

    public void createOrderItemTable() throws SQLException {
        statement.execute("CREATE TABLE orderitem (id INTEGER NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," 
                + "orderid integer references restaurantorder(id), itemid integer references menuitem(id), quantity integer)");
    }

    public void createMenuItem(String name, String description, float price, String category) throws SQLException {
        PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO menuitem (name, description, price, category) VALUES (?, ?, ?, ?)");
        prepStatement.setString(1, name);
        prepStatement.setString(2, description);
        prepStatement.setFloat(3, price);
        prepStatement.setString(4, category);
        int rowCount = prepStatement.executeUpdate();
        if (rowCount == 0) {
          throw new SQLException("Could not insert menu item " + name);
        }        
    }

}
