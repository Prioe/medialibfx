package de.malbertz.medialibfx.model.player.internal.vlcj;

import java.nio.ByteBuffer;

import com.sun.jna.Memory;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritablePixelFormat;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
/**
 * From https://github.com/caprica/vlcj-javafx
 *
 */
public abstract class DirectRendering extends Canvas {
  
  private final PixelWriter pixelWriter;
  private final WritablePixelFormat<ByteBuffer> pixelFormat;
  
  private final DirectMediaPlayerComponent mediaPlayerComponent;
  
  public DirectRendering() {
    super();
    
    pixelWriter = getGraphicsContext2D().getPixelWriter();
    pixelFormat = PixelFormat.getByteBgraInstance();
    
    mediaPlayerComponent = new MediaPlayerComponent();
  }

  private class MediaPlayerComponent extends DirectMediaPlayerComponent {

    public MediaPlayerComponent() {
      super(new BufferFormatCallbackImpl());
    }
    
  }
  
  private class BufferFormatCallbackImpl implements BufferFormatCallback {

    @Override
    public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
      final int width = 720;
      final int height = 580;
      
      Platform.runLater(() -> {
        DirectRendering.this.setWidth(width);
        DirectRendering.this.setHeight(height);
      });
      
      return new RV32BufferFormat(width, height);
    }
    
  }
  
  protected void renderFrame() {
    Memory[] nativeBuffers = mediaPlayerComponent.getMediaPlayer().lock();
    if (nativeBuffers != null) {
      Memory nativeBuffer = nativeBuffers[0];
      ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0, nativeBuffer.size());
      BufferFormat bufferFormat = ((DefaultDirectMediaPlayer) mediaPlayerComponent.getMediaPlayer()).getBufferFormat();
      if (bufferFormat.getWidth() > 0 && bufferFormat.getHeight() > 0) {
        pixelWriter.setPixels(0, 0, bufferFormat.getWidth(), bufferFormat.getHeight(), pixelFormat, byteBuffer, bufferFormat.getPitches()[0]);
      }
    }
    mediaPlayerComponent.getMediaPlayer().unlock();
  }
  
  protected abstract void startTimer();
  protected abstract void stopTimer();
  
}
