module com.example.novelscrapper {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.novelscrapper to javafx.fxml;
    exports com.example.novelscrapper;
}