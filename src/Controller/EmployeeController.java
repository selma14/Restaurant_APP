package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<?, ?> AddressCol;

    @FXML
    private TableColumn<?, ?> AgeCol;

    @FXML
    private TableColumn<?, ?> EmailCol;

    @FXML
    private TableView<Employee> EmployeeTable;

    @FXML
    private TableColumn<?, ?> FamilySituationCol;

    @FXML
    private TableColumn<?, ?> IdCol;

    @FXML
    private TableColumn<?, ?> NameCol;

    @FXML
    private TableColumn<?, ?> PhoneCol;

    @FXML
    private TableColumn<?, ?> RoleCol;

    @FXML
    private TableColumn<?, ?> SalaryCol;

    @FXML
    private ImageView Search;

    @FXML
    private TableColumn<?, ?> ShiftCol;

    @FXML
    private Button addEmployee;

    @FXML
    private Button dashboardbutton1;

    @FXML
    private Button dashboardbutton2;

    @FXML
    private Button dashboardbutton3;

    @FXML
    private Button dashboardbutton4;

    @FXML
    private Button deleteEmployee;

    @FXML
    private Button editEmployee;

    @FXML
    private TextField employeeAddress;

    @FXML
    private TextField employeeAge;

    @FXML
    private TextField employeeEmail;

    @FXML
    private TextField employeeFamilySituation;

    @FXML
    private TextField employeeId;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField employeePhone;

    @FXML
    private TextField employeeRole;

    @FXML
    private TextField employeeSalary;

    @FXML
    private TextField employeeShift;

    @FXML
    private Label logo;

    @FXML
    private TextField searchFieldEmployee;

    EmployeeDaoIntrf DAO = new EmployeeDaoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showEmployeeTable();

    }

    public void addEmployee() {
        if (!employeeId.getText().isEmpty()
                || !employeeName.getText().isEmpty()
                || !employeeRole.getText().isEmpty()
                || !employeeAge.getText().isEmpty()
                || !employeePhone.getText().isEmpty()
                || !employeeAddress.getText().isEmpty()
                || !employeeFamilySituation.getText().isEmpty()
                || !employeeShift.getText().isEmpty()
                || !employeeEmail.getText().isEmpty()
                || !employeeSalary.getText().isEmpty()) {
            int id = Integer.parseInt(employeeId.getText());
            String name = employeeName.getText();
            String role = employeeRole.getText();
            int age = Integer.parseInt(employeeAge.getText());
            int phone = Integer.parseInt(employeePhone.getText());
            String address = employeeAddress.getText();
            String familySituation = employeeFamilySituation.getText();
            String shift = employeeShift.getText();
            String email = employeeEmail.getText();
            float salary = Float.parseFloat(employeeSalary.getText());


            DAO.createEmployee(new Employee(id, age, phone, salary, name, role, address, shift, familySituation, email));
            employeeReset();
        } else {
            System.out.println("fill all blank fields !!");
        }
    }

    public void UpdateEmployee(){
        if(!employeeId.getText().isEmpty()
                || !employeeName.getText().isEmpty()
                || !employeeRole.getText().isEmpty()
                || !employeeAge.getText().isEmpty()
                || !employeePhone.getText().isEmpty()
                || !employeeAddress.getText().isEmpty()
                || !employeeFamilySituation.getText().isEmpty()
                || !employeeShift.getText().isEmpty()
                || !employeeEmail.getText().isEmpty()
                || !employeeSalary.getText().isEmpty()){
            int id = Integer.parseInt(employeeId.getText());
            String name = employeeName.getText();
            String role = employeeRole.getText();
            int age = Integer.parseInt(employeeAge.getText());
            int phone = Integer.parseInt(employeePhone.getText());
            String address = employeeAddress.getText();
            String familySituation = employeeFamilySituation.getText();
            String shift = employeeShift.getText();
            String email = employeeEmail.getText();
            float salary = Float.parseFloat(employeeSalary.getText());
            DAO.updateEmployee( id,  age,  phone,  salary,  name,  role,  address,  shift,  familySituation,  email);
            showEmployeeTable();
            employeeReset();
        }else {
            System.out.println("fill all blank fields !!");
        }
    }

    public void DeleteEmployee(){
        Alert alert;
        if (!employeeId.getText().isEmpty()
                || !employeeName.getText().isEmpty()
                || !employeeRole.getText().isEmpty()
                || !employeeAge.getText().isEmpty()
                || !employeePhone.getText().isEmpty()
                || !employeeAddress.getText().isEmpty()
                || !employeeFamilySituation.getText().isEmpty()
                || !employeeShift.getText().isEmpty()
                || !employeeEmail.getText().isEmpty()
                || !employeeSalary.getText().isEmpty()){
            int id = Integer.parseInt(employeeId.getText());

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
                DAO.deleteEmployee(id);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText(employeeName.getText()+" Was Deleted Successfully!");
                alert.showAndWait();
                showEmployeeTable();
                employeeReset();
            }
        }else {
            System.out.println("fill all blank fields !!");
        }
    }

    public void employeeReset() {//to empty the inputs
        employeeId.setText("");
        employeeName.setText("");
        employeeRole.setText("");
        employeeAge.setText("");
        employeePhone.setText("");
        employeeAddress.setText("");
        employeeFamilySituation.setText("");
        employeeShift.setText("");
        employeeEmail.setText("");
        employeeSalary.setText("");
    }

    public void SwitchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/menu.fxml"));
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
        if (option.isPresent() && option.get() == buttonTypeYes) {
            root = FXMLLoader.load(getClass().getResource("../View/loginView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("../Styles/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } else {
            return;
        }

    }

    public ObservableList<Employee> EmployeeList(){//Creating Menu list to use it on the showMenuTable function
        ObservableList<Employee> listM = FXCollections.observableArrayList();
        try{
            ResultSet result = DAO.showAllEmployee();
            while (result.next()){
                Employee employee = new Employee(result.getInt(1),
                        result.getInt(4),
                        result.getInt(5),
                        result.getFloat(10),
                        result.getString(2),
                        result.getString(3),
                        result.getString(6),
                        result.getString(8),
                        result.getString(7),
                        result.getString(9));
                listM.add(employee);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return listM;
    }

    private ObservableList<Employee> EmployeeList;

    public void showEmployeeTable(){//to show the Menu Table
        EmployeeList = EmployeeList();

        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        AgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        FamilySituationCol.setCellValueFactory(new PropertyValueFactory<>("familySituation"));
        ShiftCol.setCellValueFactory(new PropertyValueFactory<>("shift"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        SalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        EmployeeTable.setItems(EmployeeList);
        System.out.println("EmployeeTableShowed");
    }

    public void SelectEmployee(){//when we select a row on the table it displays on the inputs
        Employee employee = EmployeeTable.getSelectionModel().getSelectedItem();
        int index = EmployeeTable.getSelectionModel().getSelectedIndex();

        if(index-1 < -1){
            return;
        }

        employeeId.setText(String.valueOf(employee.getId()));
        employeeName.setText(employee.getName());
        employeeRole.setText(employee.getRole());
        employeeAge.setText(String.valueOf(employee.getAge()));
        employeePhone.setText(String.valueOf(employee.getPhone()));
        employeeAddress.setText(employee.getAddress());
        employeeFamilySituation.setText(employee.getFamilySituation());
        employeeShift.setText(employee.getShift());
        employeeEmail.setText(employee.getEmail());
        employeeSalary.setText(String.valueOf(employee.getSalary()));

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