package de.malbertz.medialibfx.main;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.malbertz.medialibfx.controller.MainController;
import de.malbertz.medialibfx.model.properties.PropertyManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainApp extends Application {

  private static final Logger log = LoggerFactory.getLogger(MainApp.class);
  private MainController mainController;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    String mainFxmlFile = "/fxml/MainView.fxml";
    ResourceBundle bundle = ResourceBundle.getBundle("bundle.UIBundle",
        new Locale("en"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource(mainFxmlFile));
    loader.setResources(bundle);
    Parent mainParent = loader.load();
    
    mainController = (MainController) loader.getController();
    Context.getInstance().setMainController(mainController);
    Scene mainScene = new Scene(mainParent);
    mainScene.getStylesheets().add(PropertyManager.get("window.stylesheets.colors"));
    mainScene.getStylesheets().add(PropertyManager.get("window.stylesheets.layout"));
    primaryStage.setScene(mainScene);
    primaryStage
        .setWidth(Double.parseDouble(PropertyManager.get("window.width")));
    primaryStage
        .setHeight(Double.parseDouble(PropertyManager.get("window.height")));
    primaryStage.setX(Double.parseDouble(PropertyManager.get("window.position.x")));
    primaryStage.setY(Double.parseDouble(PropertyManager.get("window.position.y")));
    primaryStage.setTitle(bundle.getString("window.title"));
    primaryStage.show();

  }

  @Override
  public void stop() {
    try {
      log.debug("Stopping app ...");
      Platform.runLater(() -> mainController.getPlayer().release());
      Window window = mainController.getContentPane()
          .getScene().getWindow();
      PropertyManager.set("window.height", window.getHeight() + "");
      PropertyManager.set("window.width", window.getWidth() + "");
      PropertyManager.set("window.position.x", window.getX() + "");
      PropertyManager.set("window.position.y", window.getY() + "");
      log.debug("Saved loc and bounds. App is closing successfully.");
    } catch (Exception e) {
      log.error("An error occured while saving window properties on stop", e);
    }

  }

  public static void main(String[] args) {
    Application.launch(args);
  }

}
