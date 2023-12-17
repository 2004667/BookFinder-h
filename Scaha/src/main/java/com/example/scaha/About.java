package com.example.scaha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        StackPane content = new StackPane();
        content.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Text title1 = new Text("Book");
        Text title2 = new Text("Finder++");
        title1.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        title2.setFont(Font.font("Intro", FontWeight.BOLD, 19));
        title1.setFill(Color.WHITE);
        title2.setFill(Color.rgb(72, 255, 2));
        title1.setTranslateY(-350);
        title2.setTranslateY(-350);
        title1.setTranslateX(-700);
        title2.setTranslateX(-635);
        Text homeText = new Text("Home page");
        Text signInText = new Text("Sign in");
        Text loginText = new Text("Login");
        homeText.setOnMouseClicked(e -> {
            Home ab = new Home();
            try {
                ab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        signInText.setOnMouseClicked(e -> {
            Home re = new Home();
            try {
                re.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        loginText.setOnMouseClicked(e -> {
            Home ab = new Home();
            try {
                ab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        homeText.setFill(Color.WHITE);
        signInText.setFill(Color.WHITE);
        loginText.setFill(Color.WHITE);

        homeText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        signInText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        loginText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));

        homeText.setTranslateX(200);
        homeText.setTranslateY(-350);
        signInText.setTranslateY(-350);
        signInText.setTranslateX(400);
        loginText.setTranslateY(-350);
        loginText.setTranslateX(600);

        Rectangle customShape = new Rectangle(100, 40); // Set width and height
        customShape.setFill(Color.GRAY);
        customShape.setTranslateX(600);
        customShape.setTranslateY(-350);
        Text inst = new Text("If you have difficulties with our");
        Text inst2 = new Text("website, please let us know.");
        Text youcan = new Text("Contact: 8-775-199-21-57");
        Text youe = new Text("Email: ilias2004nyssanbek@gmail.com");
        inst.setTranslateX(300);
        inst2.setTranslateX(285);
        inst2.setTranslateY(-170);
        inst.setTranslateY(-190);
        inst.setFont(Font.font("Intro", FontWeight.NORMAL, 22));
        inst2.setFont(Font.font("Intro", FontWeight.NORMAL, 22));
        youcan.setFont(Font.font("Intro", FontWeight.NORMAL, 22));
        youe.setFont(Font.font("Intro", FontWeight.NORMAL, 22));
        youcan.setTranslateX(280);
        youe.setTranslateX(340);
        youcan.setTranslateY(-130);
        youe.setTranslateY(-100);
        youcan.setFill(Color.WHITE);
        youe.setFill(Color.WHITE);
        inst.setFill(Color.WHITE);
        inst2.setFill(Color.WHITE);

        ImageView imageView = new ImageView();

        // Load an image
        Image image = new Image("C:\\Program Files\\Java\\Scaha\\src\\images\\pexels-min-an-775998.jpg"); // Replace with the actual path to your image

        // Set the image to the ImageView
        imageView.setImage(image);

        imageView.setFitHeight(540);
        imageView.setFitWidth(700);
        imageView.setTranslateX(-350);
        imageView.setTranslateY(0);

        content.getChildren().addAll(title1, title2, homeText, customShape, signInText, loginText,
                inst, inst2, youcan, youe, imageView);

        root.getChildren().addAll(content);

        stage.setTitle("Bookfinder++");
        stage.setScene(new Scene(root, 1538, 800, Color.BLACK));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
