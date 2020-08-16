package ehu.isad;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

  //define your offsets here
  private double xOffset = 0;
  private double yOffset = 0;

  @Override
  public void start(Stage primaryStage) throws Exception{

    Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));

    //grab your root here
    root.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      }
    });

    //move around here
    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
      }
    });

    primaryStage.setTitle("ISAD Dashboard");
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
