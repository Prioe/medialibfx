package de.malbertz.medialibfx.model.player;

public final class LauncherManager {

  private static Launcher videoLauncher;
  private static Launcher audioLauncher;
  
  static {
    
  }
  
  public static Launcher getAudioLauncher() {
    return audioLauncher;
  }
  
  public static Launcher getVideoLauncher() {
    return videoLauncher;
  }
  
  public static void setAudioLauncher(Launcher launcher) {
    audioLauncher = launcher;
  }
  
  public static void setVideoLauncher(Launcher launcher) {
    videoLauncher = launcher;
  }
  
  private LauncherManager(){}
  
}
