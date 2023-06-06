package Controller;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import Model.MenuDAOImpl;
import Model.MenuDAOIntrf;
import Model.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<MenuItem, String> CategoryCol;

    @FXML
    private ComboBox<String> ItemCategory;

    @FXML
    private TableColumn<MenuItem, Integer> ItemIdCol;

    @FXML
    private TextField ItemName;

    @FXML
    private TextField ItemPrice;

    @FXML
    private TableView<MenuItem> MenuTable;

    @FXML
    private TableColumn<MenuItem, String> NameCol;

    @FXML
    private TableColumn<MenuItem, Double> PriceCol;

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
        Alert alert;
        if(!itemId.getText().isEmpty()
                || !ItemName.getText().isEmpty()
                || ItemCategory.getSelectionModel().getSelectedItem() != null
                || !ItemPrice.getText().isEmpty()){

            int id = Integer.parseInt(itemId.getText());
            String name = ItemName.getText();
            String Category = (String) ItemCategory.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(ItemPrice.getText());

            try {
                DAO.createItem(new MenuItem(id,name,Category,price));
                /* alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText(ItemName.getText()+" Was Added Successfully!");
                alert.showAndWait();*/
                showMenuTable();
                itemReset();
            } catch (SQLException e) {
                System.out.println("Duplicate entry found for primary key: " + e.getMessage());
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Duplicated ID !");
                alert.showAndWait();
                itemReset();
            }
        }else {
            System.out.println("fill all blank fields !!");
        }
    }

    public void UpdateItem(){
        if(!itemId.getText().isEmpty()
                || !ItemName.getText().isEmpty()
                || ItemCategory.getSelectionModel().getSelectedItem() != null
                || !ItemPrice.getText().isEmpty()){

            int id = Integer.parseInt(itemId.getText());
            String name = ItemName.getText();
            String Category = ItemCategory.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(ItemPrice.getText());

            DAO.updateMenuItem(id,name,Category,price);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText(ItemName.getText()+" Was Updated Successfully!");
            alert.showAndWait();
            showMenuTable();
            itemReset();
        }else {
            System.out.println("fill all blank fields !!");
            return;
        }
    }

    public void DeleteItem(){
        Alert alert;
        if(!itemId.getText().isEmpty()
                || !ItemName.getText().isEmpty()
                || ItemCategory.getSelectionModel().getSelectedItem() != null
                || !ItemPrice.getText().isEmpty()){
            int id = Integer.parseInt(itemId.getText());

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cofirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Item ID: " + id + "?");
            // Get the default button types
            ButtonType buttonTypeYes = ButtonType.YES;
            ButtonType buttonTypeNo = ButtonType.NO;

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == buttonTypeYes){
                DAO.deleteMenuItem(id);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText(ItemName.getText()+" Was Deleted Successfully!");
                alert.showAndWait();
                showMenuTable();
                itemReset();
            }
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

        ItemIdCol.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<>("ItemCategory"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));

        MenuTable.setItems(MenuList);
        System.out.println("MenuTable Updated");
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

    private final String[] CategoryList = {"Appetizers","Salads","Entrées","Side Items","Beverages","Soups","Desserts"};

    public void addCategoryList(){//to display the categories on the comobox
        List<String> Clist = new ArrayList<>(Arrays.asList(CategoryList));

        ObservableList<String> listCat = FXCollections.observableArrayList(Clist);
        ItemCategory.setItems(listCat);
    }

    public void Search(){//Search bar
        FilteredList<MenuItem> filter = new FilteredList<>(MenuList, e -> true);
        SearchField.textProperty().addListener((observable, oldValue, newValue)->{
            filter.setPredicate(item ->{

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (String.valueOf(item.getItemPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(item.getItemId()).contains(lowerCaseFilter)) {
                    return true;
                } else if (item.getItemCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else {
                    return false;
                }

            });
        });
        SortedList<MenuItem> sortedtable = new SortedList<>(filter);
        sortedtable.comparatorProperty().bind(MenuTable.comparatorProperty());
        MenuTable.setItems(sortedtable);
    }

    public void SwitchToStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/employee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../Styles/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        ButtonType buttonTypeYes = ButtonType.YES;
        ButtonType buttonTypeNo = ButtonType.NO;
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == buttonTypeYes){
            root = FXMLLoader.load(getClass().getResource("../View/employee.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("../Styles/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }else {
            return;
        }

    }
    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("../Styles/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }




}
