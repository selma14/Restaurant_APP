module com.example.tryout {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tryout to javafx.fxml;
    exports com.example.tryout;
}