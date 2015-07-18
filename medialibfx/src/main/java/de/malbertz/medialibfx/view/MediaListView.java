package de.malbertz.medialibfx.view;

import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.malbertz.medialibfx.controller.MediaListController;
import de.malbertz.medialibfx.model.media.Media;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

public class MediaListView extends TableView<Media> {
  private static final Logger log = LoggerFactory
      .getLogger(MediaListView.class);
  
  private MediaListController controller;
  
  public MediaListView(ResourceBundle bundle) throws IOException {
    String fxml = "/fxml/MediaListView.fxml";
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    loader.setResources(bundle);
    loader.setRoot(this);
    try {
      loader.load();
    } catch (IOException e) {
      log.error("Failed to load {}. {}", fxml, e.getMessage());
      throw e;
    }
    controller = (MediaListController) loader.getController();
  }
  
  public MediaListController getController() {
    return controller;
  }
}
