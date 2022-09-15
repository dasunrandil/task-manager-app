package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashFormController {
    public Rectangle pgbContainer;
    public Rectangle pgbBar;
    public Label lblStatus;

    public void initialize(){
        Timeline tl = new Timeline();
        var keyFrame1 = new KeyFrame(Duration.millis(500), new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                pgbBar.setWidth(pgbBar.getWidth() + 250);
                lblStatus.setText("Setting up the UI");
            }
        });
        var keyFrame2 = new KeyFrame(Duration.millis(1000), new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                pgbBar.setWidth(pgbBar.getWidth() + 250);
            }
        });
        var keyFrame3 = new KeyFrame(Duration.millis(1250), new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                pgbBar.setWidth(pgbContainer.getWidth());
                lblStatus.setText("Please wait");
            }
        });
        var keyFrame4 = new KeyFrame(Duration.millis(1500), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                URL resource = this.getClass().getResource("/view/MainForm.fxml");
                try {
                    AnchorPane mainForm = FXMLLoader.load(resource);
                    Scene scene = new Scene(mainForm);
                    Stage stage = new Stage(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setTitle("Google Task Clone App");
                    stage.setResizable(false);
                    stage.show();
                    stage.centerOnScreen();
                    lblStatus.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tl.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
        tl.playFromStart();
    }
}
