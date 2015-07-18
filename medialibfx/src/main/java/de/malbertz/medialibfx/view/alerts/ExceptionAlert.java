package de.malbertz.medialibfx.view.alerts;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ExceptionAlert extends Alert {
  private static final Logger log = LoggerFactory.getLogger(ExceptionAlert.class);
  
  public ExceptionAlert(Exception e) {
    super(AlertType.ERROR);
    log.error("Exception Alert triggered", e);
    setTitle("Exception Dialog");
    setHeaderText(e.getLocalizedMessage());
    
    // Create expandable Exception.
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String exceptionText = sw.toString();

    Label label = new Label("The exception stacktrace was:");

    TextArea textArea = new TextArea(exceptionText);
    textArea.setEditable(false);
    textArea.setWrapText(true);

    textArea.setMaxWidth(Double.MAX_VALUE);
    textArea.setMaxHeight(Double.MAX_VALUE);
    GridPane.setVgrow(textArea, Priority.ALWAYS);
    GridPane.setHgrow(textArea, Priority.ALWAYS);

    GridPane expContent = new GridPane();
    expContent.setMaxWidth(Double.MAX_VALUE);
    expContent.add(label, 0, 0);
    expContent.add(textArea, 0, 1);

    // Set expandable Exception into the dialog pane.
    getDialogPane().setExpandableContent(expContent);
  }
}
