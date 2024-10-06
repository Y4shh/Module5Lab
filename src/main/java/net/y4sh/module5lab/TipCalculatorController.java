package net.y4sh.module5lab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
   // formatters for currency and percentages
   private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent = NumberFormat.getPercentInstance();

   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

   // GUI controls defined in FXML and used by the controller's code
   @FXML
   private TextField amountTextField;

   @FXML
   private Label tipPercentageLabel;

   @FXML
   private Slider tipPercentageSlider;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;

   private StringProperty tipProperty = new SimpleStringProperty();
   private StringProperty totalProperty = new SimpleStringProperty();

   public void initialize() {
      currency.setRoundingMode(RoundingMode.HALF_UP);

      tipPercentageLabel.textProperty().bind(tipPercentageSlider.valueProperty().asString("%.0f%%"));

      amountTextField.textProperty().addListener((observable, oldValue, newValue) -> calculate());

      tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
         tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0);
         calculate();
      });

      tipTextField.textProperty().bind(tipProperty);
      totalTextField.textProperty().bind(totalProperty);
   }

   private void calculate() {
      try {
         BigDecimal amount = new BigDecimal(amountTextField.getText());
         BigDecimal tip = amount.multiply(tipPercentage);
         BigDecimal total = amount.add(tip);

         tipProperty.set(currency.format(tip));
         totalProperty.set(currency.format(total));
      } catch (NumberFormatException ex) {
         tipProperty.set("Invalid");
         totalProperty.set("Invalid");
      }
   }
}
/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
