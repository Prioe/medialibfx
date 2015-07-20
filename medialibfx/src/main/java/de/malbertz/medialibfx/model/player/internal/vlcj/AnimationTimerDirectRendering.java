package de.malbertz.medialibfx.model.player.internal.vlcj;

import javafx.animation.AnimationTimer;
/**
 * From https://github.com/caprica/vlcj-javafx
 *
 */
public class AnimationTimerDirectRendering extends DirectRendering {

  private final AnimationTimer timer;
  
  public AnimationTimerDirectRendering() {
    timer = new AnimationTimer() {
      
      @Override
      public void handle(long now) {
        renderFrame();
      }
    };
  }
  
  @Override
  protected void startTimer() {
    timer.start();
  }

  @Override
  protected void stopTimer() {
    timer.stop();
  }

}
