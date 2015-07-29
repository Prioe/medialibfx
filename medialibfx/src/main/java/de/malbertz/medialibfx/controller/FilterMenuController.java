package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.main.Context;
import de.malbertz.medialibfx.model.media.MediaFilter;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

public class FilterMenuController implements Initializable {

  @FXML
  private TreeView<MediaFilter> filterTreeView;
  @FXML
  private Pane resizePane;

  private double dragOffset;
  private double startingWidth;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    initResize();

    TreeItem<MediaFilter> root = new TreeItem<>(new MediaFilter("root"));
    filterTreeView.setRoot(root);
    filterTreeView.setShowRoot(false);
    initFilters(root);
    filterTreeView.getSelectionModel().selectedItemProperty()
        .addListener((ChangeListener<TreeItem<MediaFilter>>) (observable,
            oldValue, newValue) -> {
          Context.getInstance().getMainController().getMediaListController()
              .applyFilter(newValue.getValue());
        });
  }

  private void initResize() {
    
    filterTreeView.prefWidthProperty().bind(resizePane.widthProperty().subtract(5));
    filterTreeView.prefHeightProperty().bind(resizePane.heightProperty());
    
    filterTreeView.setOnMouseEntered(t -> {
      resizePane.setCursor(Cursor.DEFAULT);
      t.consume();
    });
    
    filterTreeView.setOnMouseExited(t -> {
      resizePane.setCursor(Cursor.H_RESIZE);
      t.consume();
    });
    
    resizePane.setOnMouseEntered(t -> {
      resizePane.setCursor(Cursor.H_RESIZE);
      t.consume();
    });
    resizePane.setOnMouseExited(t -> {
      resizePane.setCursor(Cursor.DEFAULT);
      t.consume();
    });
    resizePane.setOnMousePressed(t -> {
      dragOffset = t.getX();
      startingWidth = resizePane.getWidth();
      t.consume();
    });
    resizePane.setOnMouseDragged(t -> {
      double newWidth = startingWidth + (t.getX() - dragOffset);
      resizePane.setPrefWidth(newWidth);
      t.consume();
    });
  }

  private void initFilters(TreeItem<MediaFilter> root) {
    MediaFilter videoFilter = new MediaFilter("Video").mimeType("video");
    MediaFilter audioFilter = new MediaFilter("Audio").mimeType("audio");
    MediaFilter audioTest = new MediaFilter("contains Avengers", audioFilter)
        .name("Avengers");
    TreeItem<MediaFilter> videoItem = new TreeItem<MediaFilter>(videoFilter);
    TreeItem<MediaFilter> audioItem = new TreeItem<MediaFilter>(audioFilter);
    audioItem.getChildren().add(new TreeItem<MediaFilter>(audioTest));
    root.getChildren().add(videoItem);
    root.getChildren().add(audioItem);
  }

}
