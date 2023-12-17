package com.example.scaha;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Home extends Application {

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
        title1.setTranslateX(-700);
        title2.setTranslateX(-635);
        title2.setTranslateY(-360);
        title1.setTranslateY(-360);

        Text helpText = new Text("Help");
        Text signInText = new Text("Sign in");
        Text loginText = new Text("Login");

        helpText.setFill(Color.WHITE);
        signInText.setFill(Color.WHITE);
        loginText.setFill(Color.WHITE);

        helpText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        signInText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        loginText.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        Rectangle customShape = new Rectangle(100, 40); // Set width and height
        customShape.setFill(Color.GRAY);
        customShape.setTranslateY(-350);
        customShape.setTranslateX(600);
        helpText.setOnMouseClicked(e -> {
            About ab = new About();
            try {
                ab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        signInText.setOnMouseClicked(e -> {
            Registerform re = new Registerform();
            try {
                re.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        loginText.setOnMouseClicked(e -> {
            Login ab = new Login();
            try {
                ab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        helpText.setTranslateX(200);
        helpText.setTranslateY(-350);
        signInText.setTranslateY(-350);
        signInText.setTranslateX(450);
        loginText.setTranslateY(-350);
        loginText.setTranslateX(600);

        Button getStartedButton = new Button("Get Started");
        getStartedButton.setTranslateX(150);
        getStartedButton.setTranslateY(300);
        getStartedButton.setOnAction(e->{
            Finder d = new Finder();
            try {
                d.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        getStartedButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        getStartedButton.setTranslateY(190);
        getStartedButton.setTranslateX(-510);
        Text searchFavoriteText = new Text("Search your favorite ");
        Text bookGenreText = new Text("book genre on ");
        Text bookText = new Text("Book");
        Text finderText = new Text("Finder++");

        searchFavoriteText.setTranslateX(-460);
        searchFavoriteText.setTranslateY(-150);
        bookGenreText.setTranslateX(-510);
        bookGenreText.setTranslateY(-100);

        bookText.setTranslateY(-50);
        finderText.setTranslateY(-50);
        finderText.setTranslateX(-460);
        bookText.setTranslateX(-585);

        searchFavoriteText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 40));
        bookGenreText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 40));
        bookText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 40));
        finderText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        searchFavoriteText.setFill(Color.WHITE);
        bookGenreText.setFill(Color.WHITE);
        bookText.setFill(Color.WHITE);
        finderText.setFill(Color.rgb(72, 255, 2));
        Text subtext1 = new Text("Discover Your Next Adventure: Navigate the ");
        Text subtext2 = new Text("Literary Universe with BookFinder++ – Your Gateway");
        Text subtext3 = new Text("to Endless Stories, Genres, and Imagination!");

        subtext1.setFill(Color.WHITE);
        subtext2.setFill(Color.WHITE);
        subtext3.setFill(Color.WHITE);

        subtext1.setTranslateX(-480);
        subtext1.setTranslateY(70);
        subtext2.setTranslateX(-480);
        subtext2.setTranslateY(95);
        subtext3.setTranslateX(-480);
        subtext3.setTranslateY(120);

        subtext1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
        subtext2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
        subtext3.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));

        ImageView imageView = new ImageView();

        // Load an image
        Image image = new Image("C:\\Program Files\\Java\\Scaha\\src\\images\\library.png"); // Replace with the actual path to your image

        // Set the image to the ImageView
        imageView.setImage(image);

        imageView.setFitHeight(540);
        imageView.setFitWidth(700);
        imageView.setTranslateX(350);
        imageView.setTranslateY(0);

        content.getChildren().addAll(title1, title2, searchFavoriteText, bookGenreText, bookText, finderText,
                subtext1, subtext2, subtext3, imageView);

        root.getChildren().addAll(content, helpText, signInText,customShape, loginText, getStartedButton);

        stage.setTitle("Bookfinder++");
        stage.setScene(new Scene(root, 1538, 800, Color.BLACK));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
