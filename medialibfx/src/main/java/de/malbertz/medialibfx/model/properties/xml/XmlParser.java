package de.malbertz.medialibfx.model.properties.xml;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import de.malbertz.medialibfx.model.media.Media;

public class XmlParser {

  //private static final JDomXmlParser jdom = new JDomXmlParser();
  
  public static void writeToItunes(List<Media> media) {
    throw new UnsupportedOperationException();
  }

  
  public static void writeToXml(Media media) throws IOException {
    final JDomXmlParser jdom = new JDomXmlParser();
    jdom.write(media);
    jdom.store();
  }
  
  public static void writeToXml(Media... media) throws IOException {
    final JDomXmlParser jdom = new JDomXmlParser();
    for (int i = 0; i < media.length; i++) {
      jdom.write(media[i]);
    }    
    jdom.store();
  }
  
  public static void writeToXml(Collection<? extends Media> media) throws IOException {
    final JDomXmlParser jdom = new JDomXmlParser();
    for (Media media2 : media) {
      jdom.write(media2);      
    }
    jdom.store();
  }

  public static List<Media> loadFromXml() {
    final JDomXmlParser jdom = new JDomXmlParser();
    return jdom.readXml();
  }

  public static List<Media> loadFromItunes() {
    return null;
  }

  private XmlParser() {
  }

}
