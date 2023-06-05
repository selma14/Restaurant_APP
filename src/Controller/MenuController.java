package Controller;

import Model.MenuDAOImpl;
import Model.MenuDAOIntrf;
import Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private TableColumn<MenuItem, String> CategoryCol;

    @FXML
    private ComboBox<?> ItemCategory;

    @FXML
    private TableColumn<MenuItem, String> ItemIdCol;

    @FXML
    private TextField ItemName;

    @FXML
    private TextField ItemPrice;

    @FXML
    private TableView<MenuItem> MenuTable;

    @FXML
    private TableColumn<MenuItem, String> NameCol;

    @FXML
    private TableColumn<MenuItem, String> PriceCol;

    @FXML
    private ImageView Search;

    @FXML
    private Button addItem;

    @FXML
    private Button deleteItem;

    @FXML
    private Button editItem;

    @FXML
    private TextField itemId;

    @FXML
    private TextField SearchField;

    MenuDAOIntrf DAO = new MenuDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMenuTable();
        addCategoryList();
    }

    public void AddItem(){//clicking Add Item OnAction
        if(!itemId.getText().isEmpty()
                || !ItemName.getText().isEmpty()
                || ItemCategory.getSelectionModel().getSelectedItem() != null
                || !ItemPrice.getText().isEmpty()){

            int id = Integer.parseInt(itemId.getText());
            String name = ItemName.getText();
            String Category = (String) ItemCategory.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(ItemPrice.getText());

            DAO.createItem(new MenuItem(id,name,Category,price));

            showMenuTable();
            itemReset();
        }else {
            System.out.println("fill all blank fields !!");
        }
    }

    public ObservableList<MenuItem> MenuList(){//Creating Menu list to use it on the showMenuTable function
        ObservableList<MenuItem> listM = FXCollections.observableArrayList();
        try{
            ResultSet result = DAO.showMenu();
            while (result.next()){
                MenuItem item = new MenuItem(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4));
                listM.add(item);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return listM;
    }

    private ObservableList<MenuItem> MenuList;
    public void showMenuTable(){//to show the Menu Table
        MenuList = MenuList();

        ItemIdCol.setCellFactory(new PropertyValueFactory<>("Item ID"));
        NameCol.setCellFactory(new PropertyValueFactory<>("Name"));
        CategoryCol.setCellFactory(new PropertyValueFactory<>("Category"));
        PriceCol.setCellFactory(new PropertyValueFactory<>("Price"));

        MenuTable.setItems(MenuList);
    }

    public void SelectItem(){//when we select a row on the table it displays on the inputs
        MenuItem item = MenuTable.getSelectionModel().getSelectedItem();
        int index = MenuTable.getSelectionModel().getSelectedIndex();

        if(index-1 < -1){
            return;
        }

        itemId.setText(String.valueOf(item.getItemId()));
        ItemName.setText(item.getItemName());
        ItemPrice.setText(String.valueOf(item.getItemPrice()));

    }

    public void itemReset(){//to empty the inputs
        itemId.setText("");
        ItemName.setText("");
        ItemPrice.setText("");
    }

    private String[] CategoryList = {"Drinks","Snacks","Desserts","Veggies","steak"};

    public void addCategoryList(){//to display the categories on the comobox
        List<String> Clist = new ArrayList<>();
        for (String categ : CategoryList){
            Clist.add(categ);
        }

        ObservableList listCat = FXCollections.observableArrayList(Clist);
        ItemCategory.setItems(listCat);
    }

    public void Search(){
        FilteredList<MenuItem> filter = new FilteredList<>(MenuList, e -> true);
    }


}
