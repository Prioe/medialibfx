package de.malbertz.medialibfx.main;

import de.malbertz.medialibfx.controller.MainController;
import de.malbertz.medialibfx.model.player.internal.vlcj.AnimationTimerDirectRendering;

public class Context {
  private final static Context instance = new Context();

  private MainController mainController;
  private AnimationTimerDirectRendering directRendering;
  
  public static Context getInstance() {
     return instance;
  }
  
  public MainController getMainController() {
    return mainController;
  }
  
  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  }
  
  public AnimationTimerDirectRendering getDirectRendering() {
    return directRendering;
  }
  
  public void setDirectRendering(AnimationTimerDirectRendering directRendering) {
    this.directRendering = directRendering;
  }
  
  private Context() {}

}
