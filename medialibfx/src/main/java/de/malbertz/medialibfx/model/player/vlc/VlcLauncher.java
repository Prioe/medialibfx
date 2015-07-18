package de.malbertz.medialibfx.model.player.vlc;

import java.util.Collection;

import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.player.Launcher;

public class VlcLauncher implements Launcher {

  @Override
  public String[] getLegalExtensions() {
    String[] types = new String[] { "3gp", // 3GP
        "asf", // AIFF
        "asf", "wmv", // ASF
        "au", // AU
        "avi", // AVI
        "flv", // FLV
        "moves", // MOV
        "mp4", // MP4
        "ogm", "ogg", // OGG
        "mkv", "mka", // MKV
        "ts", "mpg", // MPEG-2 / TS
        "mpg", "mp3", "mp2", // MPEG-2 / ES, PS, PVA, MP3
        "nsc", // NSC
        "nsv", // NSV
        "nut", // Nut
        "ra", "ram", "rm", "rv", "rmbv", // Real
        "a52", "dts", "aac", "flac", "dv", "vid", // Raw (a52, dts, aac, flac,
                                                  // "dv", "vid")
        "tta", "tac", // True Audio Codec
        "ty", // Ty Tivo
        "wav", "dts", // Wav
        "xa" // Xa
    };
    return types;
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
