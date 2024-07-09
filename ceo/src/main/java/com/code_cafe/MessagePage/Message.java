package com.code_cafe.MessagePage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.code_cafe.Database.User;
import com.code_cafe.HomePage.Home;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Message extends Application {

    private ObservableList<FeedItem> feedItems = FXCollections.observableArrayList();
    private TextArea chatMessages;
    private Label chatLabel;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("ChatBox");

        BorderPane root = new BorderPane();
        callApi();

        // Left side - User list
        ListView<FeedItem> userList = setupUserList();
        VBox userListContainer = new VBox(userList);
        userListContainer.setPadding(new Insets(10));
        userListContainer.setSpacing(10);
        userListContainer.setStyle("-fx-background-color: lightgray;");
        root.setLeft(userListContainer);

        // Right side - Chat interface
        VBox chatInterface = setupChatInterface();
        root.setCenter(chatInterface);

        // Top search bar and home button
        HBox topBar = setupTopBar(userList);
        root.setTop(topBar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ListView<FeedItem> setupUserList() {
        ListView<FeedItem> userList = new ListView<>();
        userList.setItems(feedItems);
        userList.setCellFactory(param -> new ListCell<FeedItem>() {
            @Override
            protected void updateItem(FeedItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(10);
                    hBox.setPadding(new Insets(10));

                    Label authorLabel = new Label(item.getAuthor());
                    Label contentLabel = new Label(item.getContent());

                    ImageView imageView = new ImageView(item.getImage());
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    hBox.getChildren().addAll(imageView, authorLabel, contentLabel);
                    setGraphic(hBox);
                }
            }
        });

        userList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                clearChat();
                chatLabel.setText(newValue.getAuthor());
            } else {
                chatLabel.setText("Select a user to start chatting.");
            }
        });

        return userList;
    }

    private VBox setupChatInterface() {
        VBox chatInterface = new VBox();
        chatInterface.setPadding(new Insets(10));
        chatInterface.setSpacing(10);
        chatInterface.setStyle("-fx-background-color: white;");
        chatInterface.setAlignment(Pos.TOP_CENTER);

        chatMessages = new TextArea();
        chatMessages.setEditable(false);
        chatMessages.setWrapText(true);
        chatMessages.setPrefHeight(400);
        chatInterface.getChildren().add(chatMessages);

        TextField messageInput = new TextField();
        messageInput.setPromptText("Type a message...");
        messageInput.setPrefWidth(300);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> sendMessage(messageInput.getText().trim()));

        HBox messageBox = new HBox(messageInput, sendButton);
        messageBox.setPadding(new Insets(10));
        messageBox.setSpacing(10);
        chatInterface.getChildren().add(messageBox);

        messageInput.setOnAction(event -> sendMessage(messageInput.getText().trim()));

        chatLabel = new Label("Select a user to start chatting.");
        chatLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");
        chatLabel.setPadding(new Insets(10));
        chatInterface.getChildren().add(chatLabel);
        VBox.setVgrow(chatLabel, Priority.ALWAYS);

        return chatInterface;
    }

    private HBox setupTopBar(ListView<FeedItem> userList) {
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search User");
        searchBar.setPadding(new Insets(10));

        Button homeButton = new Button("Home");
        homeButton.setOnAction(event -> returnToHome());

        HBox topBar = new HBox(searchBar, homeButton);
        topBar.setStyle("-fx-background-color: lightblue;");
        topBar.setPrefWidth(800);
        topBar.setPadding(new Insets(20));
        topBar.setSpacing(10);

        FilteredList<FeedItem> filteredFeedItems = new FilteredList<>(feedItems, p -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFeedItems.setPredicate(feedItem -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return feedItem.getAuthor().toLowerCase().contains(lowerCaseFilter);
            });
        });
        userList.setItems(filteredFeedItems);

        return topBar;
    }

    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            chatMessages.appendText("You: " + message + "\n");
        }
    }

    private void clearChat() {
        chatMessages.clear();
    }

    private void returnToHome() {
        Home homePage = new Home();
        try {
            homePage.start(new Stage());
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callApi() {
        new Thread(() -> {
            try {
                String apiUrl = "https://brickzoneprop.com/WomenEM/APIS/getUsers1.php";
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    ObjectMapper objectMapper = new ObjectMapper();
                    List<User> users = Arrays.asList(objectMapper.readValue(response.toString(), User[].class));

                    for (User user : users) {
                        feedItems.add(new FeedItem(user.getUserName(), "Description 1", new Image("/assets/images/User1.jpeg")));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static class FeedItem {
        private String author;
        private String content;
        private Image image;

        public FeedItem(String author, String content, Image image) {
            this.author = author;
            this.content = content;
            this.image = image;
        }

        public String getAuthor() { return author; }
        public String getContent() { return content; }
        public Image getImage() { return image; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}