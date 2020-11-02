package sample;

/**
 * class controller for download and set data from site
 */

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import parser.PageLoader;

public class Controller {

  @FXML
  private Text getFirstInfo;

  @FXML
  private Text getSecondInfo;

  @FXML
  private Text getThirdInfo;

  @FXML
  private Text getLastInfo;

  @FXML
  void initialize() {
      List<String> list = new PageLoader().getResultList();
      getFirstInfo.setText(list.get(0));
      getSecondInfo.setText("Температура: " + list.get(1));
      getThirdInfo.setText(list.get(2));
      getLastInfo.setText("Температура: " + list.get(3));
  }
}
