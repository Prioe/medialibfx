package de.malbertz.medialibfx.model.player;

import de.malbertz.medialibfx.model.media.Media;
import javafx.scene.layout.Pane;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class MultiMediaPlayer {
  private ResizableVideoPlayer player;
  private Media playing;

  public MultiMediaPlayer() {
    player = new ResizableVideoPlayer();
  }

  public void start(Media media) {
    if (media.getMimeType().indexOf("video") >= 0) {
      player.start(media);
      playing = media;
    } else if (media.getMimeType().indexOf("audio") >= 0) {
      player.start(media);
      playing = media;
    }
  }

  public void pause() {
    player.pause();
  }
  
  public void stop() {
    player.stop();
    playing = null;
  }

  public void release() {
    player.release();
    playing = null;
  }

  public Media getPlayingMedia() {
    return playing;
  }
  
  public DirectMediaPlayer getMediaPlayer() {
    return player.getMediaPlayer();
  }
  
  public Pane getVideoHolder() {
    return player.getPlayerHolder();
  }

}
