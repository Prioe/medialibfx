package de.malbertz.medialibfx.model;

import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.player.AnimationTimerDirectRendering;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DirectRenderingTestApp extends Application {

  private AnimationTimerDirectRendering directRendering;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    directRendering = new AnimationTimerDirectRendering();
    Pane pane = new Pane();
    pane.getChildren().add(directRendering);
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    
    directRendering.start(new Video("D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv"));
    
    primaryStage.show();
    
  }
  
  @Override
  public void stop() {
    directRendering.release();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
  
}
