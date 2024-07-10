package com.code_cafe.HomePage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.code_cafe.Database.User;
import com.code_cafe.Database.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.code_cafe.ProfilePage.Profile;
import com.code_cafe.ReelsPage.ReelsPage;
import com.code_cafe.MessagePage.Message;
import com.code_cafe.MessagePage.MessagePage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Home extends Application {
    
    private ObservableList<FeedItem> feedItems = FXCollections.observableArrayList();
    private StackPane mainContent = new StackPane();
    private HBox navBar = new HBox(35);
    private Stage primaryStage;
    Color color1 = Color.web("#6865aa"); 
    Color color2 = Color.web("#a19ee3"); 
    Color color3 = Color.web("#bdbaff"); 

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        callApiPost();

        ListView<FeedItem> listView = new ListView<>(feedItems);
        listView.setCellFactory(param -> new FeedItemCell());
        listView.setSelectionModel(null);
        listView.setFocusTraversable(false);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("hidden-scrollbar");

        // Add drop shadow to ListView container
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);
        scrollPane.setEffect(dropShadow);

        navBar.setPadding(new Insets(10));
        navBar.setStyle("-fx-background-color: beige;");
        navBar.setPrefWidth(1900);
        navBar.setEffect(dropShadow);

        ImageView msgIcon = createImageView("assets/message.png", 30, 40);
        ImageView disha = createImageView("assets/chatbot.png", 30, 40);
        ImageView skills = createImageView("assets/skill.png", 30, 40);
        ImageView funding = createImageView("assets/fund.png", 30, 40);
        ImageView home = createImageView("assets/home.png", 30, 40);
        ImageView pitches = createImageView("assets/trending.png", 30, 40);
        ImageView profileIcon = createImageView("assets/profile.gif", 40, 40);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

       
        navBar.getChildren().addAll(msgIcon, leftSpacer, skills, disha, home, pitches, funding, rightSpacer, profileIcon);

        VBox root = new VBox( mainContent);
        root.setPadding(new Insets(10));
        VBox.setVgrow(mainContent, Priority.ALWAYS);
        root.setPrefSize(1000, 990);
        root.setLayoutY(88);
        root.setLayoutX(400);

        Image women = new Image("assets/images/ladyvideo.gif");
        ImageView womenImage = new ImageView(women);
        womenImage.setFitHeight(600);
        womenImage.setPreserveRatio(true);

        // Add drop shadow to the background image
        DropShadow imageDropShadow = new DropShadow();
        imageDropShadow.setOffsetX(5);
        imageDropShadow.setOffsetY(5);
        imageDropShadow.setColor(Color.BLACK);
        womenImage.setEffect(imageDropShadow);

        // VBox lady = new VBox(womenImage);
        // lady.setLayoutX(1330);
        // lady.setLayoutY(190);

        // Create background image view
        ImageView backgroundImageView = new ImageView(new Image("assets/images/bggg.jpg")); // Change to your background image path
        backgroundImageView.setFitWidth(1900);
        backgroundImageView.setFitHeight(1000);
        backgroundImageView.setOpacity(0.5);

        Group group = new Group(backgroundImageView,navBar,root);
        Scene scene = new Scene(group, 1900, 1000);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.setFill(color3);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollable Feed Page");
        primaryStage.show();

        setContent(scrollPane, home);

        home.setOnMouseClicked(event -> setContent(scrollPane, home));
        msgIcon.setOnMouseClicked(event -> openMessagePage());
        skills.setOnMouseClicked(event -> setContent(createDummyContent("Skills Page"), skills));
        disha.setOnMouseClicked(event -> setContent(createDummyContent("Disha Page"), disha));
        pitches.setOnMouseClicked(event -> setContent(createDummyContent("Pitches Page"), pitches));
        funding.setOnMouseClicked(event -> setContent(createDummyContent("Funding Page"), funding));
        profileIcon.setOnMouseClicked(event -> openProfilePage());
        pitches.setOnMouseClicked(event -> openReelsPage());
    }

    private ImageView createImageView(String imagePath, int height, int width) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }

    private void setContent(ScrollPane content, ImageView activeIcon) {
        mainContent.getChildren().setAll(content);
        highlightActiveIcon(activeIcon);
    }

    private ScrollPane createDummyContent(String pageName) {
        VBox contentBox = new VBox(new Label(pageName));
        contentBox.setPadding(new Insets(10));
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    private void highlightActiveIcon(ImageView activeIcon) {
        for (Node node : navBar.getChildren()) {
            if (node instanceof ImageView) {
                node.setStyle("");
                ((ImageView) node).setFitHeight(50);
                ((ImageView) node).setFitWidth(50);
            }
        }
        activeIcon.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(135,206,235,0.8), 20, 0.5, 0, 0);");
        activeIcon.setFitHeight(60);
        activeIcon.setFitWidth(60);
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

    private void openMessagePage() {
        MessagePage Msg = new MessagePage();
        try {
            Msg.start(new Stage());
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callApiPost() {
        new Thread(() -> {
            try {
                String apiUrl = "https://brickzoneprop.com/WomenEM/API/getAllPosts.php";
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Post> posts = Arrays.asList(objectMapper.readValue(response.toString(), Post[].class));

                    for(Post post : posts) {
                        if (post.getContentImages() != null) {
                            feedItems.add(new FeedItem(post.getUserName(), post.getUserType(), 
                                          new Image(post.getProfileImage()), 
                                          new Image(post.getContentImages()),
                                          post.getLikes()));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class FeedItem {
        private final String author;
        private final String content;
        private final Image image;
        private final Image post;
        private final String likes;

        public FeedItem(String author, String content, Image image, Image post, String likes) {
            this.author = author;
            this.content = content;
            this.image = image;
            this.post = post;
            this.likes = likes;
        }

        public Image getPost() { return post; }
        public String getAuthor() { return author; }
        public String getContent() { return content; }
        public Image getImage() { return image; }
        public String getLikes() { return likes; }
    }

    private static class FeedItemCell extends ListCell<FeedItem> {
        @Override
        protected void updateItem(FeedItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
                setPrefHeight(USE_COMPUTED_SIZE);
            } else {
                VBox card = new VBox(0);
                card.setPadding(new Insets(0));

                HBox userinfo = new HBox(5);
                userinfo.setPadding(new Insets(0, 0, 0, 10));
                VBox position = new VBox();
                position.setPadding(new Insets(0,0,0,7));


                Label authorLabel = new Label(item.getAuthor());
                authorLabel.setStyle("-fx-font-weight: bold;");
                authorLabel.setPadding(new Insets(13,0,0,0));

                Label usertype = new Label(item.getContent());
                usertype.setFont(Font.font("Times New Roman", 19));
                usertype.setLayoutX(200);


                ImageView profileView = new ImageView(item.getImage());
                profileView.setFitHeight(60);
                profileView.setFitWidth(60);

                Image like = new Image("assets/images/like.gif");
                ImageView likeImage = new ImageView(like);
                likeImage.setFitHeight(50);
                likeImage.setFitWidth(50);
                Button likebtn = new Button();
                likebtn.setGraphic(likeImage);
                likebtn.setBackground(null);
                Label likeLabel = new Label(item.getLikes());
                likeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 19));
                likeLabel.setPadding(new Insets(13,0,0,0));

                HBox likes = new HBox(likebtn,likeLabel);


                position.getChildren().addAll(authorLabel,usertype);
                position.setSpacing(7);
                userinfo.getChildren().addAll(profileView,position);
                

                
               
                ImageView postView = new ImageView(item.getPost());
                postView.setFitHeight(600);
                postView.setFitWidth(600);
                postView.setX(50);
                // postView.setPreserveRatio(true);
                postView.setStyle("-fx-border-color: RED; -fx-border-radius: 10px; -fx-background-radius: 5px;");
                VBox Postimg = new VBox(10,postView,likes);
                Postimg.setPadding(new Insets(0,0,0,150));
                
               
                card.getChildren().addAll(userinfo,Postimg);
                card.setSpacing(30);

                card.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
                card.setStyle(" -fx-border-radius: 5px; -fx-background-radius: 5px;");


                // Add drop shadow to FeedItem card
                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(3);
                dropShadow.setOffsetY(3);
                dropShadow.setColor(Color.DARKGRAY);
                card.setEffect(dropShadow);
                
                setGraphic(card);
                setPrefHeight(800);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}