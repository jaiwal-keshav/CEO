package com.code_cafe.Database;


// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;

// public class ApiCallApp extends Application {

//     private Label responseLabel = new Label("Response will appear here...");

//     @Override
//     public void start(Stage primaryStage) {
//         Button callApiButton = new Button("Call API");
//         callApiButton.setOnAction(e -> callApi());

//         VBox root = new VBox(10, callApiButton, responseLabel);
//         Scene scene = new Scene(root, 400, 200);

//         primaryStage.setTitle("API Call Example");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     private void callApi() {
//         new Thread(() -> {
//             try {
//                 String apiUrl = "https://brickzoneprop.com/WomenEM/APIS/add_post1.php"; // Replace with your API URL
//                 URL url = new URL(apiUrl);
//                 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                 conn.setRequestMethod("GET");

//                 int responseCode = conn.getResponseCode();
//                 if (responseCode == HttpURLConnection.HTTP_OK) {
//                     BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                     String inputLine;
//                     StringBuilder response = new StringBuilder();

//                     while ((inputLine = in.readLine()) != null) {
//                         response.append(inputLine);
//                     }
//                     in.close();

//                     // Update the UI with the response
//                     Platform.runLater(() -> responseLabel.setText(response.toString()));
//                 } else {
//                     Platform.runLater(() -> responseLabel.setText("GET request failed: " + responseCode));
//                 }
//             } catch (Exception e) {
//                 Platform.runLater(() -> responseLabel.setText("Exception: " + e.getMessage()));
//             }
//         }).start();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }



import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ApiCallApp extends Application {

    private Label responseLabel = new Label("Response will appear here...");

    @Override
    public void start(Stage primaryStage) {
        Button callApiButton = new Button("Call API");
        callApiButton.setOnAction(e -> callApi());

        VBox root = new VBox(10, callApiButton, responseLabel);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("API Call Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void callApi() {
        new Thread(() -> {
            try {
                String apiUrl = "https://brickzoneprop.com/WomenEM/APIS/getUsers1.php"; // Replace with your API URL
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

                    // Parse JSON response to List<User>
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<User> users = Arrays.asList(objectMapper.readValue(response.toString(), User[].class));

                    for(User user : users) {
                        System.out.println(user.getAge() + " " + user.getUserName());
                    }
                    // Update the UI with the response
                    Platform.runLater(() -> responseLabel.setText(users.toString()));
                } else {
                    Platform.runLater(() -> responseLabel.setText("GET request failed: " + responseCode));
                }
            } catch (Exception e) {
                Platform.runLater(() -> responseLabel.setText("Exception: " + e.getMessage()));
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
