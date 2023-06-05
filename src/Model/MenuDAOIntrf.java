package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MenuDAOIntrf {
    //create Item
    public void createItem(MenuItem item);
    //show Menu
    public ResultSet showMenu() throws SQLException;
    //show item based on id
    public void showItemBasedOnID(int ItemId);
    //update Item
    public void updateMenuItem(int id,String name);
    //delete Item
    public void deleteMenuItem(int id);
}
