package de.malbertz.medialibfx.model.properties.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.malbertz.medialibfx.model.media.Audio;
import de.malbertz.medialibfx.model.media.Media;
import de.malbertz.medialibfx.model.media.Video;
import de.malbertz.medialibfx.model.properties.PropertyManager;

class JDomXmlParser {

  private Document doc;

  List<Media> readXml() {
    List<Media> list = new ArrayList<>();
    DateFormat df = new SimpleDateFormat();
    List<Element> elemList = doc.getRootElement().getChildren("audio");
    for (int i = 0; i < elemList.size(); i++) {

      Element elem = elemList.get(i);

      String mimeType = elem.getAttributeValue("mimeType");
      String name = elem.getChildText("name");
      int duration = Integer.parseInt(elem.getChildText("duration"));
      int size = Integer.parseInt(elem.getChildText("size"));
      int bitRate = Integer.parseInt(elem.getChildText("bitRate"));
      int year = Integer.parseInt(elem.getChildText("year"));
      int playCount = Integer.parseInt(elem.getChildText("playCount"));
      int rating = Integer.parseInt(elem.getChildText("rating"));
      Date playedLast = null;
      Date dateAdded = null;
      try {
        playedLast = df.parse(elem.getChildText("playedLast"));
        dateAdded = df.parse(elem.getChildText("dateAdded"));
      } catch (ParseException e) {
      }

      if (mimeType.indexOf("audio") >= 0) {
        String location = elem.getChildText("location");
        String album = elem.getChildText("album");
        String genre = elem.getChildText("genre");
        String artist = elem.getChildText("artist");
        String albumArtist = elem.getChildText("albumArtist");
        int trackId = Integer.parseInt(elem.getChildText("trackId"));
        int bpm = Integer.parseInt(elem.getChildText("bpm"));
        String publisher = elem.getChildText("publisher");
        String composer = elem.getChildText("composer");

        list.add(new Audio(mimeType, name, duration, size, bitRate, year,
            playCount, rating, playedLast, dateAdded, location, album, genre,
            artist, albumArtist, trackId, bpm, publisher, composer));
      } else if (mimeType.indexOf("video") >= 0) {
        
      }
    }

    return list;
  }

  List<Video> readVideo() {
    return null;
  }

  List<Audio> readAudio() {
    List<Audio> list = new ArrayList<>();
    DateFormat df = new SimpleDateFormat();
    List<Element> elemList = doc.getRootElement().getChildren("audio");
    for (int i = 0; i < elemList.size(); i++) {

      Element elem = elemList.get(i);

      String mimeType = elem.getAttributeValue("mimeType");
      String name = elem.getChildText("name");
      int duration = Integer.parseInt(elem.getChildText("duration"));
      int size = Integer.parseInt(elem.getChildText("size"));
      int bitRate = Integer.parseInt(elem.getChildText("bitRate"));
      int year = Integer.parseInt(elem.getChildText("year"));
      int playCount = Integer.parseInt(elem.getChildText("playCount"));
      int rating = Integer.parseInt(elem.getChildText("rating"));
      Date playedLast = null;
      Date dateAdded = null;
      try {
        playedLast = df.parse(elem.getChildText("playedLast"));
        dateAdded = df.parse(elem.getChildText("dateAdded"));
      } catch (ParseException e) {
      }
      String location = elem.getChildText("location");
      String album = elem.getChildText("album");
      String genre = elem.getChildText("genre");
      String artist = elem.getChildText("artist");
      String albumArtist = elem.getChildText("albumArtist");
      int trackId = Integer.parseInt(elem.getChildText("trackId"));
      int bpm = Integer.parseInt(elem.getChildText("bpm"));
      String publisher = elem.getChildText("publisher");
      String composer = elem.getChildText("composer");

      list.add(new Audio(mimeType, name, duration, size, bitRate, year,
          playCount, rating, playedLast, dateAdded, location, album, genre,
          artist, albumArtist, trackId, bpm, publisher, composer));
    }

    return list;
  }

  @Deprecated
  void writeAudio(Audio media) {
    Element root = doc.getRootElement();
    Element audio = new Element("audio");
    //@formatter:off
      audio.addContent(new Element("name").setText(media.getName()));
      audio.addContent(new Element("duration").setText(media.getDuration() + ""));
      audio.addContent(new Element("size").setText(media.getSize() + ""));
      audio.addContent(new Element("bitRate").setText(media.getBitRate() + ""));
      audio.addContent(new Element("year").setText(media.getYear() + ""));
      audio.addContent(new Element("playCount").setText(media.getPlayCount() + ""));
      audio.addContent(new Element("rating").setText(media.getRating() + ""));
      audio.addContent(new Element("playedLast").setText(media.getPlayedLast() + ""));
      audio.addContent(new Element("dateAdded").setText(media.getDateAdded()+""));
      audio.addContent(new Element("location").setText(media.getLocation()));
      audio.addContent(new Element("album").setText(media.getAlbum()));
      audio.addContent(new Element("genre").setText(media.getGenre()));
      audio.addContent(new Element("artist").setText(media.getArtist()));
      audio.addContent(new Element("albumArtist").setText(media.getAlbumArtist()));
      audio.addContent(new Element("trackId").setText(media.getTrackId()+""));
      audio.addContent(new Element("bpm").setText(media.getBpm()+""));
      audio.addContent(new Element("publisher").setText(media.getPublisher()));
      audio.addContent(new Element("composer").setText(media.getComposer()));
      audio.setAttribute("mimeType", media.getMimeType());
      root.addContent(audio);
      //@formatter:on
  }
  @Deprecated
  void writeVideo(Video media) {

  }

  void write(Media media) {
    /*
    if (media instanceof Audio) {
      writeAudio((Audio) media);
    } else if (media instanceof Video) {
      writeVideo((Video) media);
    }*/
    
    Element root = doc.getRootElement();
    String mimeType = media.getMimeType();
    
    if (mimeType.indexOf("audio") >= 0) {
      Element elem = new Element("audio");
      Audio audio = (Audio) media;
      //@formatter:off
      elem.addContent(new Element("name").setText(audio.getName()));
      elem.addContent(new Element("duration").setText(audio.getDuration() + ""));
      elem.addContent(new Element("size").setText(audio.getSize() + ""));
      elem.addContent(new Element("bitRate").setText(audio.getBitRate() + ""));
      elem.addContent(new Element("year").setText(audio.getYear() + ""));
      elem.addContent(new Element("playCount").setText(audio.getPlayCount() + ""));
      elem.addContent(new Element("rating").setText(audio.getRating() + ""));
      elem.addContent(new Element("playedLast").setText(audio.getPlayedLast() + ""));
      elem.addContent(new Element("dateAdded").setText(audio.getDateAdded()+""));
      elem.addContent(new Element("location").setText(audio.getLocation()));
      elem.addContent(new Element("album").setText(audio.getAlbum()));
      elem.addContent(new Element("genre").setText(audio.getGenre()));
      elem.addContent(new Element("artist").setText(audio.getArtist()));
      elem.addContent(new Element("albumArtist").setText(audio.getAlbumArtist()));
      elem.addContent(new Element("trackId").setText(audio.getTrackId() + ""));
      elem.addContent(new Element("bpm").setText(audio.getBpm() + ""));
      elem.addContent(new Element("publisher").setText(audio.getPublisher()));
      elem.addContent(new Element("composer").setText(audio.getComposer()));
      elem.setAttribute("mimeType", mimeType);
      root.addContent(elem);
      //@formatter:on
    } else if(mimeType.indexOf("video") >= 0) {
      Element elem = new Element("video");
      Video video = (Video) media;
      //@formatter:off
      elem.addContent(new Element("name").setText(video.getName()));
      elem.addContent(new Element("duration").setText(video.getDuration() + ""));
      elem.addContent(new Element("size").setText(video.getSize() + ""));
      elem.addContent(new Element("bitRate").setText(video.getBitRate() + ""));
      elem.addContent(new Element("year").setText(video.getYear() + ""));
      elem.addContent(new Element("playCount").setText(video.getPlayCount() + ""));
      elem.addContent(new Element("rating").setText(video.getRating() + ""));
      elem.addContent(new Element("playedLast").setText(video.getPlayedLast() + ""));
      elem.addContent(new Element("dateAdded").setText(video.getDateAdded()+""));
      elem.addContent(new Element("location").setText(video.getLocation()));
      elem.addContent(new Element("width").setText(video.getWidth() + ""));
      elem.addContent(new Element("height").setText(video.getHeight() + ""));
      elem.addContent(new Element("aspectRatio").setText(video.getAspectRatio()));
      for (String language : video.getLanguage()) {
        elem.addContent(new Element("language").setText(language));
      }
      elem.setAttribute("mimeType", mimeType);
      root.addContent(elem);
      //@formatter:on
    }
  }

  void remove(Media media) {

  }

  void store() throws IOException {
    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
    out.output(doc, new FileWriter(PropertyManager.getMediaXmlFile()));
  }

  public JDomXmlParser() {
    try {
      File xsd = new File(
          JDomXmlParser.class.getResource("/xml/MediaLibFxSchema.xsd").toURI());
      XMLReaderXSDFactory xsdFactory = new XMLReaderXSDFactory(xsd);
      SAXBuilder builder = new SAXBuilder(xsdFactory);
      try {
        doc = builder.build(PropertyManager.getMediaXmlFile());
      } catch (JDOMParseException e) {
        File f = PropertyManager.getMediaXmlFile();
        Files.copy(f.toPath(), new File(f.toString() + ".bak").toPath(),
            StandardCopyOption.REPLACE_EXISTING);
        f.delete();
        f.createNewFile();
        try (PrintWriter out = new PrintWriter(f)) {
          out.print("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><media/>");
        }
        doc = builder.build(f);
      }

    } catch (JDOMException | IOException | URISyntaxException e) {
      // this constructor only gets called in a static context so we rethrow all
      // exceptions as runtimeexceptions
      throw new RuntimeException(e);
    }
  }

  @Deprecated
  int getLastId() {
    return doc.getRootElement().getContentSize();
  }

}
