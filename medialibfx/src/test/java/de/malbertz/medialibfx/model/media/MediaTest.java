package de.malbertz.medialibfx.model.media;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

public class MediaTest {

  @Test
  public void test() {
    try {
      Media media = MediaLoader.loadMedia(MediaTest.class.getResource("/Example.ogg").toExternalForm());
      Audio audio = (Audio) media;
      assertTrue("audio/ogg".equals(audio.getMimeType()));
    } catch (FileNotFoundException e) {
      fail(e.getMessage());
    }
  }

}
