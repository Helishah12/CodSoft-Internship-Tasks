import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGame extends JFrame implements ActionListener {
    private final int targetNumber;
    private int attempts;
    private int maxAttempts;
    private final JLabel titleLabel;
    private final JLabel resultLabel;
    private final JLabel hintLabel;
    private final JTextField guessField;
    private final JButton guessButton;

    public NumberGame() {
        this.maxAttempts = 10; // Set a default value for maxAttempts
        targetNumber = 1 + (int) (Math.random() * 100);
        attempts = 0;

        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 220)); // Set the background color

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(165, 42, 42));

        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        titleLabel.setForeground(Color.WHITE); // Set the text color
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(new Color(255, 228, 196)); // Set the background color

        resultLabel = new JLabel("");
        hintLabel = new JLabel("");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        centerPanel.add(resultLabel);
        centerPanel.add(hintLabel);
        centerPanel.add(new JLabel("Your Guess:"));
        centerPanel.add(guessField);
        centerPanel.add(guessButton);

        add(centerPanel, BorderLayout.CENTER);

        // Ask the user for the number of rounds
        String roundsInput = JOptionPane.showInputDialog("Enter the number of rounds:");

        try {
            maxAttempts = Integer.parseInt(roundsInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Using the default number of rounds.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            int guess;
            try {
                guess = Integer.parseInt(guessField.getText());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
                return;
            }

            attempts++;
            if (attempts < maxAttempts) {
                if (guess == targetNumber) {
                    resultLabel.setText("Congratulations! You guessed the correct number.");
                    hintLabel.setText("");
                    guessButton.setEnabled(false);

                    // Display a congratulations message in a JOptionPane
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed the appropriate number.",
                            "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                } else if (guess < targetNumber) {
                    hintLabel.setText("Try a higher number.");
                } else {
                    hintLabel.setText("Try a lower number.");
                }
                resultLabel.setText("Attempts left: " + (maxAttempts - attempts));
            } else {
                resultLabel.setText("You've run out of attempts. The correct number was " + targetNumber + ".");
                hintLabel.setText("");
                guessButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGame game = new NumberGame();
            game.setVisible(true);
        });
    }
}
