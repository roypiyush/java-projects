package com.personal.opennlp;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

public class SimpleTokenizerExample {
    public static void main(String args[]) {

        String sentence = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp.";

        //Instantiating SimpleTokenizer class
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        //Tokenizing the given sentence
        String tokens[] = simpleTokenizer.tokenize(sentence);
        Span[] spans = simpleTokenizer.tokenizePos(sentence);

        for (int i = 0; i < spans.length; i++) {
            System.out.println(tokens[i] + " "
                    + spans[i] + " " + sentence.substring(spans[i].getStart(), spans[i].getEnd()));
        }
    }
}