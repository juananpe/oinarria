package ehu.isad.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTeachers;

    @FXML
    private Button btnFees;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnSettings;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Label lblStatusMin;

    @FXML
    private Label lblStatus;

    @FXML
    private FontAwesomeIconView btnClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnStudents) {
            lblStatusMin.setText("/home/students");
            lblStatus.setText("Students");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == btnTeachers) {
            lblStatusMin.setText("/home/teachers");
            lblStatus.setText("Teachers");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == btnFees) {
            lblStatusMin.setText("/home/fees");
            lblStatus.setText("Fees");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 99, 63), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == btnUsers) {
            lblStatusMin.setText("/home/users");
            lblStatus.setText("Users");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == btnSettings) {
            lblStatusMin.setText("/home/settings");
            lblStatus.setText("Settings");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(42, 28, 66), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void handleClose(MouseEvent event){
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }
}
