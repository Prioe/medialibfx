package de.malbertz.medialibfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.view.FilterMenuView;
import de.malbertz.medialibfx.view.MediaListView;
import de.malbertz.medialibfx.view.PlayMenuView;
import de.malbertz.medialibfx.view.alerts.ExceptionAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

  @FXML private BorderPane contentPane;
  
  private MediaListView mediaListView;
  private FilterMenuView filterMenuView;
  private PlayMenuView playMenuView;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      mediaListView = new MediaListView(resources);
      filterMenuView = new FilterMenuView(resources);
      playMenuView = new PlayMenuView(resources);
      contentPane.setCenter(mediaListView);
      contentPane.setLeft(filterMenuView);
      contentPane.setTop(playMenuView);
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
    }
  }
  
  public PlayMenuController getPlayMenuController() {
    return playMenuView.getController();
  }
  
  public MediaListController getMediaListController() {
    return mediaListView.getController();
  }
  
  public FilterMenuController getFilterMenuController() {
    return filterMenuView.getController();
  }
  
  public BorderPane getContentPane() {
    return contentPane;
  }

}
