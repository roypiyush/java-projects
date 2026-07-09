package com.personal.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.InputStream;

public class SentencePosDetection {

    public static void main(String args[]) throws Exception {

        String paragraph = "Hi. How are you? Welcome to github/roypiyush. Here you are given with examples of opennlp?";

        try (//Loading sentence detector model
             InputStream inputStream = new FileInputStream(new SentenceDetectionME().getClass().getClassLoader()
                     .getResource("opennlp/en-sent.bin").getFile())) {
            SentenceModel model = new SentenceModel(inputStream);

            //Instantiating the SentenceDetectorME class
            SentenceDetectorME detector = new SentenceDetectorME(model);

            //Detecting the position of the sentences in the raw text
            Span spans[] = detector.sentPosDetect(paragraph);

            //Printing the spans of the sentences in the paragraph
            for (Span span : spans)
                System.out.println(span);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}