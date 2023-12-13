import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    // Create a TextField to display the numbers and results
    private TextField displayField;

    // Variables to store the calculation state
    private double num1;
    private double num2;
    private String operator;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");

        // Create the grid layout for the calculator buttons
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Create the display field
        displayField = new TextField();
        displayField.setEditable(false);
        displayField.setPrefColumnCount(4);
        gridPane.add(displayField, 0, 0, 4, 1);

        // Create the buttons
        String[][] buttonLabels = {
                { "7", "8", "9", "/" },
                { "4", "5", "6", "*" },
                { "1", "2", "3", "-" },
                { "0", ".", "=", "+" }
        };

        for (int row = 0; row < buttonLabels.length; row++) {
            for (int col = 0; col < buttonLabels[row].length; col++) {
                Button button = new Button(buttonLabels[row][col]);
                button.setPrefWidth(50);
                button.setOnAction(e -> handleButtonClick(button.getText()));
                gridPane.add(button, col, row + 1);
            }
        }

        // Create the scene and set it on the primary stage
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String buttonLabel) {
        if (buttonLabel.matches("[0-9.]")) {
            displayField.setText(displayField.getText() + buttonLabel);
        } else if (buttonLabel.matches("[/+\\-*]")) {
            operator = buttonLabel;
            num1 = Double.parseDouble(displayField.getText());
            displayField.clear();
        } else if (buttonLabel.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            double result = calculateResult(num1, num2, operator);
            displayField.setText(Double.toString(result));
        }
    }

    private double calculateResult(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    System.out.println("Error: Division by zero");
                    return 0.0;
                }
            default:
                System.out.println("Error: Invalid operator");
                return 0.0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
