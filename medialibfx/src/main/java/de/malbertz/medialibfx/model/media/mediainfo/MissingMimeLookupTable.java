package de.malbertz.medialibfx.model.media.mediainfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

public class MissingMimeLookupTable {
  
  private static Map<String, String> map;
  
  static {
    map = new HashMap<>();
    map.put("mkv", "video/x-matroska");
    map.put("mka", "audio/x-matroska");
    map.put("ogg", "audio/ogg");
    map.put("ogv", "video/ogg");
  }
  
  public static String lookup(File file) {
    String t = map.get(FilenameUtils.getExtension(file.toString()));
    return t == null ? "" : t;
  }
  
  private MissingMimeLookupTable(){}
}
