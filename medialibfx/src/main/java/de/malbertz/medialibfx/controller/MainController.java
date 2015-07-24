package de.malbertz.medialibfx.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import de.malbertz.medialibfx.model.media.Audio;
import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;
import de.malbertz.medialibfx.model.player.MultiMediaPlayer;
import de.malbertz.medialibfx.model.properties.PropertyManager;
import de.malbertz.medialibfx.model.properties.xml.XmlParser;
import de.malbertz.medialibfx.view.FilterMenuView;
import de.malbertz.medialibfx.view.MediaListView;
import de.malbertz.medialibfx.view.PlayMenuView;
import de.malbertz.medialibfx.view.alerts.ExceptionAlert;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MainController implements Initializable {

  @FXML
  private BorderPane contentPane;
  @FXML
  private TabPane centerContentPane;
  @FXML
  private MenuBar menuBar;

  private MediaListView mediaListView;
  private FilterMenuView filterMenuView;
  private PlayMenuView playMenuView;
  private MultiMediaPlayer player;

  @FXML
  private void importFiles() {
    FileChooser fileChooser = new FileChooser();

    List<File> list = fileChooser
        .showOpenMultipleDialog(contentPane.getScene().getWindow());
    if (list == null)
      return;
    for (File file : list) {
      System.out.println(file);
    }
  }

  @FXML
  private void importDirectoryRecursive() {
    DirectoryChooser dirChooser = new DirectoryChooser();
    File dir = dirChooser.showDialog(contentPane.getScene().getWindow());
    if (dir == null) return;
    Task<Object> t = new Task<Object>() {
      @Override
      protected Object call() throws Exception {
        try {
          List<File> list = Files.walk(Paths.get(dir.toString())).filter(Files::isRegularFile)
              .map(Path::toFile).collect(Collectors.toList());
          List<Media> mediaList = new ArrayList<>();
          for (File file : list) {
            try {
              Media media = MediaInfoLoader.fromFile(file);
              mediaListView.getController().add(media);
              mediaList.add(media);
            } catch (IllegalArgumentException e) {
            } catch (FileNotFoundException e) {              
            }
          }
          XmlParser.writeToXml(mediaList);
        } catch (IOException e) {
          new ExceptionAlert(e).showAndWait();
        }
        return null;
      }
    };
    new Thread(t).start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      mediaListView = new MediaListView(resources);
      filterMenuView = new FilterMenuView(resources);
      playMenuView = new PlayMenuView(resources);
      player = new MultiMediaPlayer();
      playMenuView.getController().setMediaPlayer(player);
      
      centerContentPane.getTabs()
          .add(new Tab(resources.getString("window.internalplayer.tabname"),
              player.getVideoHolder()));
      centerContentPane.getTabs().add(new Tab(
          resources.getString("window.medialist.tabname"), mediaListView));
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
        player.start(new Video(
            "D:\\Downloads\\hgbgh06nvfhg70ecdfc\\hgbgh06nvfhg70ecdfc\\E01.mkv"));
      } catch (Exception e) {
        new ExceptionAlert(e).showAndWait();
      }
    });
    MenuItem testAudio = new MenuItem("Test Audio");
    testAudio.setOnAction(event -> {
      try {
        player.start(new Audio(
            "D:\\Musik\\Metal\\Slipknot\\1999 - Slipknot\\02. (SIC).mp3"));
      } catch (Exception e) {
        new ExceptionAlert(e).showAndWait();
      }
    });
    MenuItem testYouTube = new MenuItem("Test YouTube");
    testYouTube.setOnAction(event -> {
      try {
        
      } catch (Exception e) {
        new ExceptionAlert(e).showAndWait();
      }
    });
    devMenu.getItems().addAll(testVideo, testAudio, testYouTube);
  }

  public MultiMediaPlayer getPlayer() {
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
