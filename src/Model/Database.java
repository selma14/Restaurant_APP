package Model;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Database {
    private Connection conn = null;

    public Database() {
        String user = "root";
        String password = "";
        String database = "restaurant_db";
        int port = 3306; //default port, change it depending on your setup
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + database, user, password);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void executeQueryNoReturn(String query) throws SQLException {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            System.out.println(" checking db executing");
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }


    }

    public void executeQueryWithReturn(String query) throws SQLException {


        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            // loop through the result set
            while (rs.next()) {
                /*System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name")  + "\t" +
                        rs.getString("email"));*/

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public boolean checkUser(String userName, String pass) throws SQLException {


        PreparedStatement statement = null;
        try {

            String query = "SELECT username,password from admin where username=? and password=?";
            statement = this.conn.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();

        } finally {
            if (null != statement) {
                statement.close();
            }
            if (null != this.conn) {
                this.conn.close();
            }


        }
        return false;
    }
}

