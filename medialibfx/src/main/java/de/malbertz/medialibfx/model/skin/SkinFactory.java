package de.malbertz.medialibfx.model.skin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.controlsfx.glyphfont.Glyph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.malbertz.medialibfx.model.properties.PropertyManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkinFactory {

  private static final Logger log = LoggerFactory.getLogger(SkinFactory.class);

  private static final Map<String, Skin> skins;
  private static final Properties defaults;

  static {
    defaults = new Properties();
    try {
      defaults.load(SkinFactory.class
          .getResourceAsStream("/skins/skinDefaults.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // add hardcoded skins
    skins = new HashMap<>();
    try {
      skins.put("dark", new SkinImpl("/skins/dark", true));
      skins.put("light", new SkinImpl("/skins/light", true));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // add skins from skin folder
  }

  public static Skin getSkin(String skin) {
    return skins.get(skin);
  }

  public static Skin getSkin() {
    return getSkin(PropertyManager.get("window.skin"));
  }

  private static class SkinImpl implements Skin {
    private final Properties skinProperties;
    private List<String> stylesheets;
    private Node play;
    private Node pause;
    private Node prev;
    private Node next;
    private Node stop;
    private Node fastforward;
    private Node rewind;
    private Node mute;
    private Node lowVolume;
    private Node midVolume;
    private Node highVolume;
    private Node shuffle;
    private Node repeat;

    private SkinImpl(String path, boolean resource)
        throws FileNotFoundException, IOException {
      this.skinProperties = new Properties(defaults);
      if (resource) {
        String t = path + path.substring(path.lastIndexOf('/')) + ".properties";
        skinProperties.load(SkinFactory.class.getResourceAsStream(t));
      } else {
        // TODO: load external skins here
      }

    }

    private SkinImpl(String path) throws FileNotFoundException, IOException {
      this(path, false);
    }

    private Node getGraphic(String name) {
      Node n = null;
      String type = skinProperties.getProperty("graphic." + name + ".type")
          .toLowerCase();
      log.debug("{} graphic type: {}", name, type);
      switch (type) {
      case "glyph":
        String fontFamily = skinProperties
            .getProperty("graphic." + name + ".glyph.fontfamily");
        String unicode = skinProperties
            .getProperty("graphic." + name + ".glyph.unicode");
        int size = Integer.parseInt(
            skinProperties.getProperty("graphic." + name + ".glyph.size"));
        log.debug("{}: fontfamily: {} unicode: {} size: {}", name, fontFamily,
            unicode, size);
        n = new Glyph(fontFamily, unicode).size(size);
        break;
      case "image":
        String url = skinProperties.getProperty("graphic." + name + ".image.url");        
        n = new ImageView(new Image(SkinFactory.class.getResourceAsStream(url)));
        break;
      default:
        break;
      }

      return n;

    }

    @Override
    public Node pauseGraphic() {
      if (pause == null) {
        pause = getGraphic("pause");
      }
      return pause;
    }

    @Override
    public Node playGraphic() {
      if (play == null) {
        play = getGraphic("play");
      }
      return play;
    }

    @Override
    public Node prevGraphic() {
      if (prev == null) {
        prev = getGraphic("prev");
      }
      return prev;
    }

    @Override
    public Node nextGraphic() {
      if (next == null) {
        next = getGraphic("next");
      }
      return next;
    }

    @Override
    public Node stopGraphic() {
      if (stop == null) {
        stop = getGraphic("stop");
      }
      return stop;
    }

    @Override
    public Node fastforwardGraphic() {
      if (fastforward == null) {
        fastforward = getGraphic("fastforward");
      }
      return fastforward;
    }

    @Override
    public Node rewindGraphic() {
      if (rewind == null) {
        rewind = getGraphic("rewind");
      }
      return rewind;
    }

    @Override
    public Node muteGraphic() {
      if (mute == null) {
        mute = getGraphic("mute");
      }
      return mute;
    }

    @Override
    public Node lowVolumeGraphic() {
      if (lowVolume == null) {
        lowVolume = getGraphic("lowVolume");
      }
      return lowVolume;
    }

    @Override
    public Node midVolumeGraphic() {
      if (midVolume == null) {
        midVolume = getGraphic("midVolume");
      }
      return midVolume;
    }

    @Override
    public Node highVolumeGraphic() {
      if (highVolume == null) {
        highVolume = getGraphic("highVolume");
      }
      return highVolume;
    }

    @Override
    public Node shuffleGraphic() {
      if (shuffle == null) {
        shuffle = getGraphic("shuffle");
      }
      return shuffle;
    }

    @Override
    public Node repeatGraphic() {
      if (repeat == null) {
        repeat = getGraphic("repeat");
      }
      return repeat;
    }

    @Override
    public Node getActiveStar() {

      return getGraphic("activeStar");
    }

    @Override
    public Node getInactiveStar() {

      return getGraphic("inactiveStar");
    }

    @Override
    public List<String> getStylesheets() {
      if (stylesheets == null) {
        stylesheets = new ArrayList<>();
        String layout = skinProperties.getProperty("stylesheets.layout");
        if (layout != null) stylesheets.add(layout);
        String color = skinProperties.getProperty("stylesheets.color");
        if (color != null) stylesheets.add(color);
        String additional = skinProperties
            .getProperty("stylesheets.additional");
        if (additional != null) {
          for (String sheet : additional.split(",")) {
            stylesheets.add(sheet);
          }
        }
      }
      return stylesheets;
    }
  }

  private SkinFactory() {
  }

}
