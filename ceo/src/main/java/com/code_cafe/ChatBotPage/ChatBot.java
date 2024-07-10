
/*
* author : Janhavi Khopade
* date : 7/07/2024
* description : this is a chatbot DISHA , where you can ask 
 your doubts ,questions. DISHA can guide you . 

*/
package com.code_cafe.ChatBotPage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatBot extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Page");

        // GIF and Message Label on the same line
        ImageView gifImageView = new ImageView(new Image("assets/images/ChatBot.gif"));
        gifImageView.setFitHeight(100);
        gifImageView.setFitWidth(100);

        Label messageLabel = new Label("Heyy..!! Meet Disha ......How can I help you..?");
        messageLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14pt; -fx-text-fill: #0059b3;");
        HBox topBox = new HBox(10, gifImageView, messageLabel);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: #e0f7fa;");

        // Text Area in the middle
        TextArea chatArea = new TextArea();
        chatArea.setPrefHeight(600);
        //chatArea.setStyle("-fx-control-inner-background: #b3e5fc; -fx-font-size: 12pt; -fx-text-fill: #0059b3;");

        // Input field and send button at the bottom
        TextField inputField = new TextField();
        inputField.setPromptText("Type your message here...");
        inputField.setPrefWidth(400);
        inputField.setPrefHeight(20);
        inputField.setStyle(" -fx-font-size: 12pt;");

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(80);
        sendButton.setPrefHeight(30);
        sendButton.setStyle("-fx-background-color: #4f68ff; -fx-text-fill: white; -fx-font-weight: bold;");

        // HBox for input field and send button
        HBox inputBox = new HBox(10, inputField, sendButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-background-color: #e0f7fa;");

        // VBox for the entire layout
        VBox root = new VBox(10, topBox, chatArea, inputBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #e0f7fa;");

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Handle send button click
        sendButton.setOnAction(e -> {
            String message = inputField.getText();
            if (!message.isEmpty()) {
                chatArea.appendText("You: " + message + "\n");
                inputField.clear();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
