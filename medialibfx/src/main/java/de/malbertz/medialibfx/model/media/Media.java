package de.malbertz.medialibfx.model.media;

public interface Media {
  String getLocation();
  String getMimeType();
  String[][] properties();
}
