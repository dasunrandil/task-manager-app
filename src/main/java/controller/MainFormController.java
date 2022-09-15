package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class MainFormController {
    public AnchorPane pneTitleBar;
    public Label lblClose;
    public Label lblMinimize;
    public JFXTextField txtInput;
    public JFXButton btnAddNewTask;
    public VBox vboxItemsContainer;
    private double mouseXOffset = -1;
    private double mouseYOffset = -1;


    public void pneTitleBarOnMouseDragged(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            var stage = (Stage) lblClose.getScene().getWindow();
            stage.setX(mouseEvent.getScreenX() - mouseXOffset);
            stage.setY(mouseEvent.getScreenY() - mouseYOffset);
        }
    }

    public void pneTitleBarOnMouseMoved(MouseEvent mouseEvent) {
    }

    public void pneTitleBarOnMousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            mouseXOffset = mouseEvent.getX();
            mouseYOffset = mouseEvent.getY();
            pneTitleBar.setCursor(Cursor.MOVE);
        }
    }

    public void pneTitleBarOnMouseReleased(MouseEvent mouseEvent) {
        pneTitleBar.setCursor(Cursor.DEFAULT);
    }

    public void lblCloseOnMouseReleased(MouseEvent mouseEvent) {
        lblClose.getScene().getWindow().hide();
    }

    public void lblOnMouseReleased(MouseEvent mouseEvent) {
        ((Stage) (lblClose.getScene().getWindow())).setIconified(true);
    }

    public void btnAddNewTaskOnAction(ActionEvent actionEvent) {
        if (txtInput.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Please enter something to add").show();
            txtInput.requestFocus();
            txtInput.selectAll();
            return;
        }
        JFXCheckBox checkbox = new JFXCheckBox(txtInput.getText());

        InputStream trashBinRes = this.getClass().getResourceAsStream("/image/trash-bin.png");
        Image trashBinImg = new Image(trashBinRes);
        ImageView trashBinImgView = new ImageView(trashBinImg);
        trashBinImgView.setFitWidth(24);
        trashBinImgView.setFitHeight(24);
        trashBinImgView.setVisible(false);
        trashBinImgView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vboxItemsContainer.getChildren().remove(trashBinImgView.getParent());
            }
        });

        HBox hBox = new HBox(checkbox, trashBinImgView);
        hBox.setPrefWidth(vboxItemsContainer.getWidth());
        hBox.setPadding(new Insets(15));
        vboxItemsContainer.getChildren().add(hBox);

        checkbox.setPrefWidth(vboxItemsContainer.getWidth() - 24);

        hBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hBox.setBackground(Background.fill(Color.GRAY));
                hBox.setCursor(Cursor.HAND);
                hBox.getChildren().get(1).setVisible(true);
            }
        });

        hBox.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hBox.setBackground(Background.fill(Color.TRANSPARENT));
                hBox.setCursor(Cursor.DEFAULT);
                hBox.getChildren().get(1).setVisible(false);
            }
        });
        txtInput.clear();
        txtInput.requestFocus();
    }
}
