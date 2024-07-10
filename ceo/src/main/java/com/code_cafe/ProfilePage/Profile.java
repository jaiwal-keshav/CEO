/*
* author : Swapnali Patil
* date : 4/07/2024
* description : it is basically a User Profile page which consist of user information like Name-position and education.
*/

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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Profile extends Application {
    // private List<EducationEntry> educationList = new ArrayList<>();
    private ObservableList<EducationEntry> observableEducationList;

    Color color2 = Color.web("#E9F1FA"); // background
    Color color1 = Color.web("#2272FF"); // button
    Color color4 = Color.web("#313B5F"); // button
    Color color5 = Color.web("#384D75"); // button
    //Color color6 = Color.web("#EFF3FF"); // button
    Color color6 = Color.web("#FCF6F5"); // button

    // Color color1 = Color.web("#00ABE4");
    Color color3 = Color.web("#A1D6E2"); // HBOX or VBox

    private Stage primaryStage;
    private String username;
    private String headline;
    private String aboutMe;
    ImageView backgroundView;
    ImageView profileView;

    

    @Override
    public void start(Stage prStage) {
        this.primaryStage = prStage;
        showProfilePage();

        
    }

    
    
    public void showProfilePage() {
    primaryStage.setTitle("Profile Page");
    primaryStage.setResizable(true);

    observableEducationList = FXCollections.observableArrayList();

    Image background = new Image("assets/background1.png");
     backgroundView = new ImageView(background);
    backgroundView.setPreserveRatio(true);

    HBox hb = new HBox(backgroundView);
    hb.setPrefSize(1800, 100);
    hb.setStyle("-fx-background-color:SkyBlue");
    hb.setAlignment(Pos.CENTER);

    DropShadow imageDropShadow = new DropShadow();
        imageDropShadow.setOffsetX(5);
        imageDropShadow.setOffsetY(5);
        imageDropShadow.setColor(Color.BLACK);
    StackPane root = new StackPane(hb);
   
    StackPane.setMargin(hb, new Insets(30, 20, 0, 50));

    Image profile = new Image("assets/swapnali2.jpg");
    ImageView profileView = new ImageView(profile);
    profileView.setFitHeight(500);
    profileView.setFitWidth(500);
    profileView.setPreserveRatio(true);

    Circle clip = new Circle(150, 150, 150); // x, y, radius
    profileView.setClip(clip);

    Circle borderCircle = new Circle(157, 157, 157); // centerX, centerY, radius
    borderCircle.setStroke(Color.RED); // Set the border color
    borderCircle.setStrokeWidth(6); // Set the border width
    borderCircle.setFill(null);

    StackPane sp = new StackPane();
    sp.getChildren().addAll(borderCircle, profileView);
    StackPane.setMargin(profileView, new Insets(200, 800, 200, 100));
    StackPane.setMargin(borderCircle, new Insets(-200, 0, 0, -780));
    

    DropShadow imageDropShadow1 = new DropShadow();
        imageDropShadow1.setOffsetX(2);
        imageDropShadow1.setOffsetY(2);
        imageDropShadow1.setColor(Color.BLACK);

    Image camera = new Image("assets/camera1.png");
    ImageView cameraView = new ImageView(camera);
    cameraView.setFitHeight(50);
    cameraView.setFitWidth(50);
    cameraView.setPreserveRatio(true);
    cameraView.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
    cameraView.setLayoutY(420);
    cameraView.setLayoutX(60);
    cameraView.setEffect(imageDropShadow1);

    // Add action to the camera icon
    cameraView.setOnMouseClicked(event -> openPhotoChangeDialog());
    

    

    Text username = new Text("Swapnali Patil");
    username.setFont(Font.font("Arial", FontWeight.BOLD, 30));

    Text headline = new Text("Entrepreneur");
    headline.setFont(Font.font("Arial", FontWeight.BOLD, 30));

    VBox vb = new VBox(10, username, headline);
    vb.setLayoutX(150);
    vb.setLayoutY(530);
    vb.setAlignment(Pos.CENTER);
    vb.setStyle(
            
                    "-fx-text-fill: black;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;" +
                    "-fx-padding: 10 20 10 20;");

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
    aboutMeLabel.setStyle("-fx-text-fill: white;");

    TextArea aboutMeField = new TextArea("Short introduction about the user.");
    aboutMeField.setFont(Font.font("Arial", 18));
    aboutMeField.setWrapText(true);
    aboutMeField.setEditable(false);
    aboutMeField.setPrefWidth(1200);
    aboutMeField.setPrefHeight(100);
    aboutMeField.setLayoutX(500);
    aboutMeField.setLayoutY(520);

    Image ig3 = new Image("assets/edit2.png");
    ImageView iv3 = new ImageView(ig3);
    iv3.setFitWidth(50);
    iv3.setFitHeight(50);
    iv3.setEffect(imageDropShadow);

    Button editbtn = new Button();
    editbtn.setGraphic(iv3);
    editbtn.setLayoutX(10);
    editbtn.setLayoutY(530);
    editbtn.setStyle(
            "-fx-background-color: " + toRgbString(color1) + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;" +
                    "-fx-padding: 10 20 10 20;");

    editbtn.setOnAction(e -> openEditDialog(username, headline, aboutMeField));

    Image people = new Image("assets/connected1.png");
    ImageView pview = new ImageView(people);
    pview.setFitWidth(50);
    pview.setFitHeight(50);

    Button following = new Button(" Connections");
    following.setGraphic(pview);
    following.setFont(Font.font(25));
    //following.setOnAction(e -> openDummy(primaryStage));
    following.setStyle(
            "-fx-background-color: " + toRgbString(color6) + ";" +
                    "-fx-text-fill: black;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;" +
                    "-fx-padding: 10 20 10 20;");
    following.setEffect(imageDropShadow);
    following.setOnMouseEntered(e -> following.setStyle(
            "-fx-background-color: " + toRgbString(color6.darker()) + ";" +
                    "-fx-background-radius: 20;"));
    following.setOnMouseExited(e -> following.setStyle(
            "-fx-background-color: " + toRgbString(color6) + ";" +
                    "-fx-background-radius: 20;"));

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
    postbtn.setStyle(
            "-fx-background-color: " + toRgbString(color6) + ";" +
                    "-fx-text-fill: black;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;" +
                    "-fx-padding: 10 20 10 20;");
                    postbtn.setEffect(imageDropShadow);

    postbtn.setOnMouseEntered(e -> postbtn.setStyle(
            "-fx-background-color: " + toRgbString(color6.darker()) + ";" +
                    "-fx-background-radius: 20;"));
    postbtn.setOnMouseExited(e -> postbtn.setStyle(
            "-fx-background-color: " + toRgbString(color6) + ";" +
                    "-fx-background-radius: 20;"));

    HBox postbox = new HBox(postbtn);
    postbox.setLayoutX(140);
    postbox.setLayoutY(680);
    postbox.setPrefSize(1030, 50);
    //postbox.setOpacity(0.78);

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
    educationHeader.setPadding(new Insets(0,0,0,10));
    addEducationButton.setStyle(
            "-fx-background-color: " + toRgbString(color1) + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-radius: 20;");
                    addEducationButton.setEffect(imageDropShadow);

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
    educationSection.setPadding(new Insets(0,0,0,10));

    VBox mainContent = new VBox(10, sp, vb, aboutMeLabel, aboutMeField, connectbox, educationSection);
    mainContent.setPadding(new Insets(20));
    mainContent.setLayoutX(470);
    mainContent.setLayoutY(700);
    mainContent.setPrefSize(500, 200);
    //mainContent.setStyle("-fx-background-color: " + toRgbString(color2) + ";");
    
    Group gr = new Group(root, sp,cameraView, vb, usernameField, headlineField, aboutMeLabel, aboutMeField, iv3, editbtn,
            connectbox, postbox, mainContent);
    ScrollPane scrollPane = new ScrollPane(gr);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background-color:#7b9acc");
    BorderPane profilePane = new BorderPane();
    profilePane.setCenter(scrollPane);
    profilePane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

    Scene scene = new Scene(profilePane, 1900, 1000);
    primaryStage.setScene(scene);
    //scene.setFill(Color.LIGHTGRAY);
    
    

    
    primaryStage.show();
}

// Open dialog for photo change
private void openPhotoChangeDialog() {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);

    VBox dialogVbox = new VBox(20);
    dialogVbox.setAlignment(Pos.CENTER);

    Button changeProfilePhotoButton = new Button("Change Profile Photo");
    changeProfilePhotoButton.setOnAction(event -> {
        dialog.close();
        choosePhoto("profile");
    });

    Button changeBackgroundPhotoButton = new Button("Change Background Photo");
    changeBackgroundPhotoButton.setOnAction(event -> {
        dialog.close();
        choosePhoto("background");
    });

    dialogVbox.getChildren().addAll(changeProfilePhotoButton, changeBackgroundPhotoButton);

    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
}


// Open file chooser for selecting a photo
private void choosePhoto(String type) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
    );
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
        if (type.equals("profile")) {
            // Handle profile photo change
            updateProfilePhoto(selectedFile);
        } else if (type.equals("background")) {
            // Handle background photo change
            updateBackgroundPhoto(selectedFile);
        }
    }
}

    // Update profile photo
    private void updateProfilePhoto(File file) {
        try {
            Image newImage = new Image(file.toURI().toURL().toString());
            profileView.setImage(newImage);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error (e.g., show a message to the user)
        }
    }

    // Update background photo
    private void updateBackgroundPhoto(File file) {
        try {
            Image newImage = new Image(file.toURI().toURL().toString());
            backgroundView.setImage(newImage);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error (e.g., show a message to the user)
        }
    }

    private static String toRgbString(Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
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

        DropShadow imageDropShadow = new DropShadow();
        imageDropShadow.setOffsetX(5);
        imageDropShadow.setOffsetY(5);
        imageDropShadow.setColor(Color.BLACK);

        VBox entryBox = new VBox(10);
        entryBox.setPadding(new Insets(5));
        entryBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Dummy education logo (adjust the path as per your actual assets)
        Image educationLogo = new Image("assets/education.png");
        ImageView logoImageView = new ImageView(educationLogo);
        logoImageView.setFitWidth(50);
        logoImageView.setFitHeight(50);
        logoImageView.setEffect(imageDropShadow);

        Label institutionLabel = new Label(entry.getInstitutionName());
        institutionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        institutionLabel.setAlignment(Pos.CENTER_LEFT);
        institutionLabel.setStyle("-fx-text-fill: black;");

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
