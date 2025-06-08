package mycalulaor.com;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";
    private boolean operatorClicked = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Calculator window = new Calculator();
            window.frame.setVisible(true);
        });
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Scientific Calculator");
        frame.setSize(550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(5, 5));

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 5, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Colors
        Color numColor = new Color(224, 224, 224);
        Color opColor = new Color(255, 165, 0);
        Color sciColor = new Color(173, 216, 230);
        Color clrColor = new Color(255, 99, 71);
        Color eqColor = new Color(144, 238, 144);

        // Row 1: Numbers and basic operators
        addButton("7", numColor, buttonPanel);
        addButton("8", numColor, buttonPanel);
        addButton("9", numColor, buttonPanel);
        addOpButton("+", opColor, buttonPanel);
        addOpButton("-", opColor, buttonPanel);

        addButton("4", numColor, buttonPanel);
        addButton("5", numColor, buttonPanel);
        addButton("6", numColor, buttonPanel);
        addOpButton("*", opColor, buttonPanel);
        addOpButton("/", opColor, buttonPanel);

        addButton("1", numColor, buttonPanel);
        addButton("2", numColor, buttonPanel);
        addButton("3", numColor, buttonPanel);
        addButton("=", eqColor, buttonPanel, e -> calculate());
        addButton("C", clrColor, buttonPanel, e -> clear());

        addButton("0", numColor, buttonPanel);
        addButton(".", numColor, buttonPanel);
        addSciButton("sin", sciColor, buttonPanel);
        addSciButton("cos", sciColor, buttonPanel);
        addSciButton("tan", sciColor, buttonPanel);

        addSciButton("log", sciColor, buttonPanel);
        addSciButton("sqrt", sciColor, buttonPanel);
        addSciButton("x^2", sciColor, buttonPanel);
        addSciButton("1/x", sciColor, buttonPanel);
        addSciButton("exp", sciColor, buttonPanel);
    }

    private void addButton(String label, Color bg, JPanel panel) {
        addButton(label, bg, panel, e -> appendNumber(label));
    }

    private void addButton(String label, Color bg, JPanel panel, ActionListener action) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bg);
        button.setFocusPainted(false);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addOpButton(String label, Color bg, JPanel panel) {
        addButton(label, bg, panel, e -> setOperator(label));
    }

    private void addSciButton(String label, Color bg, JPanel panel) {
        addButton(label, bg, panel, e -> scientificOperation(label));
    }

    private void appendNumber(String num) {
        if (operatorClicked) {
            textField.setText("");
            operatorClicked = false;
        }
        textField.setText(textField.getText() + num);
    }

    private void setOperator(String op) {
        try {
            num1 = Double.parseDouble(textField.getText());
            operator = op;
            operatorClicked = true;
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void calculate() {
        try {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        textField.setText("Divide by 0");
                        return;
                    }
                    break;
                default:
                    return;
            }
            textField.setText(String.valueOf(result));
            operator = "";
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void scientificOperation(String op) {
        try {
            double val = Double.parseDouble(textField.getText());
            switch (op) {
                case "sin": result = Math.sin(Math.toRadians(val)); break;
                case "cos": result = Math.cos(Math.toRadians(val)); break;
                case "tan": result = Math.tan(Math.toRadians(val)); break;
                case "log": result = Math.log10(val); break;
                case "sqrt": result = Math.sqrt(val); break;
                case "x^2": result = Math.pow(val, 2); break;
                case "1/x":
                    if (val != 0) result = 1 / val;
                    else {
                        textField.setText("Divide by 0");
                        return;
                    }
                    break;
                case "exp": result = Math.exp(val); break;
                default: return;
            }
            textField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textField.setText("Invalid input");
        }
    }

    private void clear() {
        textField.setText("");
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = "";
        operatorClicked = false;
    }
}
