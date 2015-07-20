package de.malbertz.medialibfx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.player.AnimationTimerDirectRendering;
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
  
  private MediaListView mediaListView;
  private FilterMenuView filterMenuView;
  private PlayMenuView playMenuView;
  private AnimationTimerDirectRendering directRendering;
  
  @FXML private void testMediaPlayer() {
    try {
      directRendering.start(new Video("D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      mediaListView = new MediaListView(resources);
      filterMenuView = new FilterMenuView(resources);
      playMenuView = new PlayMenuView(resources);
      directRendering = new AnimationTimerDirectRendering();
      centerContentPane.getTabs().add(new Tab(resources.getString("window.medialist.tabname"), mediaListView));
      contentPane.setLeft(filterMenuView);
      contentPane.setTop(playMenuView);      
      centerContentPane.getTabs().add(new Tab(resources.getString("window.internalplayer.tabname"), directRendering));
      
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
    }
  }
  
  public AnimationTimerDirectRendering getDirectRendering() {
    return directRendering;
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
