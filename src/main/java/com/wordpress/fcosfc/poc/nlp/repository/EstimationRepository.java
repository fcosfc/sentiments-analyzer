package com.wordpress.fcosfc.poc.nlp.repository;

import com.wordpress.fcosfc.poc.nlp.model.Estimation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimationRepository extends JpaRepository<Estimation, Long> {  
    
    List<Estimation> findAllByOrderByEstimationDateDesc();
    
}
