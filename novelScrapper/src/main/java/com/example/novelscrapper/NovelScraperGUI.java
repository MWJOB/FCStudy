package com.example.novelscrapper.GUI;

//import com.example.novelscrapper.NovelScraper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NovelScraperGUI extends Application {
//    private NovelScraper scraper;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Novel Scraper");

        Label titleLabel = new Label("소설 스크랩퍼");
        Button startButton = new Button("시작");

//        startButton.setOnAction(e -> {
//            scraper = new NovelScraper();
//            scraper.scrapeNovel();
//        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(titleLabel, startButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}