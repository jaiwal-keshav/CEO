/*
* author : Janhavi Khopade
* date : 29/06/2024
* description :  Page for User details it includes
name, phone number , job , age ,bio etc .
then what user is like a student ,investor,mentor, enterpreneur etc.
and the industries or fields they related to.
*/
package com.code_cafe.SignUpPage;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Form extends Application {

    private Scene mainScene;
    private Scene detailsScene;
    private Scene radioButtonsScene;
    private Scene industrySelectionScene;

    @Override
    public void start(Stage primaryStage) {
        // Load the GIF
        Image gif = new Image("assets/images/form3.gif");
        ImageView imageView = new ImageView(gif);
        imageView.setFitWidth(1000);
        imageView.setPreserveRatio(true);

        // Create a fade transition for the GIF
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), imageView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();

        Label label = new Label("We're excited to know more about you...!");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.DARKBLUE);

        // Bind label font size to stage width
        label.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(40)));

        // Create a scale transition for the label
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), label);
        scaleTransition.setFromX(0.5);
        scaleTransition.setFromY(0.5);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.setCycleCount(1);
        scaleTransition.play();

        // Create the Next button for details scene
        Button nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");

        // Add action to Next button in details scene
        nextButton.setOnAction(event -> {
            primaryStage.setScene(detailsScene);
        });

        // Create a VBox layout for main scene
        VBox vbox = new VBox(20, imageView, label, nextButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: 20;  -fx-border-color: #007bff; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Create the main scene
        mainScene = new Scene(vbox, 1500, 800);
        mainScene.setFill(Color.WHITE);

        // Create the details scene with input fields
        detailsScene = createDetailsScene(primaryStage);

        // Create the radio buttons scene
        radioButtonsScene = createRadioButtonsScene(primaryStage);

        // Create the industry selection scene
        industrySelectionScene = createIndustrySelectionScene(primaryStage);

        // Set the stage and show
        primaryStage.setTitle("JavaFX Animated Form Example");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Scene createDetailsScene(Stage primaryStage) {
        VBox detailsVBox = new VBox(10);
        detailsVBox.setAlignment(Pos.CENTER);
        detailsVBox.setStyle("-fx-padding: 20;");

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setMaxWidth(400);
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setMaxWidth(400);
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setMaxWidth(400);
        TextField jobTitleField = new TextField();
        jobTitleField.setPromptText("Job Title");
        jobTitleField.setMaxWidth(400);
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setMaxWidth(400);
        TextField bioField = new TextField();
        bioField.setPromptText("Bio");
        bioField.setMaxWidth(400);

        // Bind field font size to stage width
        // firstNameField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));
        // lastNameField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));
        // phoneField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));
        // jobTitleField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));
        // ageField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));
        // bioField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().divide(100)));

        // Button to go back to main scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        backButton.setOnAction(event -> {
            primaryStage.setScene(mainScene);
        });

        // Button to submit details (placeholder action)
        // Button submitButton = new Button("Submit");
        // submitButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        // submitButton.setOnAction(event -> {
        //     // Placeholder action
        //     System.out.println("Submitted: " +
        //             "First Name: " + firstNameField.getText() +
        //             ", Last Name: " + lastNameField.getText() +
        //             ", Phone Number: " + phoneField.getText() +
        //             ", Job Title: " + jobTitleField.getText() +
        //             ", Age: " + ageField.getText() +
        //             ", Bio: " + bioField.getText());
        // });

        // Create Next button for radio buttons scene
        Button nextButton2 = new Button("Next Page");
        nextButton2.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        nextButton2.setOnAction(event -> {
            primaryStage.setScene(radioButtonsScene);
        });

        // Add all components to the details VBox
        detailsVBox.getChildren().addAll(firstNameField,
                lastNameField,
                phoneField,
                jobTitleField,
                ageField,
                bioField,
                nextButton2,
                backButton);

        return new Scene(detailsVBox, 1500, 800);
    }

    private Scene createRadioButtonsScene(Stage primaryStage) {
        VBox radioButtonsVBox = new VBox(40);
        radioButtonsVBox.setAlignment(Pos.CENTER_LEFT);
        radioButtonsVBox.setStyle("-fx-padding: 20; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;-fx-background-color: rgba(255, 255, 255, 0.8);");
        radioButtonsVBox.setLayoutX(500);
        radioButtonsVBox.setBackground(Background.EMPTY);
        radioButtonsVBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        // Create radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        
        RadioButton studentRadioButton = new RadioButton("I am a Student");
        
        studentRadioButton.setToggleGroup(toggleGroup);
        RadioButton investorRadioButton = new RadioButton("I am an Investor");
        investorRadioButton.setToggleGroup(toggleGroup);
        RadioButton mentorRadioButton = new RadioButton("I am a Mentor");
        mentorRadioButton.setToggleGroup(toggleGroup);
        RadioButton entrepreneurRadioButton = new RadioButton("I am an Entrepreneur");
        entrepreneurRadioButton.setToggleGroup(toggleGroup);
        RadioButton othersRadioButton = new RadioButton("Others");
        othersRadioButton.setToggleGroup(toggleGroup);

        

        

        // Button to go back to details scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        backButton.setOnAction(event -> {
            primaryStage.setScene(detailsScene);
        });

        // Create Next button for industry selection scene
        Button nextButton3 = new Button("Next Page");
        nextButton3.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        nextButton3.setOnAction(event -> {
            primaryStage.setScene(industrySelectionScene);
        });

        // Add all components to the radioButtons VBox
        radioButtonsVBox.getChildren().addAll(studentRadioButton,
                investorRadioButton,
                mentorRadioButton,
                entrepreneurRadioButton,
                othersRadioButton,
                nextButton3,
                backButton);

        return new Scene(radioButtonsVBox, 1500, 800);
    }

    private Scene createIndustrySelectionScene(Stage primaryStage) {
        VBox industrySelectionVBox = new VBox(20);
        industrySelectionVBox.setAlignment(Pos.CENTER);
        industrySelectionVBox.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4;");

        // Industry checkboxes
        CheckBox technologyCheckBox = new CheckBox("Technology");
        CheckBox retailEcommerceCheckBox = new CheckBox("Retail and E-commerce");
        CheckBox healthcareWellnessCheckBox = new CheckBox("Healthcare and Wellness");
        CheckBox professionalServicesCheckBox = new CheckBox("Professional services");
        CheckBox educationELearningCheckBox = new CheckBox("Education and E-learning");
        CheckBox hospitalityTourismCheckBox = new CheckBox("Hospitality and Tourism");
        CheckBox financeFintechCheckBox = new CheckBox("Finance and Fintech");
        CheckBox manufacturingProductionCheckBox = new CheckBox("Manufacturing and Production");
        CheckBox realEstateConstructionCheckBox = new CheckBox("Real Estate and Construction");
        CheckBox socialImpactNonprofitsCheckBox = new CheckBox("Social Impact and Nonprofits");
        CheckBox artCreativeIndustriesCheckBox = new CheckBox("Art and Creative industries");
        CheckBox foodBeveragesCheckBox = new CheckBox("Food and Beverages");

        // Button to go back to radio buttons scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        backButton.setOnAction(event -> {
            primaryStage.setScene(radioButtonsScene);
        });

        // Button to submit selected industries
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14pt; -fx-padding: 10 20 10 20; -fx-background-radius: 5;");
        submitButton.setOnAction(event -> {

            // Placeholder action
            StringBuilder selectedIndustries = new StringBuilder("Selected Industries:");
            if (technologyCheckBox.isSelected()) selectedIndustries.append(" Technology,");
            if (retailEcommerceCheckBox.isSelected()) selectedIndustries.append(" Retail & E-commerce,");
            if (healthcareWellnessCheckBox.isSelected()) selectedIndustries.append(" Healthcare & Wellness,");
            if (professionalServicesCheckBox.isSelected()) selectedIndustries.append(" Professional services,");
            if (educationELearningCheckBox.isSelected()) selectedIndustries.append(" Education and E-learning,");
            if (hospitalityTourismCheckBox.isSelected()) selectedIndustries.append(" Hospitality and Tourism,");
            if (financeFintechCheckBox.isSelected()) selectedIndustries.append(" Finance and Fintech,");
            if (manufacturingProductionCheckBox.isSelected()) selectedIndustries.append(" Manufacturing and Production,");
            if (realEstateConstructionCheckBox.isSelected()) selectedIndustries.append(" Real Estate and Construction,");
            if (socialImpactNonprofitsCheckBox.isSelected()) selectedIndustries.append(" Social Impact and Nonprofits,");
            if (artCreativeIndustriesCheckBox.isSelected()) selectedIndustries.append(" Art and Creative industries,");
            if (foodBeveragesCheckBox.isSelected()) selectedIndustries.append(" Food and Beverages");

            System.out.println(selectedIndustries.toString());

            submitButton.setOnAction(event2 -> {
                SignUp signUpPage = new SignUp();
                try {
                    signUpPage.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });

        // Add all components to the industrySelection VBox
        industrySelectionVBox.getChildren().addAll(technologyCheckBox,
                retailEcommerceCheckBox,
                healthcareWellnessCheckBox,
                professionalServicesCheckBox,
                educationELearningCheckBox,
                hospitalityTourismCheckBox,
                financeFintechCheckBox,
                manufacturingProductionCheckBox,
                realEstateConstructionCheckBox,
                socialImpactNonprofitsCheckBox,
                artCreativeIndustriesCheckBox,
                foodBeveragesCheckBox,
                submitButton,
                backButton);

        return new Scene(industrySelectionVBox, 1500, 800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
