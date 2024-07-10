/*
 * author : Keshav Jaiwal
 * date : 06/07/2024
 * description :  Page for presenting ideas of the entrepreneur in short video 
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
import javafx.scene.Group;
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
 
 import com.code_cafe.HomePage.Home;
 
 public class ReelsPage extends Application {
 
     private List<MediaPlayer> mediaPlayers = new ArrayList<>();
     private int currentIndex = 0; // Track current video index
     private MediaPlayer currentMediaPlayer;
     private VBox reelsBox; // VBox to hold the media views and controls
     private Stage primaryStage;
     private Button homeButton;
 
     @Override
     public void start(Stage primaryStage) {
         this.primaryStage = primaryStage;
 
         // Load video files
         List<String> videoPaths = Arrays.asList(
             getClass().getResource("/assets/videos/a.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/b.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/c.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/d.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/e.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/f.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/g.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/h.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/i.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/j.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/k1.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/l.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/m.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/n.mp4").toExternalForm(),
             getClass().getResource("/assets/videos/l.mp4").toExternalForm()
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
         //reelsBox.setStyle("-fx-background-color:Black");

        //  ImageView backgroundImageView = new ImageView(new Image("/assets/images/.jpg")); // Change to your background image path
        // backgroundImageView.setFitWidth(1900);
        // backgroundImageView.setFitHeight(1000);
        // backgroundImageView.setOpacity(0.8);
         reelsBox.setLayoutX(800);
        Group bg = new Group(reelsBox);
         // Create and set the scene
         Scene scene = new Scene(bg, 1900, 1000);
         
       
         primaryStage.setTitle("JavaFX Reels Page");
         primaryStage.setScene(scene);
         primaryStage.show();
     }
 
     private HBox createButtonBox() {
         homeButton = new Button("Return to Home");
         homeButton.setOnAction(e -> returnToHome());
 
         HBox buttonBox = new HBox(20, homeButton);
         buttonBox.setAlignment(Pos.CENTER);
         return buttonBox;
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
         mediaView.setFitWidth(470);
         mediaView.setFitHeight(800); // Adjusted height
 
         // Create interactive buttons
         Button likeButton = createIconButton("like.png", "50K");
         Button dislikeButton = createIconButton1("dislike.png");
         Button commentButton = createIconButton("comment.png", "250");
         Button shareButton = createIconButton1("share.png");
 
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
         labelsBox.setPadding(new Insets(0,0,0,50));
 
         // Create VBox to hold mediaAndButtonsBox and labels
         VBox contentContainer = new VBox(20, mediaAndButtonsBox, labelsBox, createNavigationButtons());
         contentContainer.setStyle("-fx-background-color: #3C4046; -fx-background-radius:50;");
         contentContainer.setAlignment(Pos.CENTER);
         contentContainer.setMinHeight(990);
         contentContainer.setMaxWidth(650);
 
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
         currentMediaPlayer = mediaPlayer;
         currentMediaPlayer.setOnRepeat(() -> currentMediaPlayer.seek(javafx.util.Duration.ZERO)); // Loop video
         currentMediaPlayer.setOnEndOfMedia(() -> currentMediaPlayer.seek(javafx.util.Duration.ZERO)); // Loop video
         reelsBox.getChildren().add(contentContainer);
         reelsBox.setStyle("-fx-background-radius: 10;");
         //reelsBox.setStyle("-fx-background-color:Black");
 
         // Play the first video
         mediaPlayer.play();
     }
 
     private HBox createNavigationButtons() {
         Button prevButton = new Button("Previous");
         Button nextButton = new Button("Next");
         homeButton = new Button("Return to Home");
 
         HBox navButtons = new HBox(20, prevButton, nextButton, homeButton);
         navButtons.setAlignment(Pos.CENTER_RIGHT); // Align buttons to the right
         navButtons.setPadding(new Insets(10));
 
         // Handle navigation button actions
         prevButton.setOnAction(event -> navigateToPrevious());
         nextButton.setOnAction(event -> navigateToNext());
         homeButton.setOnAction(e -> returnToHome());
 
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
             showMediaPlayer(mediaPlayer);
         }
     }
 
     private void navigateToNext() {
         if (currentIndex < mediaPlayers.size() - 1) {
             currentIndex++;
             MediaPlayer mediaPlayer = mediaPlayers.get(currentIndex);
             showMediaPlayer(mediaPlayer);
         }
     }
 
     private void returnToHome() {
         Home homePage = new Home();
         try {
             if (currentMediaPlayer != null) {
                 currentMediaPlayer.stop();
                 System.out.println("Media player stopped");
             }
             primaryStage.close();
             homePage.start(new Stage());
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 
     public static void main(String[] args) {
         launch(args);
     }
 }
 