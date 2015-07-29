package de.malbertz.medialibfx.view;

import java.util.concurrent.TimeUnit;

import org.controlsfx.control.Rating;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.skin.Skin;
import de.malbertz.medialibfx.model.skin.SkinFactory;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class MediaCellFactory<R>
    implements Callback<TableColumn<Media, R>, TableCell<Media, R>> {

  private static final Skin skin = SkinFactory.getSkin();
  private final String property;

  public MediaCellFactory(String property) {
    this.property = property;
  }

  @Override
  public TableCell<Media, R> call(TableColumn<Media, R> param) {
    switch (property) {
    case "duration":
      return new DurationCell();
    case "bitRate":
      return new BitRateCell();
    case "rating":
      return new RatingCell();
    case "mimeType":
      return new MimeTypeCell();
    default:
      return new DefaultCell();
    }

  }
  
  private final class MimeTypeCell extends TableCell<Media, R> {
    @Override
    protected void updateItem(R item, boolean empty) {
      super.updateItem(item, empty);
      if (!(item instanceof String))
        return;
      String s = (String) item;
      if (s.indexOf("audio") >= 0)
        setGraphic(skin.audioGraphic());
      else if (s.indexOf("video") >= 0)
        setGraphic(skin.videoGraphic());
      else 
        setGraphic(skin.unknownGraphic());
    }
  }

  private final class RatingCell extends TableCell<Media, R> {
    @Override
    protected void updateItem(R item, boolean empty) {
      super.updateItem(item, empty);
      if (!(item instanceof Integer))
        return;
      int i = (int) item;
      Rating rating = new Rating(5, i);
      setGraphic(rating);
    }

  }

  private final class BitRateCell extends TableCell<Media, R> {
    @Override
    protected void updateItem(R item, boolean empty) {
      super.updateItem(item, empty);
      if (!(item instanceof Integer))
        return;
      int i = (int) item;
      setText(String.format("%s%s", byteCount(i, true), "ps"));
    }

    private String byteCount(long bytes, boolean si) {
      int unit = si ? 1000 : 1024;
      if (bytes < unit)
        return bytes + " b";
      int exp = (int) (Math.log(bytes) / Math.log(unit));
      String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
      return String.format("%.0f %sb", bytes / Math.pow(unit, exp), pre);
    }
  }

  private final class DurationCell extends TableCell<Media, R> {

    @Override
    protected void updateItem(R item, boolean empty) {
      super.updateItem(item, empty);
      if (!(item instanceof Integer))
        return;
      int i = (int) item;
      setText(timeFormat(i));
    }

    private String timeFormat(int time) {
      if (time >= 3600000) {
        return String.format("%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(time),
            TimeUnit.MILLISECONDS.toMinutes(time) % 60,
            TimeUnit.MILLISECONDS.toSeconds(time) % 60);
      } else {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time) % 60);
      }
    }
  }

  private final class DefaultCell extends TableCell<Media, R> {
    @Override
    protected void updateItem(R item, boolean empty) {
      if (item == getItem())
        return;

      super.updateItem(item, empty);

      if (item == null) {
        super.setText(null);
        super.setGraphic(null);
      } else if (item instanceof Node) {
        super.setText(null);
        super.setGraphic((Node) item);
      } else {
        super.setText(item.toString());
        super.setGraphic(null);
      }
    }
  }
}
