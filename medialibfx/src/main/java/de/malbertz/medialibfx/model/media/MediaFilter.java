package de.malbertz.medialibfx.model.media;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class MediaFilter {

  private String filterName;
  private String type;
  private String name;
  private Integer maxDuration;
  private Integer minDuration;
  private Integer year;
  private Integer playCount;
  private Integer rating;
  private Date playedLast;
  private Date dateAdded;
  private String location;

  // Video
  private Integer width;
  private Integer height;
  private String aspectRatio;
  private List<String> language;

  // Audio
  private String album;
  private String genre;
  private String artist;
  private String albumArtist;
  private Integer trackId;
  private Integer bpm;
  private String publisher;
  private String composer;

  public MediaFilter(String filterName) {
    this.filterName = filterName;
  }

  public MediaFilter(String filterName, MediaFilter clone) {
    this.filterName = filterName;
    this.type = clone.type;
    this.name = clone.name;
    this.maxDuration = clone.maxDuration;
    this.minDuration = clone.minDuration;
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

    this.album = clone.album;
    this.genre = clone.genre;
    this.artist = clone.artist;
    this.albumArtist = clone.albumArtist;
    this.trackId = clone.trackId;
    this.bpm = clone.bpm;
    this.publisher = clone.publisher;
    this.composer = clone.composer;
  }

  public Predicate<Media> predicate() {
    return mediaRaw -> {
      if (mediaRaw.getMimeType().indexOf("audio") >= 0) {
        Audio media = (Audio) mediaRaw;

        return (type == null || media.getMimeType().indexOf(type) >= 0)
            && (name == null || media.getName().indexOf(name) >= 0);

      } else if (mediaRaw.getMimeType().indexOf("video") >= 0) {
        Video media = (Video) mediaRaw;

        return (type == null || media.getMimeType().indexOf(type) >= 0)
            && (name == null || media.getName().indexOf(name) >= 0)
            && (maxDuration == null || media.getDuration() <= maxDuration);

      } else {
        return true;
      }
    };
  }

  @Override
  public String toString() {
    return filterName;
  }

  public MediaFilter mimeType(String type) {
    this.type = type;
    return this;
  }

  public MediaFilter name(String name) {
    this.name = name;
    return this;
  }

  public MediaFilter maxDuration(Integer duration) {
    this.maxDuration = duration;
    return this;
  }
  
  public MediaFilter minDuration(Integer duration) {
    this.minDuration = duration;
    return this;
  }

  public MediaFilter year(Integer year) {
    this.year = year;
    return this;
  }

  public MediaFilter playCount(Integer playCount) {
    this.playCount = playCount;
    return this;
  }

  public MediaFilter rating(Integer rating) {
    this.rating = rating;
    return this;
  }

  public MediaFilter playedLast(Date playedLast) {
    this.playedLast = playedLast;
    return this;
  }

  public MediaFilter dateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
    return this;
  }

  public MediaFilter location(String location) {
    this.location = location;
    return this;
  }

  // Video

  public MediaFilter width(Integer width) {
    this.width = width;
    return this;
  }

  public MediaFilter height(Integer height) {
    this.height = height;
    return this;
  }

  public MediaFilter aspectRatio(String aspectRatio) {
    this.aspectRatio = aspectRatio;
    return this;
  }

  public MediaFilter language(List<String> language) {
    this.language = language;
    return this;
  }

  public MediaFilter album(String album) {
    this.album = album;
    return this;
  }

  public MediaFilter genre(String genre) {
    this.genre = genre;
    return this;
  }

  public MediaFilter artist(String artist) {
    this.artist = artist;
    return this;
  }

  public MediaFilter albumArtist(String albumArtist) {
    this.albumArtist = albumArtist;
    return this;
  }

  public MediaFilter trackId(Integer trackId) {
    this.trackId = trackId;
    return this;
  }

  public MediaFilter bpm(Integer bpm) {
    this.bpm = bpm;
    return this;
  }

  public MediaFilter publisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

}
