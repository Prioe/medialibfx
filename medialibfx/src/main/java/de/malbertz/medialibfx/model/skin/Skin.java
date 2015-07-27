package de.malbertz.medialibfx.model.skin;

import java.util.List;

import javafx.scene.Node;

public interface Skin {
  Node pauseGraphic();
  Node playGraphic();
  Node prevGraphic();
  Node nextGraphic();
  Node stopGraphic();
  Node fastforwardGraphic();
  Node rewindGraphic();
  Node muteGraphic();
  Node lowVolumeGraphic();
  Node midVolumeGraphic();
  Node highVolumeGraphic();
  Node shuffleGraphic();
  Node repeatGraphic();
  List<String> getStylesheets();
}
