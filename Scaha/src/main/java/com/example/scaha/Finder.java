package com.example.scaha;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Finder extends Application {
    private List<Book> allBooks = new ArrayList<>();
    private TableView<Book> tableView = createTableView();
    private ProgressBar loadingBar = new ProgressBar(0);
    private Timeline timeline;

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
        Text homeText = new Text("Help");
        Text signInText = new Text("Sign in");
        Text loginText = new Text("Login");

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

        Rectangle customShape = new Rectangle(100, 40);
        customShape.setFill(Color.GRAY);
        customShape.setTranslateX(600);
        customShape.setTranslateY(-350);

        ImageView imageView = new ImageView();
        Image image = new Image("C:\\Program Files\\Java\\Scaha\\src\\images\\pexels-min-an-775998.jpg");
        imageView.setImage(image);
        imageView.setFitHeight(540);
        imageView.setFitWidth(700);
        imageView.setTranslateX(-350);
        imageView.setTranslateY(0);
        Label genretype = new Label("Please enter book genre");
        TextField v = new TextField();
        Button find = new Button("Find");
        find.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        find.setTranslateY(-200);
        find.setTranslateX(615);
        genretype.setTextFill(Color.WHITE);
        genretype.setTranslateY(-250);
        genretype.setTranslateX(255);
        genretype.setFont(Font.font("Intro", FontWeight.NORMAL, 18));
        v.setMaxHeight(30);
        v.setMaxWidth(470);
        v.setTranslateY(-200);
        v.setTranslateX(400);

        // Loading Bar Setup
        loadingBar.setTranslateX(400);
        loadingBar.setTranslateY(-170);
        loadingBar.setPrefWidth(200);
        loadingBar.setVisible(false);

        // Timeline Setup
        timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.ZERO, event -> loadingBar.setProgress(0)),
                new KeyFrame(javafx.util.Duration.seconds(5), event -> loadingBar.setProgress(1))
        );

        find.setOnAction(e -> {
            find.setDisable(true);
            find.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
            timeline.stop();
            timeline.playFromStart();

            loadingBar.setVisible(true);

            AdapterGPT a = new AdapterGPT();
            ArrayList<String[]> list = a.parseToStringArray(a.chatGPT(v.getText()));
            List<Book> books = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Book temp = new Book.BookBuilder()
                        .setTitle(list.get(i)[0].equals(null) ? "" : list.get(i)[0])
                        .setAuthor(list.get(i)[1].equals(null) ? "" : list.get(i)[1])
                        .setPublicationDate(list.get(i)[2].equals(null) ? "" : list.get(i)[2])
                        .setPageCount(list.get(i)[3].equals(null) ? "" : list.get(i)[3])
                        .build();
                books.add(temp);
            }
            allBooks.addAll(books);
            displayResults(books);

            find.setDisable(false);
            find.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            timeline.stop();
            loadingBar.setVisible(false);

            Book.BookIterable bookIterable = new Book.BookIterable(books);
            for (Book book : bookIterable) {
                System.out.println(book.display());
                System.out.println();
            }
        });

        content.getChildren().addAll(title1, title2, homeText, customShape, signInText, loginText, imageView, genretype, v, find);

        // Add the loadingBar to a VBox along with the existing content
        VBox contentWithLoadingBar = new VBox(content, loadingBar);
        contentWithLoadingBar.setAlignment(Pos.CENTER);
        root.getChildren().addAll(contentWithLoadingBar);

        homeText.setOnMouseClicked(e -> {
            About ab = new About();
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

        stage.setTitle("Bookfinder++");
        stage.setScene(new Scene(root, 1538, 800, Color.BLACK));
        stage.show();
    }

    public void Result(Stage stage) {
        GridPane grid = new GridPane();
        Scene b = new Scene(grid, 500, 500);
        stage.setTitle("Bookfinder++");
        stage.setScene(b);
        System.out.println();
        stage.show();
    }

    public TableView<Book> createTableView() {
        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));

        TableColumn<Book, String> publicationDateCol = new TableColumn<>("Publication Date");
        publicationDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPublicationDate()));

        TableColumn<Book, String> pageCountCol = new TableColumn<>("Page Count");
        pageCountCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPageCount()));

        tableView.getColumns().addAll(titleCol, authorCol, publicationDateCol, pageCountCol);
        return tableView;
    }

    private void displayResults(List<Book> books) {
        Stage resultStage = new Stage();

        VBox vbox = new VBox(10);
        vbox.getChildren().add(tableView);
        for (Book book : books) {
            GridPane bookGrid = new GridPane();
            bookGrid.setHgap(10);
            bookGrid.setVgap(5);

            Label titleLabel = new Label("Title:");
            Label authorLabel = new Label("Author:");
            Label publicationDateLabel = new Label("Publication Date:");
            Label pageCountLabel = new Label("Page Count:");
            titleLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 16));
            authorLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 16));
            publicationDateLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 16));
            pageCountLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 16));
            Label titleValueLabel = new Label(book.getTitle());
            Label authorValueLabel = new Label(book.getAuthor());
            Label publicationDateValueLabel = new Label(book.getPublicationDate());
            Label pageCountValueLabel = new Label(book.getPageCount());
            titleValueLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
            authorValueLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
            publicationDateValueLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
            pageCountValueLabel.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
            bookGrid.addRow(0, titleLabel, titleValueLabel);
            bookGrid.addRow(1, authorLabel, authorValueLabel);
            bookGrid.addRow(2, publicationDateLabel, publicationDateValueLabel);
            bookGrid.addRow(3, pageCountLabel, pageCountValueLabel);
            bookGrid.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px;");
            vbox.getChildren().add(bookGrid);
        }

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene resultScene = new Scene(scrollPane, 500, 500);

        resultStage.setX(920);
        resultStage.setY(250);

        resultStage.setScene(resultScene);
        resultStage.setTitle("Recommendations");
        resultStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
