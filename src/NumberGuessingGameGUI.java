package src;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 10;
    private int score = 0;
    
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private JButton newGameButton;

    public NumberGuessingGameGUI() {
        // Configuration de la fenêtre
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Création du panel principal avec un fond gris foncé
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(51, 51, 51));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel pour les informations du jeu
        JPanel gameInfoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        gameInfoPanel.setBackground(new Color(51, 51, 51));
        
        messageLabel = new JLabel("Guess a number between 1 and 100!");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setHorizontalAlignment(JLabel.CENTER);
        
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        
        gameInfoPanel.add(messageLabel);
        gameInfoPanel.add(attemptsLabel);
        gameInfoPanel.add(scoreLabel);

        // Panel pour la saisie
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(51, 51, 51));
        
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(e -> checkGuess());
        
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        // Panel pour le bouton nouvelle partie
        JPanel newGamePanel = new JPanel(new FlowLayout());
        newGamePanel.setBackground(new Color(51, 51, 51));
        
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());
        newGamePanel.add(newGameButton);

        // Ajout des panels au panel principal
        mainPanel.add(gameInfoPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(newGamePanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        // Démarrage d'une nouvelle partie
        startNewGame();

        // Configuration des raccourcis clavier
        guessField.addActionListener(e -> checkGuess());
    }

    private void startNewGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 10;
        
        messageLabel.setText("Guess a number between 1 and 100!");
        messageLabel.setForeground(Color.WHITE);
        attemptsLabel.setText("Attempts left: " + maxAttempts);
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            maxAttempts--;

            if (guess == numberToGuess) {
                int pointsEarned = maxAttempts * 10; // Plus d'essais restants = plus de points
                score += pointsEarned;
                messageLabel.setText("Congratulations! You won! +" + pointsEarned + " points!");
                messageLabel.setForeground(Color.GREEN);
                scoreLabel.setText("Score: " + score);
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            } else if (maxAttempts == 0) {
                messageLabel.setText("Game Over! The number was " + numberToGuess);
                messageLabel.setForeground(Color.RED);
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            } else if (guess < numberToGuess) {
                messageLabel.setText("Try a higher number!");
                messageLabel.setForeground(Color.ORANGE);
            } else {
                messageLabel.setText("Try a lower number!");
                messageLabel.setForeground(Color.ORANGE);
            }

            attemptsLabel.setText("Attempts left: " + maxAttempts);
            guessField.setText("");
            guessField.requestFocus();

        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number!");
            messageLabel.setForeground(Color.RED);
        }
    }
}