package com.code_cafe.CoursesPage;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Skills extends Application {

    // Color color1 = Color.web("#382478");
    // Color color2 = Color.web("#E0187C");
    // Color color3 = Color.web("#79277D");
    // Color color4 = Color.web("#D4AC6E");
    // Color color5 = Color.web("#DE68B3");
    // Color color6 = Color.web("#25c4c8");
    
     Color color2 = Color.web("#E9F1FA");   //background
     Color color1 = Color.web("#2272FF");   //button
     
    //  Color color1 = Color.web("#00ABE4");
     Color color3 = Color.web("#A1D6E2");    //Hox or VBox




    private Stage primaryStage;
    private List<Course> enrolledCourses = new ArrayList<>();

    public static class Course {
        String title;
        String description;
        String imagePath;
        String videoId; // Store YouTube video ID instead of URL

        Course(String title, String description, String imagePath, String videoId) {
            this.title = title;
            this.description = description;
            this.imagePath = imagePath;
            this.videoId = videoId;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Course Layout");

        primaryStage.setScene(createMainScene());
        primaryStage.show();
    }

    private Scene createMainScene() {
        Image course = new Image("assets/tutorial.png");
        ImageView courseView = new ImageView(course);
        courseView.setFitHeight(350);

        StackPane sp = new StackPane();
        sp.getChildren().add(courseView);

        Image enroll = new Image("assets/enroll.png");
        ImageView enrollview = new ImageView(enroll);
        enrollview.setFitWidth(50);
        enrollview.setFitHeight(50);

        Button enrolledCoursesButton = new Button(" Enrolled Courses");
        enrolledCoursesButton.setFont(Font.font("Times New Roman", FontWeight.BOLD,23));
        enrolledCoursesButton.setGraphic(enrollview);
        enrolledCoursesButton.setStyle(
                "-fx-background-color: " + toRgbString(color1) + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");

        enrolledCoursesButton.setOnAction(e -> primaryStage.setScene(createEnrolledCoursesScene()));

        Label certificateProg = new Label("Certification Program");
        certificateProg.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        certificateProg.setStyle("-fx-text-fill: Black");

        HBox certificationProgramBox = new HBox(10);
        certificationProgramBox.setPrefSize(400, 400);
        certificationProgramBox.setPadding(new Insets(10));
        certificationProgramBox.setStyle(
            "-fx-background-color: " + toRgbString(color3) + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 10 20 10 20;");
        ScrollPane certificationScrollPane = new ScrollPane(certificationProgramBox);
        certificationScrollPane.setFitToHeight(true);
        certificationScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        certificationProgramBox.getChildren().addAll(
                createCourseCard("Data Science for Beginners", "assets/Marketing.png",
                        "This is a beginner-level course for data science.", "qQZE17pLegE"),
                createCourseCard("Java Programming Masterclass", "assets/Marketing.png",
                        "Master Java programming in this comprehensive course.", "qQZE17pLegE"),
                createCourseCard("Course 3", "assets/Marketing.png", "Course 3 detailed description.",
                        "https://www.example.com/video3.mp4"),
                createCourseCard("Course 4", "assets/Marketing.png", "Course 4 detailed description.",
                        "https://www.example.com/video4.mp4"),
                createCourseCard("Course 5", "assets/Marketing.png", "Course 5 detailed description.",
                        "https://www.example.com/video5.mp4"),
                createCourseCard("Course 6", "assets/Marketing.png", "Course 6 detailed description.",
                        "https://www.example.com/video6.mp4"));

        Label onlineCourse = new Label("Online Courses");
        onlineCourse.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        onlineCourse.setStyle("-fx-text-fill: Black");

        HBox onlineCoursesBox = new HBox(10);
        onlineCoursesBox.setPadding(new Insets(10));
        onlineCoursesBox.setPrefSize(400, 400);
        onlineCoursesBox.setStyle(
            "-fx-background-color: " + toRgbString(color3) + ";" +
            "-fx-text-fill: black;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 10 20 10 20;");
        ScrollPane onlineCoursesScrollPane = new ScrollPane(onlineCoursesBox);
        onlineCoursesScrollPane.setFitToHeight(true);
        onlineCoursesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        onlineCoursesBox.getChildren().addAll(
                createCourseCard("Cybersecurity Basics", "assets/Marketing.png",
                        "Learn the basics of cybersecurity in this course.", "qQZE17pLegE"),
                createCourseCard("Photography for Beginners", "assets/Marketing.png",
                        "This course covers the fundamentals of photography.", "https://www.example.com/video8.mp4"));

        Image test = new Image("assets/question.gif");
        ImageView testview = new ImageView(test);
        testview.setFitWidth(60);
        testview.setFitHeight(60);

        Button takeAssessmentButton = new Button(" Take Skill Assessment");
        takeAssessmentButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        takeAssessmentButton.setGraphic(testview);
        takeAssessmentButton.setStyle(
            "-fx-background-color: " + toRgbString(color1) + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 10 20 10 20;");

        VBox certificateBox = new VBox(10, certificateProg, certificationScrollPane);
        VBox courseBox = new VBox(10, onlineCourse, onlineCoursesScrollPane);

        VBox root = new VBox(30, sp, enrolledCoursesButton, certificateBox, courseBox, takeAssessmentButton);
        root.setPadding(new Insets(20, 50, 50, 50));
        root.setStyle(
            "-fx-background-color: " + toRgbString(color2) + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 10 20 10 20;");

        ScrollPane mainScrollPane = new ScrollPane(root);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        return new Scene(mainScrollPane, 1900, 1000);
    }

    private static String toRgbString(Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private VBox createCourseCard(String title, String imagePath, String description, String videoID) {
        VBox card = new VBox(5);
        card.setPrefWidth(800);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
        card.setAlignment(Pos.TOP_CENTER);

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(250);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleText.setTextAlignment(TextAlignment.CENTER);

        Region spacer = new Region();
        spacer.setPrefHeight(20);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Region spacer1 = new Region();
        spacer1.setPrefHeight(20);
        VBox.setVgrow(spacer1, Priority.ALWAYS);

        Button enrollButton = new Button("Enroll Now");
        enrollButton.setOnAction(e -> {
            enrolledCourses.add(new Course(title, description, imagePath, videoID));
            primaryStage.setScene(createMainScene());
        });

        HBox buttonbox = new HBox(enrollButton);
        buttonbox.setAlignment(Pos.BOTTOM_CENTER);

        card.getChildren().addAll(imageView, spacer1, titleText, spacer, buttonbox);
        card.setOnMouseClicked(
                e -> primaryStage.setScene(createCourseDetailScene(title, imagePath, description, videoID)));

        return card;
    }

    private Scene createCourseDetailScene(String courseTitle, String imagePath, String description, String videoUrl) {
        VBox detailBox = new VBox(20);
        detailBox.setPadding(new Insets(20));
        detailBox.setStyle("-fx-background-color: Beige;");

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        Text titleText = new Text(courseTitle);
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleText.setTextAlignment(TextAlignment.CENTER);

        Text descriptionText = new Text(description);
        descriptionText.setFont(Font.font("Arial", 18));
        descriptionText.setTextAlignment(TextAlignment.JUSTIFY);

        Button enrollButton = new Button("Enroll Now");
        enrollButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        enrollButton.setStyle("-fx-background-color: skyblue;");
        enrollButton.setOnAction(e -> {
            enrolledCourses.add(new Course(courseTitle, description, imagePath, videoUrl));
            primaryStage.setScene(createMainScene());
        });

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene()));

        HBox buttonBox = new HBox(10, enrollButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        detailBox.getChildren().addAll(imageView, titleText, descriptionText, buttonBox);

        return new Scene(detailBox, 1900, 1000);
    }

    private Scene createEnrolledCoursesScene() {
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(10));
        dialogVbox.getChildren().add(new Text("You are enrolled in:"));

        for (Course course : enrolledCourses) {
            Button courseButton = new Button(course.title);
            courseButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            courseButton.setStyle("-fx-background-color: lightblue;");
            courseButton.setOnAction(e -> primaryStage.setScene(createCourseVideoScene(course)));

            dialogVbox.getChildren().add(courseButton);
        }

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene()));

        dialogVbox.getChildren().add(backButton);

        return new Scene(dialogVbox, 1900, 1000);
    }

    private Scene createCourseVideoScene(Course course) {
        VBox videoBox = new VBox(20);
        videoBox.setPadding(new Insets(20));
        videoBox.setStyle("-fx-background-color: Beige;");

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.youtube.com/embed/" + course.videoId);

        Text titleText = new Text(course.title);
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleText.setTextAlignment(TextAlignment.CENTER);

        Text descriptionText = new Text(course.description);
        descriptionText.setFont(Font.font("Arial", 18));
        descriptionText.setTextAlignment(TextAlignment.JUSTIFY);

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setOnAction(e -> primaryStage.setScene(createEnrolledCoursesScene()));

        HBox buttonBox = new HBox(10, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        videoBox.getChildren().addAll(titleText, webView, descriptionText, buttonBox);

        return new Scene(videoBox, 1900, 1000);
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}

