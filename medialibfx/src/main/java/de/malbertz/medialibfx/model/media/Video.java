package de.malbertz.medialibfx.model.media;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;

public class Video implements Media {

  private final String mimeType;
  private final ReadOnlyStringProperty name;
  private final ReadOnlyIntegerProperty duration;
  private final ReadOnlyIntegerProperty size;
  private final ReadOnlyIntegerProperty bitRate;
  private final ReadOnlyIntegerProperty year;
  private final SimpleIntegerProperty playCount;
  private final SimpleIntegerProperty rating;
  private final ReadOnlyObjectProperty<Date> playedLast;
  private final ReadOnlyObjectProperty<Date> dateAdded;
  private final ReadOnlyStringProperty location;
  private final ReadOnlyIntegerProperty width;
  private final ReadOnlyIntegerProperty height;
  private final ReadOnlyStringProperty aspectRatio;
  private final ReadOnlyListProperty<String> language;

  public Video(String mimeType, String name, int duration, int size,
      int bitRate, int year, int playCount, int rating, Date playedLast,
      Date dateAdded, String location, int width, int height,
      String aspectRatio, String... language) {

    this.mimeType = mimeType;
    this.name = new ReadOnlyStringWrapper(name);
    this.duration = new ReadOnlyIntegerWrapper(duration);
    this.size = new ReadOnlyIntegerWrapper(size);
    this.bitRate = new ReadOnlyIntegerWrapper(bitRate);
    this.year = new ReadOnlyIntegerWrapper(year);
    this.playCount = new ReadOnlyIntegerWrapper(playCount);
    this.rating = new ReadOnlyIntegerWrapper(rating);
    this.playedLast = new ReadOnlyObjectWrapper<Date>(playedLast);
    this.dateAdded = new ReadOnlyObjectWrapper<Date>(dateAdded);
    this.location = new ReadOnlyStringWrapper(location);
    this.width = new ReadOnlyIntegerWrapper(width);
    this.height = new ReadOnlyIntegerWrapper(height);
    this.aspectRatio = new ReadOnlyStringWrapper(aspectRatio);
    this.language = new ReadOnlyListWrapper<>(
        FXCollections.observableArrayList(language));

  }

  public Video(Video clone) {
    this.mimeType = clone.mimeType;
    this.name = clone.name;
    this.duration = clone.duration;
    this.size = clone.size;
    this.bitRate = clone.bitRate;
    this.year = clone.year;
    this.playCount = clone.playCount;
    this.rating = clone.rating;
    this.playedLast = clone.playedLast;
    this.dateAdded = clone.dateAdded;
    this.location = clone.location;
    this.width = clone.width;
    this.height = clone.height;
    this.aspectRatio = clone.aspectRatio;
    this.language = clone.language;
  }
  
  public Video(File file) throws FileNotFoundException {
    this((Video) MediaInfoLoader.fromFile(file));
  }
  
  public Video(String string) throws FileNotFoundException {
    this(new File(string));
  }

  @Override
  public String[][] properties() {
    return new String[][] {
        { "mimeType", "string" },
        {"name", "string"},
        {"duration", "int"},
        {"size", "int"},
        {"bitRate", "int"},
        {"year", "int"},
        {"playCount", "int"},
        {"rating", "int"},
        {"playedLast", "date"},
        {"dateAdded", "date"},
        {"location", "string"},
        {"width", "int"},
        {"height", "int"},
        {"aspectRatio", "string"},
        {"language", "strings"} };
  }

  public final ReadOnlyStringProperty nameProperty() {
    return this.name;
  }

  public final java.lang.String getName() {
    return this.nameProperty().get();
  }

  public final ReadOnlyIntegerProperty durationProperty() {
    return this.duration;
  }

  public final int getDuration() {
    return this.durationProperty().get();
  }

  public final ReadOnlyIntegerProperty sizeProperty() {
    return this.size;
  }

  public final int getSize() {
    return this.sizeProperty().get();
  }

  public final ReadOnlyIntegerProperty bitRateProperty() {
    return this.bitRate;
  }

  public final int getBitRate() {
    return this.bitRateProperty().get();
  }

  public final ReadOnlyIntegerProperty yearProperty() {
    return this.year;
  }

  public final int getYear() {
    return this.yearProperty().get();
  }

  public final SimpleIntegerProperty playCountProperty() {
    return this.playCount;
  }

  public final int getPlayCount() {
    return this.playCountProperty().get();
  }

  public final void setPlayCount(final int playCount) {
    this.playCountProperty().set(playCount);
  }

  public final SimpleIntegerProperty ratingProperty() {
    return this.rating;
  }

  public final int getRating() {
    return this.ratingProperty().get();
  }

  public final void setRating(final int rating) {
    this.ratingProperty().set(rating);
  }

  public final ReadOnlyObjectProperty<Date> playedLastProperty() {
    return this.playedLast;
  }

  public final java.util.Date getPlayedLast() {
    return this.playedLastProperty().get();
  }

  public final ReadOnlyObjectProperty<Date> dateAddedProperty() {
    return this.dateAdded;
  }

  public final java.util.Date getDateAdded() {
    return this.dateAddedProperty().get();
  }

  public final ReadOnlyStringProperty locationProperty() {
    return this.location;
  }

  public final java.lang.String getLocation() {
    return this.locationProperty().get();
  }

  public final ReadOnlyIntegerProperty widthProperty() {
    return this.width;
  }

  public final int getWidth() {
    return this.widthProperty().get();
  }

  public final ReadOnlyIntegerProperty heightProperty() {
    return this.height;
  }

  public final int getHeight() {
    return this.heightProperty().get();
  }

  public final ReadOnlyStringProperty aspectRatioProperty() {
    return this.aspectRatio;
  }

  public final java.lang.String getAspectRatio() {
    return this.aspectRatioProperty().get();
  }

  public final ReadOnlyListProperty<String> languageProperty() {
    return this.language;
  }

  public final javafx.collections.ObservableList<java.lang.String> getLanguage() {
    return this.languageProperty().get();
  }

  public String getMimeType() {
    return mimeType;
  }

}
