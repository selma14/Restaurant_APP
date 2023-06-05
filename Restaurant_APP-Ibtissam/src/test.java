import Model.MenuDAOImpl;
import Model.MenuDAOIntrf;
import Model.MenuItem;

import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws SQLException {
        String name;
        int id;
        System.out.println("Welcome to Restaurant management application");
        //MENU:
        MenuDAOIntrf DAO = new MenuDAOImpl();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Add Menu Item\n" +
                    "2. Show Menu\n" +
                    "3. Show Menu Item based on id \n" +
                    "4. Update the Menu Item\n" +
                    "5. Delete the Menu Item\n");

            System.out.println("Enter Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    MenuItem Item = new MenuItem();
                    System.out.println("Enter ID : ");
                    id = sc.nextInt();
                    System.out.println("Enter name ");
                    name = sc.next();
                    System.out.println("Enter Category ");
                    String category = sc.next();
                    System.out.println("Enter Price");
                    double price = sc.nextDouble();
                    Item.setItemId(id);
                    Item.setItemName(name);
                    Item.setItemCategory(category);
                    Item.setItemPrice(price);
                    DAO.createItem(Item);
                    break;
                case 2:
                    DAO.showMenu();
                    break;
                case 3:
                    System.out.println("Enter id to show the details ");
                    int itemid = sc.nextInt();
                    DAO.showItemBasedOnID(itemid);
                    break;
                case 4:
                    System.out.println("Enter id to update the details");
                    int itemidu = sc.nextInt();
                    System.out.println("Enter the new name");
                    name = sc.next();
                    DAO.updateMenuItem(itemidu,name);
                    break;
                case 5:
                    System.out.println("Enter the id to delete");
                    id = sc.nextInt();
                    DAO.deleteMenuItem(id);
                    break;

                case 6:
                    System.out.println("Thank you for using our Application !!!");
                    System.exit(0);
                default:
                    System.out.println("Enter valid choice !");
                    break;
            }
        }while (true);
    }
}