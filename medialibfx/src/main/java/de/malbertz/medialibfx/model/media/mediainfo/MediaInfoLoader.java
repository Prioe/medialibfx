package de.malbertz.medialibfx.model.media.mediainfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import de.malbertz.medialibfx.model.media.Audio;
import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.Video;

public class MediaInfoLoader {

  private static MediaInfo mediaInfo = new MediaInfo();

  public static Media fromFile(File file) throws FileNotFoundException {
    if (mediaInfo.Open(file.toString()) < 0)
      throw new FileNotFoundException();
    String mimeType = getGeneralString("InternetMediaType");
    if ("".equals(mimeType))
      mimeType = MissingMimeLookupTable.lookup(file);
    
    int playCount = 0;
    int rating = 0;
    Date playedLast = null;
    Date dateAdded = new Date();
    String location = file.toString();

    if (mimeType.indexOf("audio") >= 0) {

      String name = getGeneralString("Title");
      int duration = getAudioInt("Duration");
      int size = getAudioInt("FileSize");
      int bitRate = getAudioInt("BitRate");
      int year = 0; // Placehoder
      String album = getGeneralString("Album");
      String genre = getGeneralString("Genre");
      String artist = getGeneralString("Performer");
      String albumArtist = getGeneralString("Album/Performer");
      int trackId = getGeneralInt("Track/Position");
      int bpm = getGeneralInt("BPM");
      String publisher = getGeneralString("Publisher");
      String composer = getGeneralString("Composer");
      return new Audio(mimeType, name, duration, size, bitRate, year, playCount, rating,
          playedLast, dateAdded, location, album, genre, artist, albumArtist,
          trackId, bpm, publisher, composer);

    } else if (mimeType.indexOf("video") >= 0) {

      String name = getGeneralString("Title");
      int duration = getVideoInt("Duration");
      int size = getVideoInt("FileSize");
      int bitRate = getVideoInt("BitRate");
      int year = 0; // Placehoder
      int width = getVideoInt("Width");
      int height = getVideoInt("Height");
      String aspectRatio = getVideoString("PixelAspectRatio/String");
      String[] language = new String[getAudioInt("Count")];
      for (int i = 0; i < language.length; i++) {
        language[i] = mediaInfo.Get(MediaInfo.StreamKind.Audio, i, "Language",
            MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
      }
      return new Video(mimeType, name, duration, size, bitRate, year, playCount, rating,
          playedLast, dateAdded, location, width, height, aspectRatio,
          language);

    } else {
      throw new IllegalArgumentException(
          "Unsupported Media Type: " + mimeType + " : " + file);
    }
  }
  
  public static Media fromFile(String string) throws FileNotFoundException {
    return fromFile(new File(string));
  }

  private static int getGeneralInt(String param) {
    try {
      return Integer.parseInt(mediaInfo.Get(MediaInfo.StreamKind.General, 0,
          param, MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  private static String getGeneralString(String param) {
    return mediaInfo.Get(MediaInfo.StreamKind.General, 0, param,
        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
  }

  private static String getVideoString(String param) {
    return mediaInfo.Get(MediaInfo.StreamKind.Video, 0, param,
        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
  }

  private static int getVideoInt(String param) {
    try {
      return Integer.parseInt(mediaInfo.Get(MediaInfo.StreamKind.Video, 0,
          param, MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  private static int getAudioInt(String param) {
    try {
      return Integer.parseInt(mediaInfo.Get(MediaInfo.StreamKind.Audio, 0,
          param, MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  /*
   * private static String getAudioString(String param) { return
   * mediaInfo.Get(MediaInfo.StreamKind.Audio, 0, param,
   * MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name); }
   */

  private MediaInfoLoader() {
  }

  

}
