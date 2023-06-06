package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TableView<?> EmployeeTable;

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
    private Button dashboardbutton;

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
        /*
        showEmployeeTable();
         */
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

    public void SwitchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/dashboard.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../View/employee.fxml"));
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
}