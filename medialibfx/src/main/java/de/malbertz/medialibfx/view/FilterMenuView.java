package de.malbertz.medialibfx.view;

import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.malbertz.medialibfx.controller.FilterMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FilterMenuView extends Pane {
  private static final Logger log = LoggerFactory
      .getLogger(MediaListView.class);

  private FilterMenuController controller;
  
  public FilterMenuView(ResourceBundle bundle) throws IOException {
    String fxml = "/fxml/FilterMenuView.fxml";
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    loader.setResources(bundle);
    loader.setRoot(this);
    try {
      loader.load();
    } catch (IOException e) {
      log.error("Failed to load {}. {}", fxml, e.getMessage());
      throw e;
    }
    this.controller = (FilterMenuController) loader.getController();
  }

  public FilterMenuController getController() {
    return controller;
  }

}
