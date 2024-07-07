/*
* author : Keshav Jaiwal
* date : 06/07/2024
* description :  Page for presnting ideas of the entrepreneur in short video 
*               format. The page will have a list of videos that the user can
*               select and play. The user can also like, dislike, comment, and
*               share the video. The page will also display the username and
*               profile picture of the user. The user can navigate to the next
*               and previous video.
*/

package com.code_cafe.ReelsPage;
 import javafx.application.Application;
 import javafx.geometry.Insets;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.VBox;
 import javafx.scene.media.Media;
 import javafx.scene.media.MediaPlayer;
 import javafx.scene.media.MediaView;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Circle;
 import javafx.scene.text.Font;
 import javafx.stage.Stage;
 
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 
 public class ReelsPage extends Application {
 
     private List<MediaPlayer> mediaPlayers = new ArrayList<>();
     private int currentIndex = 0; // Track current video index
     private MediaPlayer currentMediaPlayer;
     private VBox reelsBox; // VBox to hold the media views and controls
 
     @Override
     public void start(Stage primaryStage) {
         // Load video files
         List<String> videoPaths = Arrays.asList(
                getClass().getResource("/assets/videos/video.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/a.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/B.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/C.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/D.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/E.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/G.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/H.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/I.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/J.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/L.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/M.mp4").toExternalForm(),
                 getClass().getResource("/assets/videos/N.mp4").toExternalForm()
               
         );
 
         // Create MediaPlayers for each video
         for (String videoPath : videoPaths) {
             Media media = new Media(videoPath);
             MediaPlayer mediaPlayer = new MediaPlayer(media);
             mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO)); // Loop video
             mediaPlayers.add(mediaPlayer);
         }
 
         // Create a VBox to hold the media view and controls for the first video
         reelsBox = new VBox(20);
         reelsBox.setAlignment(Pos.CENTER);
         reelsBox.setPadding(new Insets(10));
 
         // Create the MediaView and controls for the first video
         showMediaPlayer(mediaPlayers.get(0));
 
         // Create and set the scene
         Scene scene = new Scene(reelsBox, 800, 1000);
         primaryStage.setTitle("JavaFX Reels Page");
         primaryStage.setScene(scene);
         primaryStage.show();
     }
 
     private void showMediaPlayer(MediaPlayer mediaPlayer) {
         // Pause the current media player if one is playing
         if (currentMediaPlayer != null) {
             currentMediaPlayer.pause();
         }
 
         // Clear the current contents of the VBox except for navigation buttons
         reelsBox.getChildren().removeIf(node -> node instanceof HBox || node instanceof VBox);
 
         // Create a MediaView to display video
         MediaView mediaView = new MediaView(mediaPlayer);
         mediaView.setFitWidth(400);
         mediaView.setFitHeight(800); // Adjusted height
 
         // Create interactive buttons
         Button likeButton = createIconButton("like.png", "50K");
         Button dislikeButton = createIconButton1("dislike.png");
         Button commentButton = createIconButton("comment.png", "250");
         Button shareButton = createIconButton1("share.png");
 
         // Button likeButton = createIconButton("like.png");
         // Button dislikeButton = createIconButton("dislike.png");
         // Button commentButton = createIconButton("comment.png");
         // Button shareButton = createIconButton("share.png");
 
 
         // Add buttons to a VBox
         VBox buttonsBox = new VBox(60, likeButton, dislikeButton, commentButton, shareButton);
         buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
         buttonsBox.setPadding(new Insets(10));
 
         // Create HBox to hold MediaView and buttons
         HBox mediaAndButtonsBox = new HBox(10, mediaView, buttonsBox);
         mediaAndButtonsBox.setAlignment(Pos.CENTER);
         mediaAndButtonsBox.setPadding(new Insets(10));
 
         // Create a profile picture icon (Replace with your actual image)
         ImageView profilePic = new ImageView(new Image(getClass().getResourceAsStream("/assets/profile.png")));
         profilePic.setFitWidth(24);
         profilePic.setFitHeight(24);
 
         // Create a label for the username and caption
         Label usernameLabel = new Label("@keshav");
         usernameLabel.setFont(new Font("Times New Roman", 16));
         usernameLabel.setTextFill(Color.WHITE);
 
         Label captionLabel = new Label("Enjoying JavaFX");
         captionLabel.setFont(new Font("Times New Roman", 14));
         captionLabel.setTextFill(Color.WHITE);
 
         // Create a VBox to hold profile picture, username, and caption labels
         VBox labelsBox = new VBox(5, new HBox(profilePic, usernameLabel), captionLabel);
         labelsBox.setAlignment(Pos.BOTTOM_LEFT);
         labelsBox.setPadding(new Insets(10));
 
         // Create VBox to hold mediaAndButtonsBox and labels
         VBox contentContainer = new VBox(20, mediaAndButtonsBox, labelsBox, createNavigationButtons());
         contentContainer.setStyle("-fx-background-color: black;");
         contentContainer.setAlignment(Pos.CENTER);
         contentContainer.setMinHeight(900);
         contentContainer.setMaxWidth(470);
 
         // Handle play/pause on click
         mediaView.setOnMouseClicked(event -> {
             if (currentMediaPlayer != null && currentMediaPlayer != mediaPlayer) {
                 currentMediaPlayer.pause();
             }
             if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                 mediaPlayer.pause();
             } else {
                 mediaPlayer.play();
                 currentMediaPlayer = mediaPlayer;
             }
         });
 
         // Add the content container to the VBox
         // reelsBox.getChildren().add(contentContainer);
         currentMediaPlayer = mediaPlayer;
         currentMediaPlayer.setOnRepeat(() -> currentMediaPlayer.seek(javafx.util.Duration.ZERO)); // Loop video
         currentMediaPlayer.setOnEndOfMedia(() -> currentMediaPlayer.seek(javafx.util.Duration.ZERO)); // Loop video
         reelsBox.getChildren().add(contentContainer);
         reelsBox.setStyle("-fx-background-radius: 10;");
         
 
         // Play the first video
         mediaPlayer.play();
         
     }
 
     private HBox createNavigationButtons() {
         Button prevButton = new Button("Previous");
         Button nextButton = new Button("Next");
 
         HBox navButtons = new HBox(20, prevButton, nextButton);
         navButtons.setAlignment(Pos.CENTER_RIGHT); // Align buttons to the right
         navButtons.setPadding(new Insets(10));
 
         // Handle navigation button actions
         prevButton.setOnAction(event -> navigateToPrevious());
         nextButton.setOnAction(event -> navigateToNext());
 
         return navButtons;
     }
 
     private Button createIconButton1(String iconName) {
         Image icon = new Image(getClass().getResourceAsStream("/assets/" + iconName));
         ImageView iconView = new ImageView(icon);
         iconView.setFitWidth(24);
         iconView.setFitHeight(24);
 
         VBox buttonBox = new VBox(iconView);
         buttonBox.setAlignment(Pos.CENTER);
 
         Button button = new Button();
         button.setGraphic(iconView);
         button.setShape(new Circle(12));
         button.setPrefSize(60, 60);
         button.setStyle("-fx-background-radius: 10;");
         button.setStyle("-fx-background-color: white;");    
 
         
 
         return button;
 
     }
 
 
 
 
 
 
 
 
     private Button createIconButton(String iconName, String label) {
         Image icon = new Image(getClass().getResourceAsStream("/assets/" + iconName));
         ImageView iconView = new ImageView(icon);
         iconView.setFitWidth(30);
         iconView.setFitHeight(30);
 
         Label textLabel = new Label(label);
         textLabel.setFont(new Font("Times New Roman", 14));
         textLabel.setTextFill(Color.BLACK);
         textLabel.setPadding(new Insets(3));
 
         VBox buttonBox = new VBox(iconView, textLabel);
         buttonBox.setAlignment(Pos.CENTER);
 
         Button button = new Button();
         button.setGraphic(buttonBox);
         button.setShape(new Circle(12));
         button.setPrefSize(60, 60);
         button.setStyle("-fx-background-radius: 10;");
         button.setStyle("-fx-background-color: white;");
 
         return button;
     }
 
     private void navigateToPrevious() {
         if (currentIndex > 0) {
             currentIndex--;
             MediaPlayer mediaPlayer = mediaPlayers.get(currentIndex);
             // Replace with actual usernames and captions as per your logic
             showMediaPlayer(mediaPlayer);
         }
     }
 
     private void navigateToNext() {
         if (currentIndex < mediaPlayers.size() - 1) {
             currentIndex++;
             MediaPlayer mediaPlayer = mediaPlayers.get(currentIndex);
             // Replace with actual usernames and captions as per your logic
             showMediaPlayer(mediaPlayer);
         }
     }
 
     public static void main(String[] args) {
         launch(args);
     }
 }
 