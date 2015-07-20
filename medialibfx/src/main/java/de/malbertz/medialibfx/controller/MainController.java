package de.malbertz.medialibfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.main.Context;
import de.malbertz.medialibfx.model.player.Launcher;
import de.malbertz.medialibfx.model.player.internal.InternalLauncher;
import de.malbertz.medialibfx.view.FilterMenuView;
import de.malbertz.medialibfx.view.MediaListView;
import de.malbertz.medialibfx.view.PlayMenuView;
import de.malbertz.medialibfx.view.alerts.ExceptionAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

  @FXML private BorderPane contentPane;
  @FXML private TabPane centerContentPane;
  
  private ResourceBundle resources;
  
  private MediaListView mediaListView;
  private FilterMenuView filterMenuView;
  private PlayMenuView playMenuView;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resources = resources;
    try {
      mediaListView = new MediaListView(resources);
      filterMenuView = new FilterMenuView(resources);
      playMenuView = new PlayMenuView(resources);
      centerContentPane.getTabs().add(new Tab(resources.getString("window.medialist.tabname"), mediaListView));
      contentPane.setLeft(filterMenuView);
      contentPane.setTop(playMenuView);      
      final InternalLauncher launcher = new InternalLauncher(); 
      Context.getInstance().setDirectRendering(launcher);
      centerContentPane.getTabs().add(new Tab(resources.getString("window.internalplayer.tabname"), launcher));
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
