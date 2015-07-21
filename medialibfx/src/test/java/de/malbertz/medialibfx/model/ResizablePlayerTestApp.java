package de.malbertz.medialibfx.model;

import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.player.MultiMediaPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResizablePlayerTestApp extends Application {
  
  private static final String VIDEO_PATH = "D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv";
  private MultiMediaPlayer player;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    player = new MultiMediaPlayer();    
    Scene scene = new Scene(player.getVideoHolder());
    player.start(new Video(VIDEO_PATH));
    primaryStage.setScene(scene);    
    primaryStage.show();
  }
  
  @Override
  public void stop() {
    Platform.runLater(() -> player.release());
  }
  
  public static void main(String[] args) {
    Application.launch(args);
  }
  
}
