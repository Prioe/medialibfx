package de.malbertz.medialibfx.model;

import static org.junit.Assert.*;

import org.junit.Test;

import de.malbertz.medialibfx.model.properties.PropertyManager;

public class PropertyManagerTest {

   @Test
   public void test() {
      assertNull(PropertyManager.get("junit.nulltest"));
      
   }

}
