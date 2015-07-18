package de.malbertz.medialibfx.model;

import static org.junit.Assert.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.mediainfo.MediaInfoLoader;

public class MediaTest {

  @Test
  public void test() {
    try {
      Files.walk(Paths.get("D:\\Musik\\Soundtracks"))
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .collect(Collectors.toList())
          .forEach(file -> {
            try {
              //mediaList.add(new Media(file));
            } catch (Exception e) {
              fail(e.getMessage());
            }
          });
      //Media media = mediaList.get(5);
      Media media = MediaInfoLoader.fromFile("D:\\Musik\\Soundtracks\\Avengers Age of Ultron OST\\02 Heroes.m4a");
      System.out.println(media);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

}
