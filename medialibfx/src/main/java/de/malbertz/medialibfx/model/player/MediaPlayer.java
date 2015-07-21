package de.malbertz.medialibfx.model.player;

import de.malbertz.medialibfx.model.media.Media;

abstract class MediaPlayer {
  abstract void start(Media media);
  abstract void pause();
  abstract void stop();
  abstract void release();
  
}
