package de.malbertz.medialibfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import de.malbertz.medialibfx.model.player.MultiMediaPlayer;
import de.malbertz.medialibfx.model.properties.PropertyManager;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;

public class PlayMenuController implements Initializable {

  @FXML
  private Button nextButton;
  @FXML
  private Button prevButton;
  @FXML
  private Button playButton;
  @FXML
  private ImageView coverImageView;
  @FXML
  private Label durationLabel;
  @FXML
  private Label status1Label;
  @FXML
  private Label status2Label;
  @FXML
  private Slider volumeSlider;
  @FXML
  private Slider durationSlider;

  private MultiMediaPlayer player;
  private DirectMediaPlayer mediaPlayer;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // all initialization is happening when the mediaplayer is set
  }

  private void initMediaPlayer() {
    mediaPlayer.setVolume(
        Integer.parseInt(PropertyManager.get("window.player.volume")));
    mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void playing(MediaPlayer player) {
        durationSlider.setMax(mediaPlayer.getLength());
        Platform.runLater(() -> {
          while (playButton.getStyleClass().remove("play-button")) {
          }
          playButton.getStyleClass().add("pause-button");
        });
      }

      @Override
      public void paused(MediaPlayer player) {
        Platform.runLater(() -> {
          while (playButton.getStyleClass().remove("pause-button")) {
          }
          playButton.getStyleClass().add("play-button");
        });
      }

      @Override
      public void timeChanged(MediaPlayer player, long newTime) {
        updateComponents(newTime);
      }
    });
  }

  private void initButtons() {
    playButton.setOnAction(event -> {
      player.pause();
    });
    playButton.getStyleClass().add("play-button");
  }

  private void initSliders() {
    volumeSlider.setValue(
        Double.parseDouble(PropertyManager.get("window.player.volume")));
  }

  private long lastSliderUpdate = System.currentTimeMillis(); 
  private void initLabels() {
    volumeSlider.valueProperty().addListener(o -> {
      Platform.runLater(() -> {
        int vol = mediaPlayer.getVolume();
        volumeSlider.setValue((double) vol);
        try {
          PropertyManager.set("window.player.volume", vol + "");
        } catch (IOException e) {
        }
        // TODO change volume icons here
      });
    });
    volumeSlider.valueProperty().addListener(
        (ChangeListener<Number>) (observable, oldValue, newValue) -> {
          mediaPlayer.setVolume(newValue.intValue());
        });
    
    durationSlider.valueProperty().addListener(o -> {
      
      Platform.runLater(() -> {
        long now = System.currentTimeMillis();
        if (!durationSlider.isPressed() || now - lastSliderUpdate <= 10)
          return;
        mediaPlayer.setTime((long) durationSlider.getValue());
        lastSliderUpdate = now;
      });
    });
  }

  private void updateComponents(long time) {
    Platform.runLater(() -> {
      durationSlider.setValue(time);
      durationLabel.setText(timeFormat(time));
    });
  }
  
  private String timeFormat(long time) {
    if (time >= 3600000) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time)%60,
                TimeUnit.MILLISECONDS.toSeconds(time)%60);
    } else {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time)%60);
    }
}

  public void setMediaPlayer(MultiMediaPlayer player) {
    this.player = player;
    this.mediaPlayer = player.getMediaPlayer();
    initMediaPlayer();
    initButtons();
    initLabels();
    initSliders();
  }

}
