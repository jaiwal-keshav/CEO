package com.code_cafe.CoursesPage;









import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import com.code_cafe.MessagePage.MessagePage;
import com.code_cafe.ProfilePage.Profile;
import com.code_cafe.ReelsPage.ReelsPage;

public class Skills extends Application {

    Color color2 = Color.web("#E9F1FA");   //background
    Color color1 = Color.web("#2272FF");   //button
    Color color3 = Color.web("#A1D6E2");    //HBox or VBox
    Color color4 = Color.web("#7b9acc");    //HBox or VBox
    Color color5 = Color.web("#FCF6F5");    //HBox or VBox


    private Stage primaryStage;
    private HBox navBar = new HBox(35);
    private List<Course> enrolledCourses = new ArrayList<>();

    public static class Course {
        String title;
        String description;
        String imagePath;
        String videoId;

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

    private ImageView createImageView(String imagePath, int height, int width) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    

     private Scene createMainScene() {
        
        
        // Add drop shadow to ListView container
        navBar.setPadding(new Insets(10));
        navBar.setStyle("-fx-background-color: beige;");
        navBar.setPrefWidth(1900);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);
        navBar.setEffect(dropShadow);

        ImageView msgIcon = createImageView("assets/message.png", 40, 40);
        ImageView disha = createImageView("assets/chatbot.png", 40, 40);
        ImageView skills = createImageView("assets/skill.png", 40, 40);
        ImageView funding = createImageView("assets/fund.png", 40, 40);
        ImageView home = createImageView("assets/home.png", 40, 40);
        ImageView pitches = createImageView("assets/trending.png", 40, 40);
        ImageView profileIcon = createImageView("assets/profile.gif", 40, 40);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        navBar.getChildren().addAll(msgIcon, leftSpacer, skills, disha, home, pitches, funding, rightSpacer, profileIcon);

        // Event handlers for navigation icons
        home.setOnMouseClicked(event -> openHomePage());
        msgIcon.setOnMouseClicked(event -> openMessagePage());
        skills.setOnMouseClicked(event -> primaryStage.setScene(createMainScene()));
        disha.setOnMouseClicked(event -> primaryStage.setScene(createMainScene()));
        pitches.setOnMouseClicked(event -> openReelsPage());
        funding.setOnMouseClicked(event -> primaryStage.setScene(createMainScene()));
        profileIcon.setOnMouseClicked(event -> openProfilePage());

        Image course = new Image("assets/images/tutorial.png");
        ImageView courseView = new ImageView(course);
        courseView.setFitHeight(350);

        StackPane sp = new StackPane();
        sp.getChildren().add(courseView);




        Image enroll = new Image("assets/images/enroll.png");
        ImageView enrollview = new ImageView(enroll);
        enrollview.setFitWidth(50);
        enrollview.setFitHeight(50);

        Button enrolledCoursesButton = new Button(" Enrolled Courses");
        enrolledCoursesButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 23));
        enrolledCoursesButton.setGraphic(enrollview);
        enrolledCoursesButton.setStyle(
                "-fx-background-color: " + toRgbString(color5) + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");
        enrolledCoursesButton.setPadding(new Insets(10));

        enrolledCoursesButton.setOnAction(e -> primaryStage.setScene(createEnrolledCoursesScene()));
        addHoverEffect(enrolledCoursesButton);

        Label certificateProg = new Label("Certification Program");
        certificateProg.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        certificateProg.setStyle("-fx-text-fill: Black");

        HBox certificationProgramBox = new HBox(20);
        certificationProgramBox.setPrefSize(400, 400);
        certificationProgramBox.setPadding(new Insets(10));
        certificationProgramBox.setStyle(
                //"-fx-background-color: " + toRgbString(color3) + ";" +
                "-fx-background-color: rgba(255, 255, 255, 0.5);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");
        
        
        ScrollPane certificationScrollPane = new ScrollPane(certificationProgramBox);
        certificationScrollPane.setFitToHeight(true);
        certificationScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        certificationScrollPane.setOpacity(0.9);

        certificationProgramBox.getChildren().addAll(
                createCourseCard("Data Science for Beginners", "assets/images/Marketing.png",
                        "This is a beginner-level course for data science.", "qQZE17pLegE"),
                createCourseCard("Java Programming Masterclass", "assets/images/Marketing.png",
                        "Master Java programming in this comprehensive course.", "qQZE17pLegE"),
                createCourseCard("Course 3", "assets/images/Marketing.png", "Course 3 detailed description.",
                        "https://www.example.com/video3.mp4"),
                createCourseCard("Course 4", "assets/images/Marketing.png", "Course 4 detailed description.",
                        "https://www.example.com/video4.mp4"),
                createCourseCard("Course 5", "assets/images/Marketing.png", "Course 5 detailed description.",
                        "https://www.example.com/video5.mp4"),
                createCourseCard("Course 6", "assets/images/Marketing.png", "Course 6 detailed description.",
                        "https://www.example.com/video6.mp4"));
                        

        Label onlineCourse = new Label("Online Courses");
        onlineCourse.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        onlineCourse.setStyle("-fx-text-fill: Black");

        HBox onlineCoursesBox = new HBox(20);
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
                createCourseCard("Cybersecurity Basics", "assets/images/Marketing.png",
                        "Learn the basics of cybersecurity in this course.", "qQZE17pLegE"),
                createCourseCard("Photography for Beginners", "assets/images/Marketing.png",
                        "This course covers the fundamentals of photography.", "https://www.example.com/video8.mp4"));

        Image test = new Image("assets/images/question.gif");
        ImageView testview = new ImageView(test);
        testview.setFitWidth(60);
        testview.setFitHeight(60);

        Button takeAssessmentButton = new Button(" Take Skill Assessment");
        takeAssessmentButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        takeAssessmentButton.setGraphic(testview);
        takeAssessmentButton.setStyle(
                "-fx-background-color: " + toRgbString(color5) + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");
        addHoverEffect(takeAssessmentButton);

        VBox certificateBox = new VBox(10, certificateProg, certificationScrollPane);
        certificateBox.setPadding(new Insets(10));
        VBox courseBox = new VBox(10, onlineCourse, onlineCoursesScrollPane);
        courseBox.setPadding(new Insets(10));


        VBox root = new VBox(30);
        //root.setPadding(new Insets(20));
        root.getChildren().addAll(navBar, sp, enrolledCoursesButton, certificateBox, courseBox, takeAssessmentButton);
        //root.setAlignment(Pos.CENTER);
        
    


        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(root);
        //mainPane.setPadding(new Insets(0, 10, 10, 10));

        mainPane.setStyle("-fx-background-color: #7b9acc;");
       // mainPane.setPrefWidth(1900);


        ScrollPane mainScrollPane = new ScrollPane(mainPane);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mainScrollPane.setStyle("-fx-background-color: #7b9acc;");

        return new Scene(mainScrollPane, 1900, 1000);
    }
     
    private void openProfilePage() {
        Profile profilePage = new Profile();
        try {
            profilePage.start(new Stage());
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openReelsPage() {
        ReelsPage ReelsPage = new ReelsPage();
        try {
            ReelsPage.start(new Stage());
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void openHomePage() {
        
    }


    private void openMessagePage() {
        MessagePage Msg = new MessagePage();
        try {
            Msg.start(new Stage());
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String toRgbString(Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private VBox createCourseCard(String title, String imagePath, String description, String videoID) {
        VBox card = new VBox(5);
        card.setPrefWidth(300);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: #ddd; -fx-border-width: 1; -fx-background-color: white; " +
                "-fx-background-radius: 10; -fx-border-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 5);");
        card.setAlignment(Pos.TOP_CENTER);
        

        addHoverEffect(card);

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(280);
        imageView.setFitHeight(200);
        imageView.setStyle("-fx-background-radius: 10;");

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
        enrollButton.setStyle(
                "-fx-background-color: " + toRgbString(color1) + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");
        enrollButton.setOnAction(e -> {
            enrolledCourses.add(new Course(title, description, imagePath, videoID));
            primaryStage.setScene(createMainScene());
        });
        addHoverEffect(enrollButton);

        HBox buttonbox = new HBox(enrollButton);
        buttonbox.setAlignment(Pos.BOTTOM_CENTER);

        card.getChildren().addAll(imageView, spacer1, titleText, spacer, buttonbox);
        card.setOnMouseClicked(
                e -> primaryStage.setScene(createCourseDetailScene(title, imagePath, description, videoID)));

        return card;
    }

    private void addHoverEffect(javafx.scene.Node node) {
        node.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), node);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();

            if (node instanceof Button) {
                ((Button) node).setStyle("-fx-background-color: #1E90FF;");
            }
        });

        node.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), node);
            st.setToX(1);
            st.setToY(1);
            st.play();

            if (node instanceof Button) {
                ((Button) node).setStyle("-fx-background-color: " + toRgbString(color1) + ";" +
                "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 10 20 10 20;");
            }
        });
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
        addHoverEffect(enrollButton);

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene()));
        addHoverEffect(backButton);

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
            addHoverEffect(courseButton);

            dialogVbox.getChildren().add(courseButton);
        }

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        backButton.setOnAction(e -> primaryStage.setScene(createMainScene()));
        addHoverEffect(backButton);

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
        addHoverEffect(backButton);

        HBox buttonBox = new HBox(10, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        videoBox.getChildren().addAll(titleText, webView, descriptionText, buttonBox);

        return new Scene(videoBox, 1900, 1000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
