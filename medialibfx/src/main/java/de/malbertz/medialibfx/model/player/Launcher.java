package de.malbertz.medialibfx.model.player;

import java.util.Collection;

import de.malbertz.medialibfx.model.media.Media;

public interface Launcher {
  String[] getLegalExtensions();
  void launch(Media file);
  void launch(Collection<Media> c);
}
