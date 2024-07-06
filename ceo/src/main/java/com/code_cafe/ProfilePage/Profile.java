package com.code_cafe.ProfilePage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Profile extends Application {
    // private List<EducationEntry> educationList = new ArrayList<>();
    private ObservableList<EducationEntry> observableEducationList;

    private Stage primaryStage;
    private String username;
    private String headline;
    private String aboutMe;

    @Override
    public void start(Stage prStage) {
        this.primaryStage = prStage;
        showProfilePage();
    }

    public void showProfilePage() {

        primaryStage.setTitle("Profile Page");
        // prStage.setWidth(1000);
        // prStage.setHeight(1000);
        primaryStage.setResizable(true);

        observableEducationList = FXCollections.observableArrayList();

        Image ig = new Image("assets/background.png");
        ImageView iv = new ImageView(ig);
        iv.setPreserveRatio(true);

        HBox hb = new HBox(iv);
        // hb.setLayoutX(0);
        hb.setPrefSize(1800, 100);
        hb.setStyle("-fx-background-color:teal");
        // hb.setPadding(new Insets(20,20,20,20));
        hb.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(hb);
        StackPane.setMargin(hb, new Insets(30, 20, 0, 50));

        Image ig2 = new Image("assets/swapnali2.jpg");
        ImageView iv2 = new ImageView(ig2);
        iv2.setFitHeight(500);
        iv2.setFitWidth(500);
        iv2.setPreserveRatio(true);
        // iv2.setLayoutX(700);
        // iv2.setLayoutY(1000);

        Circle clip = new Circle(150, 150, 150); // x, y, radius
        iv2.setClip(clip);

        Circle borderCircle = new Circle(157, 157, 157); // centerX, centerY, radius
        borderCircle.setStroke(Color.RED); // Set the border color
        borderCircle.setStrokeWidth(6); // Set the border width
        borderCircle.setFill(null);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(borderCircle, iv2);
        StackPane.setMargin(iv2, new Insets(200, 800, 200, 100));
        StackPane.setMargin(borderCircle, new Insets(-200, 0, 0, -780));

        Text username = new Text("Swapnali Patil");
        username.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // username.setLayoutX(150);
        // username.setLayoutY(600);

        Text headline = new Text("Entrepreneur");
        headline.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        VBox vb = new VBox(10, username, headline);
        vb.setLayoutX(150);
        vb.setLayoutY(530);
        vb.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField(username.getText());
        usernameField.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        usernameField.setVisible(false);
        usernameField.setLayoutX(100);
        usernameField.setLayoutY(620);

        TextField headlineField = new TextField();
        headlineField.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        headlineField.setVisible(false);
        headlineField.setLayoutX(100);
        headlineField.setLayoutY(620);

        Text aboutMeLabel = new Text("About Me:");
        aboutMeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        aboutMeLabel.setLayoutX(500);
        aboutMeLabel.setLayoutY(500);

        // Text aboutMe = new Text ("Short introduction about the user");
        // aboutMe.setFont(Font.font("Arial", 20));
        // aboutMe.setWrappingWidth(500);
        // aboutMe.setLayoutX(500);
        // aboutMe.setLayoutY(550);// Set width for text wrapping

        TextArea aboutMeField = new TextArea("Short introduction about the user.");
        aboutMeField.setFont(Font.font("Arial", 18));
        aboutMeField.setWrapText(true);
        aboutMeField.setEditable(false);
        aboutMeField.setPrefWidth(1200);
        aboutMeField.setPrefHeight(100);
        aboutMeField.setLayoutX(500);
        aboutMeField.setLayoutY(520);

        // Create TextArea for editing the "About Me" section
        // TextArea aboutMeField = new TextArea(aboutMe.getText());
        // aboutMeField.setFont(Font.font("Arial", 20));
        // aboutMeField.setVisible(false);
        // aboutMeField.setWrapText(true);
        // aboutMeField.setPrefRowCount(3);
        // aboutMeField.setLayoutX(500);
        // aboutMeField.setLayoutY(700);

        Image ig3 = new Image("assets/edit2.png");
        ImageView iv3 = new ImageView(ig3);
        iv3.setFitWidth(50);
        iv3.setFitHeight(50);
        // iv3.setLayoutX(1800);
        // iv3.setLayoutY(530);

        Button editbtn = new Button();
        editbtn.setGraphic(iv3);
        editbtn.setStyle("-fx-background-color: transparent;");
        editbtn.setLayoutX(10);
        editbtn.setLayoutY(530);

        editbtn.setOnAction(e -> openEditDialog(username, headline, aboutMeField));

        // editbtn.setOnAction(new EventHandler<ActionEvent>() {
        // public void handle(ActionEvent event){
        // System.out.println("Button clicked");

        // usernameField.setVisible(true);
        // usernameField.setText(username.getText());
        // username.setVisible(true);

        // headlineField.setVisible(true);
        // headlineField.setText(headline.getText());
        // headline.setVisible(true);

        // // aboutMeField.setVisible(true);
        // // aboutMeField.setText(aboutMe.getText());
        // // aboutMe.setVisible(false);

        // usernameField.requestFocus();
        // }
        // });

        // usernameField.setOnAction(e -> {
        // username.setText(usernameField.getText());
        // username.setVisible(true);
        // usernameField.setVisible(false);

        // });

        // headlineField.setOnAction(e -> {
        // headline.setText(headlineField.getText());
        // headline.setVisible(true);
        // headlineField.setVisible(false);
        // });

        // Button saveAboutMeBtn = new Button("Save");
        // saveAboutMeBtn.setOnAction(e -> {
        // aboutMeField.setEditable(false);
        // });

        Image people = new Image("assets/connected1.png");
        ImageView pview = new ImageView(people);
        pview.setFitWidth(50);
        pview.setFitHeight(50);

        Button following = new Button(" Connections");
        following.setGraphic(pview);
        following.setFont(Font.font(25));
        //following.setOnAction(e -> openDummy(primaryStage));

        HBox connectbox = new HBox(following);
        connectbox.setLayoutX(140);
        connectbox.setLayoutY(780);
        connectbox.setPrefSize(1000, 50);

        Image post = new Image("assets/post.png");
        ImageView postview = new ImageView(post);
        postview.setFitWidth(50);
        postview.setFitHeight(50);

        Button postbtn = new Button(" Create Post ");
        postbtn.setGraphic(postview);
        postbtn.setFont(Font.font(25));

        HBox postbox = new HBox(postbtn);
        postbox.setLayoutX(140);
        postbox.setLayoutY(680);
        postbox.setPrefSize(1030, 50);

        Label Education = new Label("Education:");
        Education.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Education.setLayoutX(500);
        Education.setLayoutY(700);
        Label educationLabel = new Label("Education:");
        educationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Button addEducationButton = new Button("+");
        addEducationButton.setOnAction(e -> addEducationEntry());
        HBox educationHeader = new HBox(10, educationLabel, addEducationButton);
        educationHeader.setAlignment(Pos.CENTER_LEFT);

        VBox educationEntriesContainer = new VBox(10);
        educationEntriesContainer.setPadding(new Insets(10));
        observableEducationList.addListener((javafx.beans.Observable observable) -> {
            educationEntriesContainer.getChildren().clear();
            for (EducationEntry entry : observableEducationList) {
                VBox entryBox = createEducationEntryBox(entry);
                educationEntriesContainer.getChildren().add(entryBox);
            }
        });

        VBox educationSection = new VBox(10, educationHeader, educationEntriesContainer);
        educationSection.setPadding(new Insets(10));
        educationSection.setPrefSize(500, 500);

        VBox mainContent = new VBox(10, sp, vb, aboutMeLabel, aboutMeField, connectbox, educationSection);
        mainContent.setPadding(new Insets(20));
        mainContent.setLayoutX(470);
        mainContent.setLayoutY(700);
        mainContent.setPrefSize(500, 200);

        Image photo = new Image("assets/background.png");
        ImageView view = new ImageView(photo);
        view.setLayoutX(500);
        view.setLayoutY(1500);

        Group gr = new Group(root, sp, vb, usernameField, headlineField, aboutMeLabel, aboutMeField, iv3, editbtn,
                connectbox, postbox, mainContent, view);
        gr.setStyle("-fx-background-color: ;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeight(1000);
        scrollPane.prefWidth(100);
        scrollPane.setContent(gr);
        // scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true); // Ensure it fits to the width of the content
        scrollPane.setFitToHeight(false); // Disable fitting to the height
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        // scrollPane.setStyle("-fx-background: teal;");

        scrollPane.setStyle("-fx-background: beige; -fx-background-color: beige;");

        Scene prScene = new Scene(scrollPane, 1900, 1000);
        prScene.setFill(Color.BEIGE);
        primaryStage.setScene(prScene);
        primaryStage.show();

    }

    // dialog box of editing username, headline and about me
    private void openEditDialog(Text usernameText, Text headlineText, TextArea aboutMeField) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL); // getText to save it to database
        dialog.initOwner(primaryStage);
        dialog.setTitle("Edit Profile");

        VBox dialogVBox = new VBox(10);
        dialogVBox.setPadding(new Insets(20));

        TextField usernameField = new TextField(usernameText.getText());
        usernameField.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        TextField headlineField = new TextField(headlineText.getText());
        headlineField.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        TextArea aboutMeTextArea = new TextArea(aboutMeField.getText());
        aboutMeTextArea.setFont(Font.font("Arial", 18));
        aboutMeTextArea.setWrapText(true);

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        saveButton.setOnAction(e -> {
            username = usernameField.getText();
            headline = headlineField.getText();
            aboutMe = aboutMeTextArea.getText();

            usernameText.setText(username);
            headlineText.setText(headline);
            aboutMeField.setText(aboutMe);

            dialog.close();
        });

        dialogVBox.getChildren().addAll(new Label("Username:"), usernameField, new Label("Headline:"), headlineField,
                new Label("About Me:"), aboutMeTextArea, saveButton);

        Scene dialogScene = new Scene(dialogVBox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    // edit education info
    private VBox createEducationEntryBox(EducationEntry entry) {

        VBox entryBox = new VBox(10);
        entryBox.setPadding(new Insets(5));
        entryBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Dummy education logo (adjust the path as per your actual assets)
        Image educationLogo = new Image("assets/education.png");
        ImageView logoImageView = new ImageView(educationLogo);
        logoImageView.setFitWidth(50);
        logoImageView.setFitHeight(50);

        Label institutionLabel = new Label(entry.getInstitutionName());
        institutionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        institutionLabel.setAlignment(Pos.CENTER_LEFT);

        Label degreeLabel = new Label(entry.getDegree() + " - " + entry.getFieldOfStudy());
        degreeLabel.setFont(Font.font("Arial", 20));

        Label dateLabel = new Label(entry.getStartDate() + " - " + entry.getEndDate());
        dateLabel.setFont(Font.font("Arial", 20));

        HBox label = new HBox(institutionLabel);
        label.setAlignment(Pos.CENTER);

        HBox edu = new HBox(10, logoImageView, label);
        VBox edu1 = new VBox(10, degreeLabel, dateLabel);
        edu1.setPadding(new Insets(0, 10, 10, 10));

        entryBox.getChildren().addAll(edu, edu1);
        return entryBox;
    }

    private void addEducationEntry() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Education");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField institutionField = new TextField();
        institutionField.setPromptText("Institution Name");
        TextField degreeField = new TextField(); // get text for db
        degreeField.setPromptText("Degree");
        TextField fieldOfStudyField = new TextField();
        fieldOfStudyField.setPromptText("Field of Study");
        TextField startDateField = new TextField();
        startDateField.setPromptText("Start Date");
        TextField endDateField = new TextField();
        endDateField.setPromptText("End Date");

        grid.add(new Label("Institution Name:"), 0, 0);
        grid.add(institutionField, 1, 0);
        grid.add(new Label("Degree:"), 0, 1);
        grid.add(degreeField, 1, 1);
        grid.add(new Label("Field of Study:"), 0, 2);
        grid.add(fieldOfStudyField, 1, 2);
        grid.add(new Label("Start Date:"), 0, 3);
        grid.add(startDateField, 1, 3);
        grid.add(new Label("End Date:"), 0, 4);
        grid.add(endDateField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                EducationEntry entry = new EducationEntry(
                        institutionField.getText(),
                        degreeField.getText(),
                        fieldOfStudyField.getText(),
                        startDateField.getText(),
                        endDateField.getText());
                observableEducationList.add(entry);
            }
        });
    }

    // navigating from one stage to another
    // private void openDummy(Stage primaryStage) {
    //     Dummy connectionsPage = new Dummy();
    //     connectionsPage.setAccount(this);
    //     try {
    //         connectionsPage.start(primaryStage);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
