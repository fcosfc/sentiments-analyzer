package com.wordpress.fcosfc.poc.nlp.service;

import com.wordpress.fcosfc.poc.nlp.model.Sentiment;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class SentimentsService {

    private final StanfordCoreNLP pipeline;
    
    public SentimentsService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        
        pipeline = new StanfordCoreNLP(props);
    }
    
    public List<Sentiment> estimateSentiments(String paragraph) {
        List<Sentiment> estimatedSentiments = new ArrayList<>();
        
        Annotation annotation = pipeline.process(paragraph);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            
            estimatedSentiments.add(
                    new Sentiment(sentence.get(SentimentCoreAnnotations.SentimentClass.class), 
                            RNNCoreAnnotations.getPredictedClass(tree), 
                            sentence.toString()));
        }
        
        return estimatedSentiments;
    }
    
}
