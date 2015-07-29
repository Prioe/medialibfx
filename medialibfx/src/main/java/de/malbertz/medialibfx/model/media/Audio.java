package de.malbertz.medialibfx.model.media;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class Audio implements Media {

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
  private final ReadOnlyStringProperty album;
  private final ReadOnlyStringProperty genre;
  private final ReadOnlyStringProperty artist;
  private final ReadOnlyStringProperty albumArtist;
  private final ReadOnlyIntegerProperty trackId;
  private final ReadOnlyIntegerProperty bpm;
  private final ReadOnlyStringProperty publisher;
  private final ReadOnlyStringProperty composer;

  public Audio(String mimeType, String name, int duration, int size,
      int bitRate, int year, int playCount, int rating, Date playedLast,
      Date dateAdded, String location, String album, String genre,
      String artist, String albumArtist, int trackId, int bpm, String publisher,
      String composer) {

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
    this.album = new ReadOnlyStringWrapper(album);
    this.genre = new ReadOnlyStringWrapper(genre);
    this.artist = new ReadOnlyStringWrapper(artist);
    this.albumArtist = new ReadOnlyStringWrapper(albumArtist);
    this.trackId = new ReadOnlyIntegerWrapper(trackId);
    this.bpm = new ReadOnlyIntegerWrapper(bpm);
    this.publisher = new ReadOnlyStringWrapper(publisher);
    this.composer = new ReadOnlyStringWrapper(composer);

  }

  public Audio(Audio clone) {
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
    this.album = clone.album;
    this.genre = clone.genre;
    this.artist = clone.artist;
    this.albumArtist = clone.albumArtist;
    this.trackId = clone.trackId;
    this.bpm = clone.bpm;
    this.publisher = clone.publisher;
    this.composer = clone.composer;
  }

  public Audio(File file) throws FileNotFoundException {
    this((Audio) MediaInfoLoader.fromFile(file));
  }

  public Audio(String string) throws FileNotFoundException {
    this(new File(string));
  }

  @Override
  public String[][] properties() {
    return new String[][] { { "mimeType", "string" }, { "name", "string" }, { "duration", "int" },
        { "size", "int" }, { "bitRate", "int" }, { "year", "int" },
        { "playCount", "int" }, { "rating", "int" }, { "playedLast", "date" },
        { "dateAdded", "date" }, { "location", "string" },
        { "album", "string" }, { "genre", "string" }, { "artist", "string" },
        { "albumArtist", "string" }, { "trackId", "int" }, { "bpm", "int" },
        { "publisher", "string" }, { "composer", "string" } };
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

  public final ReadOnlyStringProperty albumProperty() {
    return this.album;
  }

  public final java.lang.String getAlbum() {
    return this.albumProperty().get();
  }

  public final ReadOnlyStringProperty genreProperty() {
    return this.genre;
  }

  public final java.lang.String getGenre() {
    return this.genreProperty().get();
  }

  public final ReadOnlyStringProperty artistProperty() {
    return this.artist;
  }

  public final java.lang.String getArtist() {
    return this.artistProperty().get();
  }

  public final ReadOnlyStringProperty albumArtistProperty() {
    return this.albumArtist;
  }

  public final java.lang.String getAlbumArtist() {
    return this.albumArtistProperty().get();
  }

  public final ReadOnlyIntegerProperty trackIdProperty() {
    return this.trackId;
  }

  public final int getTrackId() {
    return this.trackIdProperty().get();
  }

  public final ReadOnlyIntegerProperty bpmProperty() {
    return this.bpm;
  }

  public final int getBpm() {
    return this.bpmProperty().get();
  }

  public final ReadOnlyStringProperty publisherProperty() {
    return this.publisher;
  }

  public final java.lang.String getPublisher() {
    return this.publisherProperty().get();
  }

  public final ReadOnlyStringProperty composerProperty() {
    return this.composer;
  }

  public final java.lang.String getComposer() {
    return this.composerProperty().get();
  }

  public String getMimeType() {
    return mimeType;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Audio [mimeType=").append(mimeType).append(", name=")
        .append(name).append(", duration=").append(duration).append(", size=")
        .append(size).append(", bitRate=").append(bitRate).append(", year=")
        .append(year).append(", playCount=").append(playCount)
        .append(", rating=").append(rating).append(", playedLast=")
        .append(playedLast).append(", dateAdded=").append(dateAdded)
        .append(", location=").append(location).append(", album=").append(album)
        .append(", genre=").append(genre).append(", artist=").append(artist)
        .append(", albumArtist=").append(albumArtist).append(", trackId=")
        .append(trackId).append(", bpm=").append(bpm).append(", publisher=")
        .append(publisher).append(", composer=").append(composer).append("]");
    return builder.toString();
  }  
  
}
