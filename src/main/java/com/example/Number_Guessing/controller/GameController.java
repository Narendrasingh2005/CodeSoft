package com.example.Number_Guessing.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") 
public class GameController {

    private int targetNumber;
    private int attempts;
    private final int maxAttempts = 7;
    private int score = 0;

    public GameController() {
        resetGame();
    }

    @PostMapping("/guess")
    public String guess(@RequestParam int number) {
        if (attempts >= maxAttempts) {
            return "❌ Out of attempts! The number was: " + targetNumber;
        }
        attempts++;
        if (number == targetNumber) {
            score++;
            String msg = "✅ Correct! You guessed in " + attempts + " attempts. Score: " + score;
            resetGame();
            return msg;
        } else if (number < targetNumber) {
            return "📉 Too low!";
        } else {
            return "📈 Too high!";
        }
    }

    @PostMapping("/reset")
    public String reset() {
        resetGame();
        return "New game started!";
    }

    @GetMapping("/score")
    public int getScore() {
        return score;
    }

    private void resetGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
    }
}
