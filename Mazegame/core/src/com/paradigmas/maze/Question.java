package com.paradigmas.maze;

public class Question {

    protected String question;
    protected String[] options;
    protected int answer;

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options = new String[options.length]; // Initialize the options array

        for (int i = 0; i < options.length; i++) {
            this.options[i] = options[i];
        }

        this.answer = answer;
    }
}
