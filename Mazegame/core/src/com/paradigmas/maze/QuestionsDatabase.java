package com.paradigmas.maze;

import java.io.BufferedReader;
import java.io.FileWriter;
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


    public static void deleteCopiedFile() {
        try {
            FileWriter writer = new FileWriter("copied_questions.txt");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveCopiedQuestions() {
        try (FileWriter writer = new FileWriter("copied_questions.txt")) {
            for (String question : questionsDatabase) {
                writer.write(question + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getWording(int questionIndex) {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(questionIndex);
        saveCopiedQuestions();
        String[] parts = question.split(";");
        return parts[0];
    }

    public static String getAlt1(int questionIndex) {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(questionIndex);
        String[] parts = question.split(";");
        return parts[1];
    }

    public static String getAlt2(int questionIndex) {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(questionIndex);
        String[] parts = question.split(";");
        return parts[2];
    }

    public static String getAlt3(int questionIndex) {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(questionIndex);
        String[] parts = question.split(";");
        String alt3 = parts[3];
        return alt3;
    }

    public static int getAnswer(int questionIndex) {
        if (questionsDatabase.isEmpty()) {
            initializeQuestions();
        }
        String question = questionsDatabase.get(questionIndex);
        String[] parts = question.split(";");
        int answer = Integer.parseInt(parts[4]);
        questionsDatabase.remove(questionIndex);
        saveCopiedQuestions();
        return answer;
    }

}
