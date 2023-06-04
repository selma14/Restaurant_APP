package Model;

public interface MenuDAOIntrf {
    //create Item
    public void createItem(MenuItem item);
    //show Menu
    public void showMenu();
    //show item based on id
    public void showItemBasedOnID(int ItemId);
    //update Item
    public void updateMenuItem(int id,String name);
    //delete Item
    public void deleteMenuItem(int id);
}
