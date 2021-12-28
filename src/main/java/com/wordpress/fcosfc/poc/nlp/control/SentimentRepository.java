package com.wordpress.fcosfc.poc.nlp.control;

import com.wordpress.fcosfc.poc.nlp.entity.Estimation;
import com.wordpress.fcosfc.poc.nlp.entity.Sentiment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
    
    List<Sentiment> findByEstimation(Estimation estimation);
    
}
