package com.personal.opennlp;

import opennlp.tools.tokenize.WhitespaceTokenizer;

public class WhitespaceTokenizerExample {

    public static void main(String args[]) {

        String sentence = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp.";

        //Instantiating whitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;

        //Tokenizing the given paragraph
        String tokens[] = whitespaceTokenizer.tokenize(sentence);

        //Printing the tokens
        for (String token : tokens)
            System.out.println(token);
    }
}