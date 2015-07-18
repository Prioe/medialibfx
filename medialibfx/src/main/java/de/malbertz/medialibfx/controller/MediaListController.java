package de.malbertz.medialibfx.controller;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.MediaFilter;
import de.malbertz.medialibfx.model.properties.xml.XmlParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MediaListController implements Initializable {

  @FXML
  private TableView<Media> mediaTableView;

  private ObservableList<Media> mediaList;
  private FilteredList<Media> filteredMediaList;

  public void applyFilter(MediaFilter filter) {
    filteredMediaList.setPredicate(filter.predicate());
    initColumns();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    mediaList = FXCollections.observableArrayList();
    filteredMediaList = mediaList.filtered(p -> {
      return true;
    });
    mediaTableView.setItems(filteredMediaList);

    mediaList.addAll(XmlParser.loadFromXml());
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
        column = new TableColumn<Media, String>(property);

        break;
      case "int":
        column = new TableColumn<Media, Integer>(property);
        break;
      case "date":
        column = new TableColumn<Media, Date>(property);
        break;
      case "strings":
        column = new TableColumn<Media, String[]>(property);
        break;
      default:
        break;
      }
      if (column != null)
        column.setCellValueFactory(new PropertyValueFactory<>(property));
      mediaTableView.getColumns().add(column);

    }
  };

}
