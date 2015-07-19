package de.malbertz.medialibfx.model.skin;

import java.io.IOException;

import de.malbertz.medialibfx.model.properties.PropertyManager;

public class SkinLoader {
  
  private static final Skin skin;
  
  static {
    try {
      skin = new Skin(PropertyManager.get("skin"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static Skin getSkin() {
    return skin;
  }
  
  private SkinLoader() {
  }
  
}
