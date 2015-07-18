package de.malbertz.medialibfx.model.media;

import java.util.function.Predicate;

public class MediaFilter {

  private String name;
  private String type;
  private String fileName;

  public MediaFilter(String name) {
    this.name = name;
  }
  
  public MediaFilter(String name, String type, String fileName) {
    this.name = name;
    this.fileName = fileName;
    this.type = type;
  }
  
  public MediaFilter clone() {
    return new MediaFilter(this.name, this.type, this.fileName);
  }

  public MediaFilter type(String type) {
    this.type = type;
    return this;
  }
  
  public MediaFilter name(String fileName) {
    this.fileName = fileName;
    return this;
  }
  
  public MediaFilter filterName(String name) {
    this.name = name;
    return this;
  }

  public Predicate<Media> predicate() {
    return media -> {
      return (type == null || (type != null && null == type))
          && (fileName == null || (fileName != null && "".indexOf(fileName) >= 0));
    };
  }

  @Override
  public String toString() {
    return name;
  }

}
