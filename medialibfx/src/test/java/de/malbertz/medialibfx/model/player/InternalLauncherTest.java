package de.malbertz.medialibfx.model.player;

import static org.junit.Assert.*;

import org.junit.Test;

import de.malbertz.medialibfx.model.player.internal.InternalLauncher;

public class InternalLauncherTest {

  @Test
  public void test() {
    try {
      System.out.println(System.getProperty("sun.arch.data.model"));
    new InternalLauncher();
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

}
