package com.code_cafe.MentorList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.code_cafe.Database.Mentor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.stage.Stage;

public class UserList extends Application {

    private ObservableList<User> userList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        ListView<User> listView = new ListView<>(userList);

        listView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> listView) {
                return new UserCell();
            }
        });
        listView.setPrefSize(1900, 1000);

        VBox root = new VBox(listView);
        root.setPadding(new Insets(40, 70, 30, 70));
        Scene scene = new Scene(root, 1900, 1000);
        root.setStyle("-fx-background: beige; -fx-background-color: beige;");
        primaryStage.setTitle("User List");
        primaryStage.setScene(scene);

        primaryStage.show();

        loadUsers();
    }

    private void loadUsers() {
        callApi();
    }

    public static class User {
        private final String name;
        private final String info;
        private final String category;
        private final String imageUrl;

        public User(String name, String info, String category, String imageUrl) {
            this.name = name;
            this.info = info;
            this.category = category;
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public String getInfo() {
            return info;
        }

        public String getCategory() {
            return category;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class UserCell extends ListCell<User> {
        private final HBox hbox;
        private final ImageView imageView;
        private final VBox vbox;
        private final Label nameLabel;
        private final Label infoLabel;
        private final Label categoryLabel;

        public UserCell() {
            super();
            imageView = new ImageView();
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            Circle clip = new Circle(40, 40, 40);
            imageView.setClip(clip);

            nameLabel = new Label();
            infoLabel = new Label();
            categoryLabel = new Label();

            vbox = new VBox(nameLabel, infoLabel, categoryLabel);
            vbox.setSpacing(5);

            hbox = new HBox(imageView, vbox);
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10, 10, 10, 10));
        }

        @Override
        protected void updateItem(User user, boolean empty) {
            super.updateItem(user, empty);

            if (empty || user == null) {
                setGraphic(null);
            } else {
                nameLabel.setText(user.getName());
                infoLabel.setText(user.getInfo());
                categoryLabel.setText(user.getCategory());
                imageView.setImage(new Image(user.getImageUrl()));
                setGraphic(hbox);
            }
        }
    }

    private void callApi() {
        new Thread(() -> {
            try {
                String apiUrl = "https://brickzoneprop.com/WomenEM/APIS/getMentors.php"; // Replace with your API URL
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

                    // Parse JSON response to List<Mentor>
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Mentor> mentors = Arrays.asList(objectMapper.readValue(response.toString(), Mentor[].class));

                    for (Mentor mentor : mentors) {
                        // Assuming you want to use the profile image URL from the JSON response
                        String profileImageUrl = mentor.getProfileImg();

                        // Add new User object to the userList
                        User user = new User(mentor.getUserName(), mentor.getBio(), mentor.getInterestedDomain(), profileImageUrl);
                        userList.add(user);
                    }
                } else {
                    System.err.println("GET request failed: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
