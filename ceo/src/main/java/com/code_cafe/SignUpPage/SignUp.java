package com.code_cafe.SignUpPage;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUp extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane mainLayout = new StackPane();
        mainLayout.setPadding(new Insets(20));

        // Load the background image
        Image backgroundImage = new Image("assets/images/backg.jpeg"); // Adjust the path to your image file
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1900); // Adjust the size of the image
        backgroundImageView.setFitHeight(1000);
        backgroundImageView.setPreserveRatio(false);

        // Create the main layout
        HBox mainLayout1 = new HBox();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f2f5ff;");

        // Create the left side layout
        VBox leftLayout = new VBox();
        leftLayout.setSpacing(15);
        leftLayout.setPadding(new Insets(20));
        leftLayout.setStyle("-fx-background-color: #ffffff;");
        leftLayout.setAlignment(Pos.CENTER);
        leftLayout.setPrefSize(650, 500);

        // Add image before the label
        Image logoImage = new Image("assets/images/logo.jpeg"); // Adjust the path to your image file
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(100);
        logoImageView.setFitHeight(100);
        leftLayout.getChildren().add(logoImageView);

        // Add logo and text
        Label getStarted = new Label("WELCOME TO SHEO");
        getStarted.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        leftLayout.getChildren().add(getStarted);

        Label signUpLink = new Label("Don't have an account? Sign up");
        signUpLink.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        signUpLink.setTextFill(Color.BLUE);
        leftLayout.getChildren().add(signUpLink);

        // Create and add form fields
        TextField signInEmail = new TextField();
        signInEmail.setPromptText("Email");
        signInEmail.setMaxWidth(350);
        leftLayout.getChildren().add(signInEmail);

        TextField signInPassword = new TextField();
        signInPassword.setPromptText("Password");
        signInPassword.setMaxWidth(350);
        leftLayout.getChildren().add(signInPassword);

        // Create and add sign in button
        Button signInButton = new Button("Sign in");
        signInButton.setMinWidth(250);
        signInButton.setStyle("-fx-background-color: #4f68ff; -fx-text-fill: white; -fx-font-size: 16;");
        leftLayout.getChildren().add(signInButton);

        // Create the right side layout
        VBox rightLayout = new VBox();
        rightLayout.setSpacing(15);
        rightLayout.setPadding(new Insets(20));
        rightLayout.setAlignment(Pos.CENTER);
        rightLayout.setStyle("-fx-background-color: #4f68ff;");
        rightLayout.setPrefSize(550, 500);

        Image image = new Image("assets/images/signup.jpg"); // Adjust the path to your image file
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(550); // Adjust the size of the image
        imageView.setFitHeight(400);
        rightLayout.getChildren().add(imageView);

        // Add AI and description
        Label aiLabel = new Label("AI");
        aiLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        aiLabel.setTextFill(Color.WHITE);
        rightLayout.getChildren().add(aiLabel);

        Label description = new Label("Have your own personal website");
        description.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        description.setTextFill(Color.WHITE);
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);
        rightLayout.getChildren().add(description);

        // Add animation to signUpLink label
        signUpLink.setOnMouseClicked(event -> {
            // Create translate transition for rightLayout
            TranslateTransition translateRight = new TranslateTransition(Duration.seconds(1), rightLayout);
            translateRight.setToX(-650);

            // Create translate transition for leftLayout
            TranslateTransition translateLeft = new TranslateTransition(Duration.seconds(1), leftLayout);
            translateLeft.setToX(650);

            // Create scale transition for leftLayout
            ScaleTransition scaleLeft = new ScaleTransition(Duration.seconds(1), leftLayout);
            scaleLeft.setToX(1.1);
            scaleLeft.setToY(1.1);

            // Create scale transition for rightLayout
            ScaleTransition scaleRight = new ScaleTransition(Duration.seconds(1), rightLayout);
            scaleRight.setToX(1.1);
            scaleRight.setToY(1.1);

            // Create a sequential transition to play translate and scale transitions
            SequentialTransition sequentialTransitionLeft = new SequentialTransition(translateLeft, scaleLeft);
            SequentialTransition sequentialTransitionRight = new SequentialTransition(translateRight, scaleRight);

            // Play animations
            sequentialTransitionLeft.play();
            sequentialTransitionRight.play();

            // Switch contents after animation
            translateRight.setOnFinished(e -> {
                leftLayout.getChildren().clear();
                leftLayout.setStyle("-fx-background-color: #4f68ff;");
                leftLayout.setPrefSize(550, 500);

                // Reuse the logo image
                ImageView logoImageView2 = new ImageView(logoImage);
                logoImageView2.setFitWidth(100);
                logoImageView2.setFitHeight(100);
                leftLayout.getChildren().add(logoImageView2);

                // Add new text and fields
                Label getStarted2 = new Label("WELCOME TO SHEO");
                getStarted2.setFont(Font.font("Arial", FontWeight.BOLD, 28));
                leftLayout.getChildren().add(getStarted2);

                TextField nameField = new TextField();
                nameField.setPromptText("Enter your name");
                nameField.setMaxWidth(350);
                leftLayout.getChildren().add(nameField);

                TextField emailField = new TextField();
                emailField.setPromptText("Enter your email");
                emailField.setMaxWidth(350);
                leftLayout.getChildren().add(emailField);

                TextField countryField = new TextField();
                countryField.setPromptText("Enter your country");
                countryField.setMaxWidth(350);
                leftLayout.getChildren().add(countryField);

                Button signUpButton = new Button("Sign up");
                signUpButton.setMinWidth(250);
                signUpButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #4f68ff; -fx-font-size: 16;");
                leftLayout.getChildren().add(signUpButton);

                // Create a drop shadow effect
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(10);
                dropShadow.setOffsetX(5);
                dropShadow.setOffsetY(5);
                dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));

                leftLayout.setEffect(dropShadow);
                rightLayout.setEffect(dropShadow);

                // Move the VBox elements back to the original position and play the drop shadow animation
                translateLeft.setToX(0);
                translateRight.setToX(0);
                sequentialTransitionLeft.play();
                sequentialTransitionRight.play();
            });
        });

        // Combine left and right layouts
        mainLayout1.getChildren().addAll(leftLayout, rightLayout);
        mainLayout1.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(backgroundImageView, mainLayout1);

        // Create scene and set stage
        Scene scene = new Scene(mainLayout, 1400, 800);
        primaryStage.setTitle("Sign Up Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
