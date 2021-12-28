package com.wordpress.fcosfc.poc.nlp.control;

import com.wordpress.fcosfc.poc.nlp.entity.Estimation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimationRepository extends JpaRepository<Estimation, Long> {  
    
    List<Estimation> findAllByOrderByEstimationDateDesc();
    
}
