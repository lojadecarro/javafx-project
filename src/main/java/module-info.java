module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
}