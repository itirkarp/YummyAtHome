package setupschema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Prakriti
 */
public class SetupSchema {
    public static enum Category {STARTERS, MAINS, RICE, DRINKS, BREADS};
    
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement prepStatement = null;

        try {
            System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost/sun-appserv-samples", "app", "app");
            statement = connection.createStatement();

            DB db = new DB(connection, statement);
            
            // Drop and create all tables
            // Comment out drop statements if running for the first time
            db.dropOrderItemTable();
            db.dropOrderTable();
            db.dropMenuItemTable();
            db.dropUserTable();
            
            db.createUserTable();
            db.createMenuItemTable();
            db.createOrderTable();
            db.createOrderItemTable();
            
            // Create menu
            db.createMenuItem("Tandoori Chicken", "Grilled chicken with spices served with onion salad", 20, Category.STARTERS.toString());
            db.createMenuItem("Seekh Kebab", "Minced mutton skewers", 15, Category.STARTERS.toString());            
            db.createMenuItem("Butter Chicken", "Chicken curry dish with butter and spices", 10, Category.MAINS.toString());
            db.createMenuItem("Kadhai Chicken", "Spicy chicken with onions and capsicum", 10, Category.MAINS.toString());
            db.createMenuItem("Mutton Dum Biryani", "Slow cooked chicken and rice with exotic Indian spices served with dahi", 20, Category.RICE.toString());
            db.createMenuItem("Jeera Rice", "Rice with cumin and other spices", 10, Category.RICE.toString());
            db.createMenuItem("Jaljeera", "Refreshing drink", 5, Category.DRINKS.toString());
            db.createMenuItem("Lassi", "Sweet refreshing drink", 5, Category.DRINKS.toString());
            db.createMenuItem("Garlic Naan", "Bread prepared in a tandoor with garlic and butter", 3, Category.BREADS.toString());
            db.createMenuItem("Tandoori Roti", "Flat Bread roasted in a tandoor", 3, Category.BREADS.toString());
            
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (prepStatement != null) {
                try {
                    prepStatement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
