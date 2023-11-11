module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires transitive javafx.graphics;

    opens com.example.javafxproject to javafx.fxml;
    opens com.example.javafxproject.Controller to javafx.fxml;
    exports com.example.javafxproject;
}