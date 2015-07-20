package de.malbertz.medialibfx.model.player.internal;

import java.util.Collection;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.player.Launcher;
import javafx.beans.property.BooleanProperty;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class InternalLauncher implements Launcher {

  public InternalLauncher() {
    boolean found = new NativeDiscovery().discover();
    System.out.println(found);
    System.out.println(LibVlc.INSTANCE.libvlc_get_version());
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

  @Override
  public void launch(Media file) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void launch(Collection<Media> c) {
    // TODO Auto-generated method stub
    
  }

}
