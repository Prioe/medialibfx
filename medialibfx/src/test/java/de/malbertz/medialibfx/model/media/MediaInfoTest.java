package de.malbertz.medialibfx.model.media;

import java.io.File;

import org.junit.Test;

import de.malbertz.medialibfx.model.media.mediainfo.MediaInfo;
import de.malbertz.medialibfx.model.media.mediainfo.MissingMimeLookupTable;

public class MediaInfoTest {

  @Test
  public void test() {
    final String name = "/Example.ogg";
    MediaInfo mi = new MediaInfo();
    mi.Open(name);
    mi.Option("Complete", "");
    mi.Inform();   
    mi.Get(MediaInfo.StreamKind.General, 0, "InternetMediaType",
        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
    MissingMimeLookupTable.lookup(new File(name));
  }

}
