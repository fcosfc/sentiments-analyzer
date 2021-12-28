package com.wordpress.fcosfc.poc.nlp.control.service;

import com.wordpress.fcosfc.poc.nlp.control.event.EstimationPerformedEvent;
import com.wordpress.fcosfc.poc.nlp.entity.Estimation;
import com.wordpress.fcosfc.poc.nlp.entity.Sentiment;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for NLP analizing of sentiments
 * 
 * @author Paco Saucedo.
 */
@Service
public class SentimentsService {

    private final StanfordCoreNLP pipeline;
    
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public SentimentsService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

        pipeline = new StanfordCoreNLP(props);
    }
    
    /**
     * Method for estimating sentiments
     * 
     * @param paragraph Text to analyze
     * @return List of sentiments
     */
    public List<Sentiment> estimateSentiments(String paragraph) {
        List<Sentiment> estimatedSentiments = new ArrayList<>();
        Estimation estimation = new Estimation(paragraph);

        Annotation annotation = pipeline.process(paragraph);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

            Sentiment sentiment = new Sentiment(sentence.get(SentimentCoreAnnotations.SentimentClass.class),
                    sentence.toString(),
                    estimation);
            estimatedSentiments.add(sentiment);
        }
        estimation.setSentimentsList(estimatedSentiments);
        
        applicationEventPublisher.publishEvent(new EstimationPerformedEvent(estimation, this));

        return estimation.getSentimentsList();
    }

}
