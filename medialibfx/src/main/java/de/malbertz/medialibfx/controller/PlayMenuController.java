package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class PlayMenuController implements Initializable {

  @FXML private Button nextButton;
  @FXML private Button prevButton;
  @FXML private Button playButton;
  @FXML private ImageView coverImageView;
  @FXML private Label durationLabel;
  @FXML private Label status1Label;
  @FXML private Label status2Label;
  boolean playing = false;
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    playButton.setOnAction(event -> {
      SVGPath svg = new SVGPath();
      if (!playing) {
        svg.setContent("M5,0 L0,20 L5,20 L5,0 L0,0 M10,0 L10,20 L15,20 L15,0, L10,0");
        svg.setFill(Color.BLACK);
        svg.getStyleClass().add("pause-shape");
      }
      playButton.setGraphic(svg);
      playing = !playing;
    });
    
  }

}
