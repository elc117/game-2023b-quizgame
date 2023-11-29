package com.paradigmas.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class QuestionsDatabase {

    private static ArrayList<String> questionsDatabase = new ArrayList<>();

    public static void initializeQuestions() {
        copyQuestionsFile();
    }

    private static void copyQuestionsFile() {
        try (BufferedReader reader = new BufferedReader(Gdx.files.internal("questions.txt").reader())) {
            String line;
            while ((line = reader.readLine()) != null) {
                questionsDatabase.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getWording() {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.remove(0);
        String[] parts = question.split(";");
        return parts[0];
    }

    public static String getAlt1() {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(0);
        String[] parts = question.split(";");
        return parts[1];
    }

    public static String getAlt2() {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(0);
        String[] parts = question.split(";");
        return parts[2];
    }

    public static String getAlt3() {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(0);
        String[] parts = question.split(";");
        String alt3 = parts[3];
        questionsDatabase.remove(0);
        return alt3;
    }

    public static int getAnswer() {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(0);
        String[] parts = question.split(";");
        int answer = Integer.parseInt(parts[4]);
        questionsDatabase.remove(0);
        return answer;
    }

}
