package de.malbertz.medialibfx.model.skin;

import de.malbertz.medialibfx.model.properties.PropertyManager;

public class SkinLoader {
  
  private static final Skin skin;
  
  static {
    skin = new Skin(PropertyManager.get("skin"));
  }
  
  public static Skin getSkin() {
    return skin;
  }
  
  private SkinLoader() {
  }
  
}
