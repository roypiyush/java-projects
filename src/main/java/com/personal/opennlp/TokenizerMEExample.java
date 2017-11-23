package com.personal.opennlp;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.InputStream;

public class TokenizerMEExample {

    public static void main(String args[]) throws Exception {

        String sentence = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp.";

        try (//Loading the Tokenizer model
             InputStream inputStream = new FileInputStream(new SentenceDetectionME().getClass().getClassLoader()
                     .getResource("opennlp/en-token.bin").getFile())) {

            TokenizerModel tokenModel = new TokenizerModel(inputStream);

            //Instantiating the TokenizerME class
            TokenizerME tokenizer = new TokenizerME(tokenModel);
            tokenizer.tokenizePos(sentence); // This required for finding probabilities
            double[] probs = tokenizer.getTokenProbabilities();

            //Tokenizing the given raw text
            String tokens[] = tokenizer.tokenize(sentence);

            //Printing the tokens
            for (int i = 0; i < tokens.length; i++) {
                System.out.println(tokens[i] + " " + probs[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}