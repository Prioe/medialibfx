package de.malbertz.medialibfx.main;

import de.malbertz.medialibfx.controller.MainController;

public class Context {
  private final static Context instance = new Context();

  private MainController mainController;
  
  public static Context getInstance() {
     return instance;
  }
  
  public MainController getMainController() {
    return mainController;
  }
  
  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  }
  
  private Context() {}

}
