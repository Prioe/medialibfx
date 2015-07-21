package de.malbertz.medialibfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.model.media.Audio;
import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.player.ResizablePlayer;
import de.malbertz.medialibfx.model.properties.PropertyManager;
import de.malbertz.medialibfx.view.FilterMenuView;
import de.malbertz.medialibfx.view.MediaListView;
import de.malbertz.medialibfx.view.PlayMenuView;
import de.malbertz.medialibfx.view.alerts.ExceptionAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

  @FXML private BorderPane contentPane;
  @FXML private TabPane centerContentPane;
  @FXML private MenuBar menuBar;
  
  private MediaListView mediaListView;
  private FilterMenuView filterMenuView;
  private PlayMenuView playMenuView;
  private ResizablePlayer player;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      mediaListView = new MediaListView(resources);
      filterMenuView = new FilterMenuView(resources);
      playMenuView = new PlayMenuView(resources);
      player = new ResizablePlayer();
      centerContentPane.getTabs().add(new Tab(resources.getString("window.internalplayer.tabname"), player.getPlayerHolder()));
      centerContentPane.getTabs().add(new Tab(resources.getString("window.medialist.tabname"), mediaListView));
      contentPane.setLeft(filterMenuView);
      contentPane.setTop(playMenuView);   
      
      if (PropertyManager.get("debug.devmode").indexOf("true") >= 0) {
        initDevMode();
      }
      
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
    }
  }
  
  private void initDevMode() {
    Menu devMenu = new Menu("Debug");
    menuBar.getMenus().add(devMenu);
    MenuItem testVideo = new MenuItem("Test Video");
    testVideo.setOnAction(event -> {
      try {
        centerContentPane.getSelectionModel().select(0);
        player.start(new Video("D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv"));
      } catch (Exception e) {
        new ExceptionAlert(e).showAndWait();
      }
    });
    MenuItem testAudio = new MenuItem("Test Audio - not functional");
    testAudio.setOnAction(event -> {
      try {
        centerContentPane.getSelectionModel().select(0);
        player.start(new Audio("D:\\Musik\\Metal\\Slipknot\\1999 - Slipknot\\02. (SIC).mp3"));
      } catch (Exception e) {
        new ExceptionAlert(e).showAndWait();
      }
    });
    devMenu.getItems().addAll(testVideo, testAudio);
  }
  
  public ResizablePlayer getPlayer() {
    return player;
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
