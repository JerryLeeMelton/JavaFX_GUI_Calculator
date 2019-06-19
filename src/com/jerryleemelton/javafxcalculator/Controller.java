//Jerry Melton
//CSIS139 JAVA (Sat 12:50pm)
//13 - GUI Calculator App


package com.jerryleemelton.javafxcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // FXML Variable references
    @FXML
    private VBox root;

    @FXML
    private Label output;
    @FXML
    private Label operations;

    @FXML
    private Button clearButton;
    @FXML
    private Button percentButton;
    @FXML
    private Button backspaceButton;
    @FXML
    private Button divideButton;

    @FXML
    private Button sevenButton;
    @FXML
    private Button eightButton;
    @FXML
    private Button nineButton;
    @FXML
    private Button multiplyButton;

    @FXML
    private Button fourButton;
    @FXML
    private Button fiveButton;
    @FXML
    private Button sixButton;
    @FXML
    private Button minusButton;

    @FXML
    private Button oneButton;
    @FXML
    private Button twoButton;
    @FXML
    private Button threeButton;
    @FXML
    private Button plusButton;

    @FXML
    private Button plusOrMinusButton;
    @FXML
    private Button zeroButton;
    @FXML
    private Button decimalButton;
    @FXML
    private Button equalsButton;

    // Variables for tracking program input
    private String num1 = "";
    private String operator = "";

    // Booleans to track program state
    private boolean outputStart = true;
    private boolean operationStart = true;

    // KeyCombination Identifiers
    private static final KeyCombination asteriskKeyCode = new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN);
    private static final KeyCombination percentKeyCode = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);
    private static final KeyCombination plusKeyCode = new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN);
    private static final KeyCombination plusOrMinusKeyCode = new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN);
    private static final KeyCombination copyKeyCode = new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setKeyHandler(root);
    }

    @FXML
    private void processNumbers(ActionEvent e) {

        if(outputStart) {
            output.setText("");
            outputStart = false;
        }

        if(operationStart) {
            operations.setText("");
            operationStart = false;
        }

        if(output.getText().length() == 10) return;

        String value = ((Button)e.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperators(ActionEvent e) {
        String value = ((Button)e.getSource()).getText();

        if(!value.equals("=")) {

            if(!operator.isEmpty()) {
                operator = value;
                operations.setText(operations.getText() + " " + output.getText() + " " + operator);
                output.setText(Model.calculate(num1, output.getText(), operator));
                num1 = output.getText();
//                operator = "";
                outputStart = true;
            }
            else {
                operator = value;
                num1 = output.getText();
                operations.setText(operations.getText() + num1 + " " + operator);
                outputStart = true;
            }


        }
        else {

            if(operator.isEmpty()) return;

            operations.setText(operations.getText() + " " + output.getText());
            output.setText(Model.calculate(num1, output.getText(), operator));
            operator = "";
            outputStart = true;
            operationStart = true;
        }

    }

    @FXML
    private void processEtc(ActionEvent e) {

        String value = ((Button)e.getSource()).getText();

        switch (value) {
            case "AC":
                num1 = "";
                operator = "";
                output.setText("0");
                operations.setText("");
                outputStart = true;
                return;

            case "%":
                output.setText(Model.percentConversion(output.getText()));
                return;

            case "BS":  // Backspace Arrow
                if(output.getText().length() == 1) {
                    output.setText("0");
                    outputStart = true;
                    return;
                }
                output.setText(output.getText().substring(0, output.getText().length() - 1));
                return;

            case "±":
                if(output.getText().equals("0")) return;
                if(output.getText().contains("-")) {
                    output.setText(output.getText().replace("-", ""));
                }
                else {
                    output.setText("-" + output.getText());
                }
                return;

            case ".":
                if(outputStart) {
                    output.setText("0.");
                    outputStart = false;
                }
                else {
                    if(output.getText().contains(".")) return;
                    output.setText(output.getText() + ".");
                }
        }
    }

    private void setKeyHandler(VBox root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {

            if(asteriskKeyCode.match(ev)) {
                multiplyButton.fire();
            }
            else if(percentKeyCode.match(ev)) {
                percentButton.fire();
            }
            else if(plusOrMinusKeyCode.match(ev)) {
                plusOrMinusButton.fire();
            }
            else if(plusKeyCode.match(ev)) {
                plusButton.fire();
            }
            else if(copyKeyCode.match(ev)) {
                final ClipboardContent content = new ClipboardContent();

                content.putString(output.getText());
                Clipboard.getSystemClipboard().setContent(content);
            }
            else {
                KeyCode code = ev.getCode();

                switch (code) {
                    case NUMPAD0:
                        zeroButton.fire();
                        break;
                    case NUMPAD1:
                        oneButton.fire();
                        break;
                    case NUMPAD2:
                        twoButton.fire();
                        break;
                    case NUMPAD3:
                        threeButton.fire();
                        break;
                    case NUMPAD4:
                        fourButton.fire();
                        break;
                    case NUMPAD5:
                        fiveButton.fire();
                        break;
                    case NUMPAD6:
                        sixButton.fire();
                        break;
                    case NUMPAD7:
                        sevenButton.fire();
                        break;
                    case NUMPAD8:
                        eightButton.fire();
                        break;
                    case NUMPAD9:
                        nineButton.fire();
                        break;
                    case DIGIT0:
                        zeroButton.fire();
                        break;
                    case DIGIT1:
                        oneButton.fire();
                        break;
                    case DIGIT2:
                        twoButton.fire();
                        break;
                    case DIGIT3:
                        threeButton.fire();
                        break;
                    case DIGIT4:
                        fourButton.fire();
                        break;
                    case DIGIT5:
                        fiveButton.fire();
                        break;
                    case DIGIT6:
                        sixButton.fire();
                        break;
                    case DIGIT7:
                        sevenButton.fire();
                        break;
                    case DIGIT8:
                        eightButton.fire();
                        break;
                    case DIGIT9:
                        nineButton.fire();
                        break;
                    case ENTER:
                        equalsButton.fire();
                        break;
                    case EQUALS:
                        equalsButton.fire();
                        break;
                    case ESCAPE:
                        clearButton.fire();
                        break;
                    case PERIOD:
                        decimalButton.fire();
                        break;
                    case DECIMAL:
                        decimalButton.fire();
                        break;
                    case BACK_SPACE:
                        backspaceButton.fire();
                        break;
                    case DELETE:
                        backspaceButton.fire();
                        break;
                    case SLASH:
                        divideButton.fire();
                        break;
                    case DIVIDE:
                        divideButton.fire();
                        break;
                    case ASTERISK:
                        multiplyButton.fire();
                        break;
                    case MULTIPLY:
                        multiplyButton.fire();
                        break;
                    case MINUS:
                        minusButton.fire();
                        break;
                    case SUBTRACT:
                        minusButton.fire();
                        break;
                    case PLUS:
                        plusButton.fire();
                        break;
                    case ADD:
                        plusButton.fire();
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
