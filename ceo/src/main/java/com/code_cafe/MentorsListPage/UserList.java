package com.code_cafe.MentorsListPage;



import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
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
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;

public class UserList extends Application {

    @Override
    public void start(Stage primaryStage) {
        ListView<User> listView = new ListView<>();
        
        listView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> listView) {
                return new UserCell();
            }
        });
        listView.setPrefSize(1900, 1000);
        
        VBox root = new VBox(listView);
        root.setPadding(new Insets(40,70,30,70));
        Scene scene = new Scene(root, 1900, 1000);
        root.setStyle("-fx-background: beige; -fx-background-color: beige;");
        primaryStage.setTitle("User List");
        primaryStage.setScene(scene);
        
        primaryStage.show();

        loadUsers(listView);
    }
   
    private void loadUsers(ListView<User> listView) {
        // Sample data; replace with database fetch logic
        List<User> users = new ArrayList<>();
        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man2.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man2.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man2.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man2.png"));
        
        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man2.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man2.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man2.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man2.png"));

        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man2.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man2.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man.png"));

        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man.png"));

        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man.png"));

        users.add(new User("Jane Smith", "I love analyzing data and finding insights.", "Data Science & Analytics", "assets/man.png"));
        users.add(new User("Sarha Shah", "Dedicated to creating user-centric design solutions.", "Art and Creative Industries", "assets/man.png"));
        users.add(new User("Pooja Smith", "Digital marketing enthusiast with a focus on SEO and SEM strategies.", "Retail and E-commerce", "assets/man.png"));
        users.add(new User("Oliver Taylor", "Experienced supply chain professional with expertise in logistics and operations.", "Manufacturing and Production", "assets/man.png"));
        listView.getItems().addAll(users);
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

    
    
}
