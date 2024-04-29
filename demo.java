package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Event Insert Form");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Event Name
        Label eventNameLabel = new Label("Event Name:");
        TextField eventNameField = new TextField();
        grid.add(eventNameLabel, 0, 0);
        grid.add(eventNameField, 1, 0);

        // Event Date
        Label eventDateLabel = new Label("Event Date:");
        DatePicker eventDateField = new DatePicker();
        grid.add(eventDateLabel, 0, 1);
        grid.add(eventDateField, 1, 1);

        // Start Time
        Label startTimeLabel = new Label("Start Time:");
        TextField startTimeField = new TextField();
        grid.add(startTimeLabel, 0, 2);
        grid.add(startTimeField, 1, 2);

        // End Time
        Label endTimeLabel = new Label("End Time:");
        TextField endTimeField = new TextField();
        grid.add(endTimeLabel, 0, 3);
        grid.add(endTimeField, 1, 3);

        // Venue
        Label venueLabel = new Label("Venue:");
        TextField venueField = new TextField();
        grid.add(venueLabel, 0, 4);
        grid.add(venueField, 1, 4);

        // Host
        Label hostLabel = new Label("Host:");
        TextField hostField = new TextField();
        grid.add(hostLabel, 0, 5);
        grid.add(hostField, 1, 5);

        // Description
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionField = new TextArea();
        grid.add(descriptionLabel, 0, 6);
        grid.add(descriptionField, 1, 6);

        // Eligibility
        Label eligibilityLabel = new Label("Eligibility:");
        TextField eligibilityField = new TextField();
        grid.add(eligibilityLabel, 0, 7);
        grid.add(eligibilityField, 1, 7);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 8);

        submitButton.setOnAction(event -> {
            // Perform database insertion when the button is clicked
            insertEventData(
                    eventNameField.getText(),
                    eventDateField.getValue(),
                    LocalTime.parse(startTimeField.getText()),
                    LocalTime.parse(endTimeField.getText()),
                    venueField.getText(),
                    hostField.getText(),
                    descriptionField.getText(),
                    Integer.parseInt(eligibilityField.getText())
            );
        });

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void insertEventData(String eventName, LocalDate eventDate, LocalTime startTime, LocalTime endTime,
                                 String venue, String host, String description, int eligibility) {
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO Event (EventName, EventDate, StartTime, EndTime, Venue, Host, Description, Eligibility) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, eventName);
                preparedStatement.setDate(2, java.sql.Date.valueOf(eventDate));
                preparedStatement.setTime(3, java.sql.Time.valueOf(startTime));
                preparedStatement.setTime(4, java.sql.Time.valueOf(endTime));
                preparedStatement.setString(5, venue);
                preparedStatement.setString(6, host);
                preparedStatement.setString(7, description);
                preparedStatement.setInt(8, eligibility);

                preparedStatement.executeUpdate();
                System.out.println("Event data inserted into the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}