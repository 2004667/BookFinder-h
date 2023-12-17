package com.example.scaha;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends Application {

    public void Please(Stage primaryStage, String message) {
        Stage please = new Stage();
        GridPane h = new GridPane();
        Scene k = new Scene(h, 300, 150);
        Text p = new Text(message);
        p.setTranslateY(60);
        p.setTranslateX(60);
        p.setFont(Font.font("Intro", FontWeight.NORMAL, 17));
        h.getChildren().addAll(p);
        please.setScene(k);
        please.setX(primaryStage.getX() + 630); // Set the position to the right of the main stage
        please.setY(primaryStage.getY() + 300);
        please.show();
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black;"); // Set the background color for GridPane

        StackPane sta = new StackPane();
        Scene scene = new Scene(gridPane, 1538, 800);
        scene.setFill(Color.BLACK);

        Text l1 = new Text("Book");
        Text l2 = new Text("Finder++");
        l1.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        l2.setFont(Font.font("Intro", FontWeight.BOLD, 19));
        l1.setFill(Color.WHITE);
        l2.setFill(Color.rgb(72, 255, 2));

        Text about = new Text("Help");
        Text home = new Text("Home page");
        Text sign = new Text("Sign in");

        Rectangle customShape = new Rectangle(100, 40); // Set width and height
        customShape.setFill(Color.GRAY);
        Label name = new Label("Name:");
        TextField namet = new TextField();
        Label email = new Label("Email:");
        TextField emailt = new TextField();
        Label password = new Label("Password:");
        TextField passwordt = new TextField();
        name.setTextFill(Color.WHITE);
        email.setTextFill(Color.WHITE);
        password.setTextFill(Color.WHITE);
        name.setTranslateX(615);
        name.setTranslateY(300);
        namet.setTranslateX(670);
        namet.setTranslateY(300);
        namet.setPrefHeight(27);
        namet.setPrefWidth(110);
        email.setTranslateX(620);
        email.setTranslateY(350);
        emailt.setPrefHeight(27);
        emailt.setTranslateX(670);
        emailt.setTranslateY(350);
        password.setTranslateX(590);
        password.setTranslateY(400);
        passwordt.setTranslateX(670);
        passwordt.setTranslateY(400);
        passwordt.setPrefHeight(27);
        Button submitButton = new Button("Login");
        submitButton.setTranslateX(880);
        submitButton.setTranslateY(440);
        submitButton.setStyle("-fx-background-color: #17de02; -fx-text-fill: white;");

        name.setFont(Font.font("Intro", FontWeight.NORMAL, 18));
        email.setFont(Font.font("Intro", FontWeight.NORMAL, 18));
        password.setFont(Font.font("Intro", FontWeight.NORMAL, 18));
        about.setFill(Color.WHITE);
        sign.setFill(Color.WHITE);
        home.setFill(Color.WHITE);

        about.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        sign.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        home.setFont(Font.font("Intro", FontWeight.NORMAL, 19));

        // Adjusted translation values
        l1.setTranslateX(48);
        l2.setTranslateX(90);
        l1.setTranslateY(30);
        l2.setTranslateY(30);

        about.setTranslateX(800);
        about.setTranslateY(30);
        sign.setTranslateX(1100);
        sign.setTranslateY(30);
        home.setTranslateX(1275);
        home.setTranslateY(30);

        customShape.setTranslateX(1275);
        customShape.setTranslateY(30);

        sta.setMaxHeight(400);
        sta.setMaxWidth(100);

        Text lack = new Text("If you have no account, please register");
        lack.setTranslateX(670);
        lack.setTranslateY(480);
        lack.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        lack.setFill(Color.WHITE);
        submitButton.setOnMouseClicked(e -> {
            String enteredEmail = emailt.getText();
            String enteredPassword = passwordt.getText();

            if (isValidLogin(enteredEmail, enteredPassword)) {
                // Successful login
                Finder g = new Finder();
                try {
                    g.start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                // You can add code here to navigate to the next screen or perform other actions.
            } else {
                // Invalid login
                Please(stage, "Invalid login. Please check \n"+"your credentials.");
            }
        });

        lack.setOnMouseClicked(h -> {
            Registerform hs = new Registerform();
            hs.start(stage);
        });
        sign.setOnMouseClicked(f -> {
            Registerform re = new Registerform();
            try {
                re.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add event handlers for "About" and "Home page" to show the "Please" message
        about.setOnMouseClicked(p -> Please(stage, "Please first of all login"));
        home.setOnMouseClicked(p -> Please(stage, "Please first of all login"));

        stage.setTitle("Bookfinder++");
        stage.setScene(scene);
        stage.show();

        gridPane.getChildren().addAll(l1, l2, sta, about, sign, customShape, home, lack, name, namet, email, emailt, password, passwordt, submitButton);
    }

    private boolean isValidLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("Data.file"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Email: " + email)) {
                    // If the email is found, check the corresponding password
                    line = reader.readLine(); // Read the next line containing the password
                    return line != null && line.startsWith("Password: " + password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Email not found or password doesn't match
    }

    public static void main(String[] args) {
        launch(args);
    }
}
