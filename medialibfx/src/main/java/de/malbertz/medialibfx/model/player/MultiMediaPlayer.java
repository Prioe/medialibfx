package de.malbertz.medialibfx.model.player;

import de.malbertz.medialibfx.model.media.Media;
import javafx.scene.layout.Pane;

public class MultiMediaPlayer {
  private ResizableVideoPlayer player;

  public MultiMediaPlayer() {
    player = new ResizableVideoPlayer();
  }

  public void start(Media media) {
    if (media.getMimeType().indexOf("video") >= 0) {
      player.start(media);
    } else if (media.getMimeType().indexOf("audio") >= 0) {
      player.start(media);
    }
  }

  public void stop() {
    player.stop();
  }

  public void release() {
    player.release();

  }

  public Pane getVideoHolder() {
    return player.getPlayerHolder();
  }

}
