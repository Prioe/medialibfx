package de.malbertz.medialibfx.model;

import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.player.ResizablePlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResizablePlayerTestApp extends Application {
  
  private static final String VIDEO_PATH = "D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv";
  private ResizablePlayer player;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    player = new ResizablePlayer();
    Scene scene = new Scene(player.getPlayerHolder());
    primaryStage.setScene(scene);
    player.start(new Video(VIDEO_PATH));
    primaryStage.show();
  }
  
  public static void main(String[] args) {
    Application.launch(args);
  }
  
}
