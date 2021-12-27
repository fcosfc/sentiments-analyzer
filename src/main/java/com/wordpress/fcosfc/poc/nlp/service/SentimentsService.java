package com.wordpress.fcosfc.poc.nlp.service;

import com.wordpress.fcosfc.poc.nlp.model.Estimation;
import com.wordpress.fcosfc.poc.nlp.model.Sentiment;
import com.wordpress.fcosfc.poc.nlp.repository.EstimationRepository;
import com.wordpress.fcosfc.poc.nlp.repository.SentimentRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentsService {

    private final EstimationRepository estimationRepository;
    private final SentimentRepository sentimentRepository;
    private final StanfordCoreNLP pipeline;

    @Autowired
    public SentimentsService(EstimationRepository estimationRepository, SentimentRepository sentimentRepository) {
        this.estimationRepository = estimationRepository;
        this.sentimentRepository = sentimentRepository;

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

        pipeline = new StanfordCoreNLP(props);
    }

    public List<Sentiment> estimateSentiments(String paragraph) {
        List<Sentiment> estimatedSentiments = new ArrayList<>();
        Estimation estimation = new Estimation(paragraph);
        estimation = estimationRepository.save(estimation);

        Annotation annotation = pipeline.process(paragraph);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

            Sentiment sentiment = new Sentiment(sentence.get(SentimentCoreAnnotations.SentimentClass.class),
                    RNNCoreAnnotations.getPredictedClass(tree),
                    sentence.toString(),
                    estimation);
            estimatedSentiments.add(sentiment);
            sentimentRepository.save(sentiment);
        }
        estimation.setSentimentsList(estimatedSentiments);

        return estimation.getSentimentsList();
    }

}
