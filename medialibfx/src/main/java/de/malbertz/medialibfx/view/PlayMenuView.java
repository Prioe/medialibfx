package de.malbertz.medialibfx.view;

import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.malbertz.medialibfx.controller.PlayMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class PlayMenuView extends HBox {
  private static final Logger log = LoggerFactory
      .getLogger(MediaListView.class);

  private PlayMenuController controller;
  
  public PlayMenuView(ResourceBundle bundle) throws IOException {
    String fxml = "/fxml/PlayMenuView.fxml";
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    loader.setResources(bundle);
    loader.setRoot(this);
    try {
      loader.load();
    } catch (IOException e) {
      log.error("Failed to load {}. {}", fxml, e.getMessage());
      throw e;
    }
    this.controller = (PlayMenuController) loader.getController();
  }

  public PlayMenuController getController() {
    return controller;
  }

}
