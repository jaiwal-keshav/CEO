package com.code_cafe.HomePage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.stage.Stage;

public class Home extends Application {

    Label lb1 = new Label("Keshav");
    Label lb2 = new Label("Jaiwal");
    Label lb3 = new Label("Jaiwal");
    Label lb4 = new Label("Janhavi");
    Image image = new Image("assets/image.png"); // Ensure the path is correct

    private ObservableList<FeedItem> feedItems = FXCollections.observableArrayList(
        new FeedItem("User1", lb1.getText(), image),
        new FeedItem("User2", lb2.getText(), image),
        new FeedItem("User3", lb3.getText(), image),
        new FeedItem("User4", lb4.getText(), image)
    );

    private StackPane mainContent = new StackPane();
    private HBox navBar = new HBox(20); // Initialize navBar here

    @Override
    public void start(Stage primaryStage) {

        ListView<FeedItem> listView = new ListView<>(feedItems);
        listView.setCellFactory(param -> new FeedItemCell());
        listView.setSelectionModel(null); // Disable selection of list items
        listView.setFocusTraversable(false); // Disable focus traversable for list items

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Adding CSS style to hide the scrollbar
        scrollPane.getStyleClass().add("hidden-scrollbar");

        // Create the navigation bar
        navBar.setPadding(new Insets(10));
        navBar.setStyle("-fx-background-color: beige;");

        // Create navigation items
        ImageView msgIcon = new ImageView(new Image("assets/message.png"));
        msgIcon.setFitHeight(40); // Adjust the height to cover the whole HBox
        msgIcon.setFitWidth(50);

        ImageView disha = new ImageView(new Image("assets/chatbot.png"));
        disha.setFitHeight(40); // Adjust the height to cover the whole HBox
        disha.setFitWidth(50);

        ImageView skills = new ImageView(new Image("assets/skill.png"));
        skills.setFitHeight(40); // Adjust the height to cover the whole HBox
        skills.setFitWidth(50);

        ImageView funding = new ImageView(new Image("assets/fund.png"));
        funding.setFitHeight(40); // Adjust the height to cover the whole HBox
        funding.setFitWidth(50);

        ImageView home = new ImageView(new Image("assets/home.png"));
        home.setFitHeight(40); // Adjust the height to cover the whole HBox
        home.setFitWidth(50);

        ImageView pitches = new ImageView(new Image("assets/trending.png"));
        pitches.setFitHeight(40); // Adjust the height to cover the whole HBox
        pitches.setFitWidth(50);

        ImageView profileIcon = new ImageView(new Image("assets/profile.gif"));
        profileIcon.setFitHeight(40); // Adjust the height to cover the whole HBox
        profileIcon.setFitWidth(50);

        // Create spacer regions
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // Add items to the navigation bar
        navBar.getChildren().addAll(msgIcon, leftSpacer, skills, disha, home, pitches, funding, rightSpacer, profileIcon);

        // Add padding to the VBox
        VBox root = new VBox(navBar, mainContent);
        root.setPadding(new Insets(10));
        
        // Make the StackPane grow with the window
        VBox.setVgrow(mainContent, Priority.ALWAYS);
        root.setPrefSize(1600, 870);

        Group group = new Group(root);
        Scene scene = new Scene(group, 1900, 900);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollable Feed Page");
        primaryStage.show();

        // Set initial content
        setContent(scrollPane, home);

        // Add event handlers to switch content
        home.setOnMouseClicked(event -> setContent(scrollPane, home));
        msgIcon.setOnMouseClicked(event -> setContent(createDummyContent("Messages Page"), msgIcon));
        skills.setOnMouseClicked(event -> setContent(createDummyContent("Skills Page"), skills));
        disha.setOnMouseClicked(event -> setContent(createDummyContent("Disha Page"), disha));
        pitches.setOnMouseClicked(event -> setContent(createDummyContent("Pitches Page"), pitches));
        funding.setOnMouseClicked(event -> setContent(createDummyContent("Funding Page"), funding));
        profileIcon.setOnMouseClicked(event -> setContent(createDummyContent("Profile Page"), profileIcon));
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
            ((ImageView) node).setFitHeight(50); // Reset height
            ((ImageView) node).setFitWidth(50); // Reset width
        }
    }
    activeIcon.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(135,206,235,0.8), 20, 0.5, 0, 0);");
    activeIcon.setFitHeight(60); // Increase height
    activeIcon.setFitWidth(60); // Increase width
}

    
    private static class FeedItem {
        private final String author;
        private final String content;
        private final Image image;

        public FeedItem(String author, String content, Image image) {
            this.author = author;
            this.content = content;
            this.image = image;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public Image getImage() {
            return image;
        }
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
                VBox card = new VBox();
                card.setPadding(new Insets(10));
                card.setSpacing(10);

                HBox hBox = new HBox(10); // 10 is the spacing between elements
                hBox.setPadding(new Insets(10));

                Label authorLabel = new Label(item.getAuthor());
                authorLabel.setStyle("-fx-font-weight: bold;");

                Label contentLabel = new Label(item.getContent());

                ImageView imageView = new ImageView(item.getImage());
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                hBox.getChildren().addAll(imageView, authorLabel, contentLabel);

                TextArea textArea = new TextArea("This is a text area");
                textArea.setPrefHeight(50);
                textArea.setPrefWidth(300);
                card.getChildren().addAll(hBox, textArea);

                card.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
                card.setStyle("-fx-border-color: gray; -fx-border-radius: 5px; -fx-background-radius: 5px;");

                setGraphic(card);
                setPrefHeight(400); // Set the height of each cell to 400 pixels
            }
        }
    }

    // public static void main(String[] args) {
    //     launch(args);
    // }
}
