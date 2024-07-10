package com.code_cafe.AboutUsPage;
import javafx.scene.Parent;
    
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Page2 extends Application {
    public Parent getView() {
// TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }
    

    @Override
    public void start(Stage primaryStage) {
        // Create components
        Image investorImage = new Image("/assets/images/skills.jpg");
        ImageView imageView = new ImageView(investorImage);
        imageView.setFitWidth(600); // Image width
        imageView.setPreserveRatio(true); // Preserve aspect ratio
        imageView.setLayoutX(1000);
        imageView.setLayoutY(800);

        // Big label "SheEO" with drop shadow
        Label bigLabel = new Label("SheEO");
        bigLabel.setFont(Font.font("Times new roman", 60)); // Font size
        DropShadow dropShadow = new DropShadow(5, Color.BLACK);
        bigLabel.setEffect(dropShadow);
        bigLabel.setPadding(new Insets(20,100,60,300));

        // Label "Connect with Investors" with typing animation
        Label connectLabel = new Label();
        connectLabel.setFont(Font.font("Times new roman", 40));
        animateTyping(connectLabel, "Unlock Your Potential to Learn, Grow, and Succeed");
        connectLabel.setPadding(new Insets(20,100,60,200));

        Button nextButton = new Button("Next");
        nextButton.setPrefWidth(200);

        // Handle button click event
        nextButton.setOnAction(e -> {
            // Navigate to the next page or perform action
            // Example: Change scene to another page
             primaryStage.setScene(new Scene(new Page2().getView(),1000,600));
        });

        // Left VBox for labels
        VBox leftVBox = new VBox(20); // Spacing between components
        leftVBox.setPadding(new Insets(20)); // Padding around the VBox
        leftVBox.getChildren().addAll(bigLabel, connectLabel);
        leftVBox.setAlignment(Pos.CENTER_LEFT); // Align components to the left

        // Right VBox for image and button
        VBox rightVBox = new VBox(20); // Spacing between components
        rightVBox.setPadding(new Insets(20)); // Padding around the VBox
        rightVBox.getChildren().addAll(imageView, nextButton);
        rightVBox.setAlignment(Pos.CENTER_RIGHT); // Align components to the right

        // StackPane to hold both VBoxes
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(leftVBox, rightVBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(stackPane);

        // Apply background color to the BorderPane (light brown)
        borderPane.setStyle("-fx-background-color: #f5deb3;");

        // Create scene and set it to the stage
        Scene scene = new Scene(borderPane, 1000, 600); // Scene size
        primaryStage.setScene(scene);
        primaryStage.setTitle("About Us Page");
        primaryStage.show();
    }

    // Method to animate typing effect on a label
    private void animateTyping(Label label, String text) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = text.length();
                final int n = Math.round(length * (float) frac);
                label.setText(text.substring(0, n));
            }
        };
        animation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
