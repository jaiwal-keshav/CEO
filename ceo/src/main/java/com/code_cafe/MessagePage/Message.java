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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import com.code_cafe.Database.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Message extends Application {

    // private ObservableList<FeedItem> feedItems = FXCollections.observableArrayList(
    //         // new FeedItem("Janhavi", "Description 1", new Image("/assets/images/User1.jpeg")),
    //         // new FeedItem("Keshav", "Description 2", new Image("/assets/images/User2.jpeg")),
    //         // new FeedItem("Swapnali", "Description 3", new Image("/assets/images/User3.jpeg")),
    //         // new FeedItem("Bharati", "Description 4", new Image("/assets/images/User4.jpeg")),
    //         // new FeedItem("Kartik", "Description 5", new Image("/assets/images/User5.jpeg")),
    //         // new FeedItem("Raj", "Description 6", new Image("/assets/images/User6.jpeg")),
    //         // new FeedItem("Manasvi", "Description 7", new Image("/assets/images/User7.jpeg")),
    //         // new FeedItem("Riya", "Description 8", new Image("/assets/images/User8.jpeg")),
    //         // new FeedItem("Tanvi", "Description 9", new Image("/assets/images/User9.jpeg")),
    //         // new FeedItem("Jyoti", "Description 10", new Image("/assets/images/User10.jpeg"))
    //         // Add more feed items as needed

    // );

    public ObservableList<FeedItem> feedItems = FXCollections.observableArrayList();

    private TextArea chatMessages; // Declare chatMessages as a class-level variable
    private Label chatLabel;

    @Override
    public void start(Stage primaryStage) {
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
        VBox chatInterface = new VBox();
        chatInterface.setPadding(new Insets(10));
        chatInterface.setSpacing(10);
        chatInterface.setStyle("-fx-background-color: white;");
        chatInterface.setAlignment(Pos.TOP_CENTER);

        // Chat messages area (TextArea)
        chatMessages = new TextArea();
        chatMessages.setEditable(false); // Read-only
        chatMessages.setWrapText(true);
        chatMessages.setPrefHeight(400); // Adjust height as needed
       // chatMessages.setTextAlignment(TextAlignment.LEFT);
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

        HBox messageBox = new HBox(messageInput, sendButton);
        messageBox.setPadding(new Insets(10));
        messageBox.setSpacing(10);
        chatInterface.getChildren().add(messageBox);

        // Add message sending functionality (e.g., press Enter to send)
        messageInput.setOnAction(event -> {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                sendMessage(message);
                messageInput.clear();
            }
        });

       


        // Display initial chat label
        chatLabel = new Label("Select a user to start chatting.");
        chatLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt;");
        chatLabel.setPadding(new Insets(10));
        chatInterface.getChildren().add(chatLabel);
        VBox.setVgrow(chatLabel, Priority.ALWAYS); // Allow label to expand
        root.setCenter(chatInterface);

        // Top search bar
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search User");
        searchBar.setPadding(new Insets(10));

        HBox topBar = new HBox(searchBar);
        topBar.setStyle("-fx-background-color: lightblue;");
        topBar.setPrefWidth(800);
        topBar.setPadding(new Insets(20, 20, 20, 20));
        root.setTop(topBar);

        // Filtered list for displaying search results
        FilteredList<FeedItem> filteredFeedItems = new FilteredList<>(feedItems, p -> true);

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
                // Clear previous chat messages or update chat interface here
            } else {
                chatLabel.setText("Select a user to start chatting.");
            }
        });

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
                    HBox hBox = new HBox(10); // 10 is the spacing between elements
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
        private String content;
        private Image image;

        public FeedItem(String author, String content, Image image) {
            this.author = author;
            this.content = content;
            this.image = image;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public Image getImage() {
            return image;
        }
    }


    private void callApi() {
        new Thread(() -> {
            try {
                String apiUrl = "https://brickzoneprop.com/WomenEM/APIS/getUsers1.php"; // Replace with your API URL
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

                    // Parse JSON response to List<User>
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<User> users = Arrays.asList(objectMapper.readValue(response.toString(), User[].class));

                    for(User user : users) {
                        System.out.println(user.getAge() + " " + user.getUserName());
                        feedItems.add(new FeedItem(user.getUserName(), "Description 1", new Image("/assets/images/User1.jpeg")));
                    }
                    // Update the UI with the response
                    //Platform.runLater(() -> responseLabel.setText(users.toString()));
                } else {
                   // Platform.runLater(() -> responseLabel.setText("GET request failed: " + responseCode));
                }
            } catch (Exception e) {
                //Platform.runLater(() -> responseLabel.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }
}

