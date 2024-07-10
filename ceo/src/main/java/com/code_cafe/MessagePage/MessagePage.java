/*
* author : Janhavi Khopade
* date : 2/07/2024
* description : it is basically a chat interface where user can search another users 
and send messages from there connections .investors can communicate with enterpreneurs
using this page.
*/
package com.code_cafe.MessagePage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessagePage extends Application {

    private ObservableList<FeedItem> feedItems = FXCollections.observableArrayList(
            new FeedItem("Janhavi", new Image("/assets/images/User1.jpeg")),
            new FeedItem("Aditi", new Image("/assets/images/User2.jpeg")),
            new FeedItem("Swapnali", new Image("/assets/images/User3.jpeg")),
            new FeedItem("Bharati", new Image("/assets/images/User4.jpeg")),
            new FeedItem("Kartiki", new Image("/assets/images/User5.jpeg")),
            new FeedItem("Nandini", new Image("/assets/images/User6.jpeg")),
            new FeedItem("Manasvi", new Image("/assets/images/User7.jpeg")),
            new FeedItem("Riya", new Image("/assets/images/User8.jpeg")),
            new FeedItem("Tanvi", new Image("/assets/images/User9.jpeg")),
            new FeedItem("Jyoti", new Image("/assets/images/User10.jpeg"))
            // Add more feed items as needed
    );

    private TextArea chatMessages; // Declare chatMessages as a class-level variable
    private Label chatLabel;
    private ImageView userImageView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChatBox");

        HBox root = new HBox();

        // Left side - User list
        ListView<FeedItem> userList = setupUserList();
        userList.setStyle("-fx-background-color: lightgray;");
        userList.setPrefWidth(240); // 30% of 800
        VBox.setVgrow(userList, Priority.ALWAYS);

        // Right side - Chat interface
        VBox chatInterface = new VBox();
        chatInterface.setPadding(new Insets(10));
        chatInterface.setSpacing(10);
        chatInterface.setStyle("-fx-background-color: white;");
        chatInterface.setAlignment(Pos.TOP_CENTER);

        // User info container
        HBox userInfoContainer = new HBox(10); // 10 is the spacing between elements
        userImageView = new ImageView();
        userImageView.setFitHeight(50);
        userImageView.setFitWidth(50);
        chatLabel = new Label("Welcome to the Chat...!!!");
        chatLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");
        HBox.setHgrow(chatLabel, Priority.ALWAYS);
        chatLabel.setAlignment(Pos.CENTER);
        userInfoContainer.getChildren().addAll(userImageView, chatLabel);
        userInfoContainer.setAlignment(Pos.CENTER_LEFT);
        chatInterface.getChildren().add(userInfoContainer);

        // Chat messages area (TextArea) with padding on both sides and background image
        chatMessages = new TextArea();
        chatMessages.setEditable(false); // Read-only
        chatMessages.setWrapText(true);
        chatMessages.setPrefHeight(400); // Adjust height as needed
        chatMessages.setPadding(new Insets(10)); // Padding around TextArea
        chatMessages.setMaxWidth(Double.MAX_VALUE); // Allow TextArea to take available width
        chatMessages.setStyle("-fx-background-color:lightblue ");
        VBox.setVgrow(chatMessages, Priority.ALWAYS);

        chatInterface.getChildren().add(chatMessages);

        // Message input area (TextField) and Send button
        TextField messageInput = new TextField();
        messageInput.setPromptText("Type a message...");
        messageInput.setPrefWidth(300); // Adjust width as needed

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                messageInput.clear();
            }
        });

        HBox messageBox = new HBox(10, messageInput, sendButton); // Add spacing between TextField and Button
        messageBox.setAlignment(Pos.CENTER); // Center align the HBox
        messageBox.setPadding(new Insets(10));
        chatInterface.getChildren().add(messageBox);

        // Add message sending functionality (e.g., press Enter to send)
        messageInput.setOnAction(event -> {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                messageInput.clear();
            }
        });

        root.getChildren().addAll(userList, chatInterface);
        HBox.setHgrow(chatInterface, Priority.ALWAYS);
        userList.setMaxWidth(240);
        chatInterface.setMaxWidth(Double.MAX_VALUE);

        // Top search bar
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search User");
        searchBar.setPadding(new Insets(10));

        HBox topBar = new HBox(searchBar);
        topBar.setStyle("-fx-background-color: lightblue;");
        topBar.setPrefWidth(800);
        topBar.setPadding(new Insets(20, 20, 20, 20));
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBar);
        borderPane.setCenter(root);

        // Filtered list for displaying search results
        FilteredList<FeedItem> filteredFeedItems = new FilteredList<>(feedItems, p -> true);
        userList.setItems(filteredFeedItems); // Set filteredFeedItems to the ListView

        // Search bar listener to filter users
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFeedItems.setPredicate(feedItem -> {
                // If filter text is empty, display all users
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare usernames with filter text
                String lowerCaseFilter = newValue.toLowerCase();
                return feedItem.getAuthor().toLowerCase().contains(lowerCaseFilter);
            });
        });

        // Handle user selection in the list
        userList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                clearChat();
                chatLabel.setText(newValue.getAuthor()); // Set chat label to selected user's name
                userImageView.setImage(newValue.getImage()); // Set user image
                // Clear previous chat messages or update chat interface here
            } else {
                chatLabel.setText("Select a user to start chatting.");
                userImageView.setImage(null); // Clear user image
            }
        });

        Scene scene = new Scene(borderPane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        
    }

    private ListView<FeedItem> setupUserList() {
        ListView<FeedItem> userList = new ListView<>();
        userList.setCellFactory(param -> new ListCell<FeedItem>() {
            @Override
            protected void updateItem(FeedItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(10); // 10 is the spacing between elements
                    hBox.setPadding(new Insets(10));

                    Label authorLabel = new Label(item.getAuthor());
                    authorLabel.setStyle("-fx-font-size: 14pt;");
                    HBox.setHgrow(authorLabel, Priority.ALWAYS);
                    authorLabel.setAlignment(Pos.CENTER);
                    ImageView imageView = new ImageView(item.getImage());
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    hBox.getChildren().addAll(imageView, authorLabel);
                    setGraphic(hBox);
                }
            }
        });

        return userList;
    }

    private void sendMessage(String message) {
        // Display message in chatMessages TextArea
        chatMessages.appendText("You: " + message + "\n");
    }

    private void clearChat() {
        // Clear chatMessages TextArea
        chatMessages.clear();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class FeedItem {
        private String author;
        private Image image;

        public FeedItem(String author, Image image) {
            this.author = author;
            this.image = image;
        }

        public String getAuthor() {
            return author;
        }

        public Image getImage() {
            return image;
        }
    }
}
