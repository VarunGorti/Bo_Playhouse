package com.example.bodeng.boplayhouse;

import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

class BattleCircleGame {

    private int score = 0;

    private static HashMap<String, ArrayList<String>> categories;
    private static List<String> questions;
    private static List<String> curAnswers;
    private HashSet<Character> usedLetters;
    private HashSet<String> usedWords;

    private static String curQuestion;

    private static int numTurn = 0;

    public BattleCircleGame() throws IOException {
        if (numTurn == 0) {

            // Category 1: Boy names
            categories = new HashMap<>();
            categories.put("Boy names", new ArrayList<String>());
            categories.get("Boy names").add("a");
            categories.get("Boy names").add("b");
            categories.get("Boy names").add("c");
            categories.get("Boy names").add("d");
            categories.get("Boy names").add("e");
            categories.get("Boy names").add("f");
            categories.get("Boy names").add("g");
            categories.get("Boy names").add("h");
            categories.get("Boy names").add("i");
            categories.get("Boy names").add("j");
            categories.get("Boy names").add("k");
            categories.get("Boy names").add("la");

//            try (BufferedReader br =
//                         new BufferedReader(new FileReader("boyNames.txt"))) {
//                addBoyNamesToTree(br);
//            }



            // Choose a random question and populate the answers based on that
            Random random = new Random();
            questions = new ArrayList<>(categories.keySet());
            curQuestion = questions.get(random.nextInt(questions.size()));
            curAnswers = categories.get(curQuestion);

            System.out.println(curQuestion + "    " + curAnswers.toString());

        }
        numTurn++;
        usedLetters = new HashSet<>();
        usedWords = new HashSet<>();

    }

    // pre: attempt != null and attempt != empty string and the user has not tried this word already
    // Codes: -1 = This is not a valid word, or has already been tried
    //         0 = This word is valid, but you LOST rip
    //         1 = This word is valid and you can keep going
    public int tryWord(String attempt) {
        if (attempt != null && !attempt.equals("") && !usedWords.contains(attempt) && curAnswers.contains(attempt)) {
            char firstLetter = attempt.charAt(0);
            if (usedLetters.contains(firstLetter)) {
                return 0;
            } else {
                usedLetters.add(attempt.charAt(0));
                usedWords.add(attempt);
                score++;
                return 1;
            }
        } else { // This means that the word is null, empty, not a real word, or has been tried
            return -1;
        }
    }

    private void addBoyNamesToTree(BufferedReader reader) throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            categories.get("Boy names").add(line);
        }
    }

    public int getScore() {
        return score;
    }

    public static HashMap<String, ArrayList<String>> getCategories() {
        return categories;
    }

    public static List<String> getQuestions() {
        return questions;
    }

    public static String getCurQuestion() {
        return curQuestion;
    }

    public static List<String> getCurAnswers() {
        return curAnswers;
    }

    public HashSet<Character> getUsedLetters() {
        return usedLetters;
    }

    public static int getNumTurn() {
        return numTurn;
    }

    public String getInstructions() {
        return "Given a category, type as many words as matching the category as you can think of! " +
                "But there's a catch! If any of your words end with a letter that starts one of your other " +
                "words, you lose! After 30 seconds, your score is the number of words you get. Good luck!";
    }


}
