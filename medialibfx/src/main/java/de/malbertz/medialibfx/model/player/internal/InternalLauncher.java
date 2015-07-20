package de.malbertz.medialibfx.model.player.internal;

import java.util.Collection;

import com.sun.jna.NativeLibrary;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.player.Launcher;
import de.malbertz.medialibfx.model.player.internal.vlcj.AnimationTimerDirectRendering;
import de.malbertz.medialibfx.model.properties.PropertyManager;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class InternalLauncher extends AnimationTimerDirectRendering implements Launcher {

  private AnimationTimerDirectRendering directRendering;
  
  public InternalLauncher() {   
    directRendering = new AnimationTimerDirectRendering();
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
  }

  @Override
  public void launch(Collection<Media> c) {
    // TODO Auto-generated method stub
    
  }

}
