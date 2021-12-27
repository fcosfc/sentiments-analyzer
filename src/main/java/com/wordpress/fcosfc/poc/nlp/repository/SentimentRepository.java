package com.wordpress.fcosfc.poc.nlp.repository;

import com.wordpress.fcosfc.poc.nlp.model.Estimation;
import com.wordpress.fcosfc.poc.nlp.model.Sentiment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
    
    List<Sentiment> findByEstimation(Estimation estimation);
    
}
