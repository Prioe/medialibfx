package de.malbertz.medialibfx.model.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

   private static Properties properties;
   private static File propertiesFile;
   private static File mediaXmlFile;

   static {
      String os = System.getProperty("os.name").toLowerCase();
      String path;
      if (os.indexOf("win") >= 0) {
         path = System.getenv("APPDATA");
      } else {
         path = System.getProperty("user.home");
      }
      propertiesFile = new File(path + "/MediaLibFX/app.properties");
      mediaXmlFile = new File(path + "/MediaLibFX/media.xml");
      if (!propertiesFile.exists()) {
         new File(path + "/MediaLibFX").mkdir();
      }
      try {
         propertiesFile.createNewFile();
         mediaXmlFile.createNewFile();
         Properties defaults = new Properties();
         defaults.load(PropertyManager.class
               .getResourceAsStream("/properties/defaults.properties"));
         properties = new Properties(defaults);
         properties.load(new FileInputStream(propertiesFile));
         store();
      } catch (IOException e) {
         throw new RuntimeException("Failed to load properties", e);
      }
      
   }

   private static void store() throws FileNotFoundException, IOException {
      try (FileOutputStream fos = new FileOutputStream(propertiesFile)) {
         properties.store(fos, "User Properties for the MediaLibFX Appication");
      }
   }

   public static String get(String key) {
      return properties.getProperty(key);
   }

   public static void set(String key, String value)
         throws FileNotFoundException, IOException {
      
      properties.setProperty(key, value);
      store();
   }
   
   public static File getMediaXmlFile() {
     return mediaXmlFile;
   }

   private PropertyManager() {
   }

}
