package net.y4sh.module5lab;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.converter.NumberStringConverter;

public class ColorChooserController {
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    private IntegerProperty red = new SimpleIntegerProperty(0);
    private IntegerProperty green = new SimpleIntegerProperty(0);
    private IntegerProperty blue = new SimpleIntegerProperty(0);
    private DoubleProperty alpha = new SimpleDoubleProperty(1.0);

    public void initialize() {
        Bindings.bindBidirectional(redTextField.textProperty(), redSlider.valueProperty(), new NumberStringConverter("#"));
        Bindings.bindBidirectional(greenTextField.textProperty(), greenSlider.valueProperty(), new NumberStringConverter("#"));
        Bindings.bindBidirectional(blueTextField.textProperty(), blueSlider.valueProperty(), new NumberStringConverter("#"));
        Bindings.bindBidirectional(alphaTextField.textProperty(), alphaSlider.valueProperty(), new NumberStringConverter("#.##"));

        red.bind(redSlider.valueProperty());
        green.bind(greenSlider.valueProperty());
        blue.bind(blueSlider.valueProperty());
        alpha.bind(alphaSlider.valueProperty());
        redSlider.valueProperty().addListener((obs, oldValue, newValue) -> updateColor());
        greenSlider.valueProperty().addListener((obs, oldValue, newValue) -> updateColor());
        blueSlider.valueProperty().addListener((obs, oldValue, newValue) -> updateColor());
        alphaSlider.valueProperty().addListener((obs, oldValue, newValue) -> updateColor());

        updateColor();
    }

    private void updateColor() {
        colorRectangle.setFill(Color.rgb(red.get(), green.get(), blue.get(), alpha.get()));
    }
}