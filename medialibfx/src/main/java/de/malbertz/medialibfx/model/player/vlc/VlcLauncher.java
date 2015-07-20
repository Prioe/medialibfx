package de.malbertz.medialibfx.model.player.vlc;

import java.util.Collection;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.player.Launcher;
import javafx.beans.property.BooleanProperty;

public class VlcLauncher implements Launcher {

  @Override
  public void launch(Media file) {
    // TODO Auto-generated method stub

  }

  @Override
  public void launch(Collection<Media> c) {
    // TODO Auto-generated method stub

  }

  @Override
  public BooleanProperty playingProperty() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isPlaying() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setPlaying(boolean playing) {
    // TODO Auto-generated method stub
    
  }

}
