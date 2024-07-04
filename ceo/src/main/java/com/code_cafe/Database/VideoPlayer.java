package com.code_cafe.Database;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoPlayer extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load the video file from the resources folder
        String videoPath = getClass().getResource("/assets/video.mp4").toExternalForm();

        // Create a Media object for the video
        Media media = new Media(videoPath);

        // Create a MediaPlayer to control playback
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Create a MediaView to display the video
        MediaView mediaView = new MediaView(mediaPlayer);

        // Create playback control buttons
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");

        // Add event handlers for buttons
        playButton.setOnAction(e -> mediaPlayer.play());
        pauseButton.setOnAction(e -> mediaPlayer.pause());
        stopButton.setOnAction(e -> mediaPlayer.stop());

        // Create an HBox for the buttons and add them
        HBox controls = new HBox(10, playButton, pauseButton, stopButton);
        controls.setAlignment(Pos.CENTER);

        // Create a VBox to hold the MediaView and controls
        VBox layout = new VBox(10, mediaView, controls);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        // Create a scene and add it to the stage
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("JavaFX Video Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
