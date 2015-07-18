package de.malbertz.medialibfx.model;

import static org.junit.Assert.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import de.malbertz.medialibfx.model.media.Audio;
import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.properties.xml.XmlParser;

public class XmlParserTest {

  @Test
  public void test() {
    try {
      
      List<Media> list = new ArrayList<>();
      Files.walk(Paths.get("D:\\Musik\\Soundtracks")).filter(Files::isRegularFile)
          .map(Path::toFile).collect(Collectors.toList()).forEach(file -> {

            try {
              list.add(new Audio(file));
            } catch (Exception e) {
              // TODO Auto-generated catch block
              System.out.println(e.getMessage());
            }
          });

      XmlParser.writeToXml(list);
  
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
