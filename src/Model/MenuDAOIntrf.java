package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MenuDAOIntrf {
    //create Item
    public void createItem(MenuItem item) throws SQLException;
    //show Menu
    public ResultSet showMenu() throws SQLException;
    //update Item
    public void updateMenuItem(int id, String name,String category,double price);
    //delete Item
    public void deleteMenuItem(int id);
}
