package com.paradigmas.maze;

import java.util.ArrayList;

public class QuestionsDatabase {

    public static ArrayList<Question> questionsDatabase = new ArrayList<Question>();

    public static void initializeQuestions() {
        String[] opcoes1 = {"160 mil anos", "1 bilhao de anos", "235 milhoes de anos"};
        Question questao1 = new Question("Quantos anos tem o fossil mais antigo encontrado no Rio Grande do Sul (aproximadamente) ?", opcoes1, 3);
        questionsDatabase.add(questao1);
        
        String[] opcoes2 = {"4,5 metros", "1,2 metro", "10 metros"};
        Question questao2 = new Question("Que tamanho tem o maior fossil ja encontrado no Rio Grande do Sul ?", opcoes2, 1);
        questionsDatabase.add(questao2);
        
        String[] opcoes3 = {"Siberia", "Rio Grande do Sul", "California"};
        Question questao3 = new Question("Qual lugar no mundo possui os fosseis mais antigos ja registrados ?", opcoes3, 2);
        questionsDatabase.add(questao3);
        
        String[] opcoes4 = {"Triceratops", "Saturnalião", "Tiranossauro"};
        Question questao4 = new Question("Qual desses foi um dinossauro que viveu em solo gaucho?", opcoes4, 2);
        questionsDatabase.add(questao4);
        
        String[] opcoes5 = {"Estauricossauro", "Estegossauro", "Mosassauro"};
        Question questao5 = new Question("Qual desses dinossauros e' foi dos primeiros a existirem na terra?", opcoes5, 1);
        questionsDatabase.add(questao5);
       
    }
}
