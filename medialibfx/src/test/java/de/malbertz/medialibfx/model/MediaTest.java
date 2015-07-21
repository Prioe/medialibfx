package de.malbertz.medialibfx.model;

import static org.junit.Assert.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;

public class MediaTest {

  @Test
  public void test() {
    Map<String, String> map = new HashMap<>();
    try {
      Files.walk(Paths.get("D:\\Musik"))
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .collect(Collectors.toList())
          .forEach(file -> {
            try {
              map.put(FilenameUtils.getExtension(file.toString().toLowerCase()), "" );
            } catch (Exception e) {
              fail(e.getMessage());
            }
          });
      System.out.println(map);
      Media media = MediaInfoLoader.fromFile("D:\\Musik\\Soundtracks\\Avengers Age of Ultron OST\\02 Heroes.m4a");
      System.out.println(media);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

}
