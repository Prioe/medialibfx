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
  }
  
  public static String lookup(File file) {
    return map.get(FilenameUtils.getExtension(file.toString()));
  }
  
  private MissingMimeLookupTable(){}
}
