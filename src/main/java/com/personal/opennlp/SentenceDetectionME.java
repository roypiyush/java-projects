package com.personal.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.InputStream;

public class SentenceDetectionME {

    public static void main(String args[]) throws Exception {

        String sentence = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp."
                + "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.\n" +
                "Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group.\n" +
                "Rudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC,\n" +
                "was named a director of this British industrial conglomerate.";
        //Loading sentence detector model
        try (InputStream inputStream
                     = new FileInputStream(new SentenceDetectionME().getClass().getClassLoader()
                .getResource("opennlp/en-sent.bin").getFile())) {

            SentenceModel model = new SentenceModel(inputStream);

            //Instantiating the SentenceDetectorME class
            SentenceDetectorME detector = new SentenceDetectorME(model);

            //Detecting the sentence
            String sentences[] = detector.sentDetect(sentence);

            //Printing the sentences
            for (String sent : sentences) {
                System.out.println(sent);
            }

            //Getting the probabilities of the last decoded sequence
            double[] probs = detector.getSentenceProbabilities();

            System.out.println("  ");

            for (int i = 0; i < probs.length; i++)
                System.out.println(probs[i]);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}