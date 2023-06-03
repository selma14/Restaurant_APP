module src {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens src to javafx.fxml;
    exports src;
}
