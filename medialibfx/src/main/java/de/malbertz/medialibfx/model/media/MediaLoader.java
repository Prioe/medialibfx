package de.malbertz.medialibfx.model.media;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;
import de.malbertz.medialibfx.model.properties.xml.XmlParser;

public class MediaLoader {

  public static List<Media> fromDirectory(File dir) throws IOException {
    if (!dir.isDirectory())
      throw new IllegalArgumentException(dir + "is no Directory");
   
    List<File> files = Files.walk(Paths.get(dir.toURI()))
        .filter(Files::isRegularFile).map(Path::toFile)
        .collect(Collectors.toList());
    
    List<Media> list = new ArrayList<>();
    for (File file : files) {
      list.add(MediaInfoLoader.fromFile(file));
    }

    return list;
  }
  
  public static List<Media> fromXml() {
    return XmlParser.loadFromXml();
  }

  public static List<Media> fromItunes(File dir) {
    if (!dir.isDirectory())
      throw new IllegalArgumentException(dir + "is no Directory");

    return XmlParser.loadFromItunes();
  }
  
  public static Media loadMedia(File f) throws FileNotFoundException {
    return MediaInfoLoader.fromFile(f);
  }
  
  public static Media loadMedia(String s) throws FileNotFoundException {
    return MediaInfoLoader.fromFile(s);
  }

  private MediaLoader() {
  }

}
