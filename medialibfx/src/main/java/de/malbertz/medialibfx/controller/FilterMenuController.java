package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.main.Context;
import de.malbertz.medialibfx.model.media.MediaFilter;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FilterMenuController implements Initializable {

  @FXML
  private TreeView<MediaFilter> treeView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    TreeItem<MediaFilter> root = new TreeItem<>(new MediaFilter("root"));
    treeView.setRoot(root);
    treeView.setShowRoot(false);
    initFilters(root);
    treeView.getSelectionModel().selectedItemProperty()
        .addListener((ChangeListener<TreeItem<MediaFilter>>) (observable,
            oldValue, newValue) -> {
          Context.getInstance().getMainController().getMediaListController()
              .applyFilter(newValue.getValue());
        });
  }

  private void initFilters(TreeItem<MediaFilter> root) {    
    MediaFilter videoFilter = new MediaFilter("Video")
        .type("video");
    MediaFilter audioFilter = new MediaFilter("Audio")
        .type("audio");
    MediaFilter audioTest = audioFilter.clone().name("Avengers").filterName("contains Avengers");
    TreeItem<MediaFilter> videoItem = new TreeItem<MediaFilter>(videoFilter);
    TreeItem<MediaFilter> audioItem = new TreeItem<MediaFilter>(audioFilter);
    audioItem.getChildren().add(new TreeItem<MediaFilter>(audioTest));
    root.getChildren().add(videoItem);
    root.getChildren().add(audioItem);
  }
  
}
