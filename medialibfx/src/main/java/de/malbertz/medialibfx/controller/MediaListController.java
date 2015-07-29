package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.main.Context;
import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.MediaFilter;
import de.malbertz.medialibfx.model.media.MediaLoader;
import de.malbertz.medialibfx.view.MediaCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MediaListController implements Initializable {

  @FXML
  private TableView<Media> mediaTableView;

  private ObservableList<Media> mediaList;
  private FilteredList<Media> filteredMediaList;

  public Media getSelected() {
    return mediaTableView.getSelectionModel().getSelectedItem();
  }
  
  public void add(Media media) {
    mediaList.add(media);
  }
  
  public void applyFilter(MediaFilter filter) {
    filteredMediaList.setPredicate(filter.predicate());
    initColumns();
  }
  
  public boolean isEmpty() {
    return mediaTableView.getItems().isEmpty();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    mediaList = FXCollections.observableArrayList();
    filteredMediaList = mediaList.filtered(p -> {
      return true;
    });
    mediaTableView.setItems(filteredMediaList);
    mediaTableView.setRowFactory(tv -> {
      TableRow<Media> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && !row.isEmpty())
          Context.getInstance().getMainController().getPlayer().start(row.getItem());
        
      });
      return row;
    });
    mediaList.addAll(MediaLoader.fromXml());
    initColumns();

  }

  private void initColumns() {
    Map<String, String> columnProperties = new HashMap<>(5);
    for (Media media : filteredMediaList) {
      String[][] properties = media.properties();
      for (String[] property : properties) {
        columnProperties.put(property[0], property[1]);
      }

    }

    mediaTableView.getColumns().clear();
    for (Map.Entry<String, String> mime : columnProperties.entrySet()) {

      TableColumn<Media, ?> column = null;
      String property = mime.getKey(), type = mime.getValue();
      switch (type) {
      case "string":
        TableColumn<Media, String> stringColumn = new TableColumn<>(prettyHeader(property));
        stringColumn.setCellFactory(new MediaCellFactory<String>(property));
        column = stringColumn;
        break;
      case "int":
        TableColumn<Media, Integer> integerColumn = new TableColumn<>(prettyHeader(property));
        integerColumn.setCellFactory(new MediaCellFactory<Integer>(property));
        column = integerColumn;
        break;
      case "date":
        TableColumn<Media, Date> dateColumn = new TableColumn<>(prettyHeader(property));
        dateColumn.setCellFactory(new MediaCellFactory<Date>(property));
        column = dateColumn;
        break;
      case "strings":
        TableColumn<Media, String[]> stringsColumn = new TableColumn<>(prettyHeader(property));
        stringsColumn.setCellFactory(new MediaCellFactory<String[]>(property));
        column = stringsColumn;
        break;
      default:
        break;
      }
      if (column != null) 
        column.setCellValueFactory(new PropertyValueFactory<>(property));
      
      mediaTableView.getColumns().add(column);

    }
  };
  
  private String prettyHeader(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (i == 0) 
        sb.append(Character.toUpperCase(c));
      else if (Character.isUpperCase(c))
        sb.append(" ").append(c);
      else
        sb.append(c);
    }
    return sb.toString();
  }

}
