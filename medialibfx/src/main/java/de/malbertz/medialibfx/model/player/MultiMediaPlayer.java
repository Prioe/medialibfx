package de.malbertz.medialibfx.model.player;

import de.malbertz.medialibfx.model.media.Media;
import javafx.scene.layout.Pane;

public class MultiMediaPlayer {
  private ResizableVideoPlayer videoPlayer;
  private AudioPlayer audioPlayer;

  public MultiMediaPlayer() {
    videoPlayer = new ResizableVideoPlayer();
    audioPlayer = new AudioPlayer();
  }

  public void start(Media media) {
    if (media.getMimeType().indexOf("video") >= 0) {
      stop();
      videoPlayer.start(media);
    } else if (media.getMimeType().indexOf("audio") >= 0) {
      
    }
  }

  public void stop() {
    audioPlayer.stop();
    videoPlayer.stop();
  }

  public void release() {
    audioPlayer.release();
    videoPlayer.release();
  }

  public Pane getVideoHolder() {
    return videoPlayer.getPlayerHolder();
  }
  
}
