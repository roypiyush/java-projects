package com.personal.opennlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.FileInputStream;
import java.io.InputStream;

public class PosTaggerExample {

    public static void main(String args[]) throws Exception {

        //Loading Parts of speech-maxent model
        try (
                InputStream inputStream = new FileInputStream(new SentenceDetectionME().getClass().getClassLoader()
                        .getResource("opennlp/en-pos-maxent.bin").getFile())) {

            POSModel model = new POSModel(inputStream);

            //Instantiating POSTaggerME class
            POSTaggerME tagger = new POSTaggerME(model);

            String sentence = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp.";

            //Tokenizing the sentence using WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            //Generating tags
            String[] tags = tagger.tag(tokens);

            //Instantiating the POSSample class
            POSSample sample = new POSSample(tokens, tags);
            System.out.println(sample.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}