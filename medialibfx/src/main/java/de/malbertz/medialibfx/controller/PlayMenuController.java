package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.main.Context;
import de.malbertz.medialibfx.model.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlayMenuController implements Initializable {

  @FXML private Button nextButton;
  @FXML private Button prevButton;
  @FXML private Button playButton;
  @FXML private ImageView coverImageView;
  @FXML private Label durationLabel;
  @FXML private Label status1Label;
  @FXML private Label status2Label;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    playButton.setOnAction(event -> {
      Media media = Context.getInstance().getMainController().getMediaListController().getSelected();
      if (media == null) return;
      Context.getInstance().getMainController().start(media);
    });
    
  }

}
