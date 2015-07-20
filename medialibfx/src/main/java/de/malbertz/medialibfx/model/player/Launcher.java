package de.malbertz.medialibfx.model.player;

import java.util.Collection;

import de.malbertz.medialibfx.model.media.Media;
import javafx.beans.property.BooleanProperty;

public interface Launcher {
  BooleanProperty playingProperty();
  boolean isPlaying();
  void setPlaying(boolean playing);
  void launch(Media file);
  void launch(Collection<Media> c);
}
