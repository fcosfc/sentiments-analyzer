package com.wordpress.fcosfc.poc.nlp.control.event;

import com.wordpress.fcosfc.poc.nlp.control.repository.EstimationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manager that saves estimations for further reference.
 * Decouples the estimation process from persistence.
 * 
 * @author Paco Saucedo
 */
@Component
@Transactional
public class EstimationPerformedEventManager implements ApplicationListener<EstimationPerformedEvent> {

    private final EstimationRepository estimationRepository;
    
    @Autowired
    public EstimationPerformedEventManager(EstimationRepository estimationRepository) {
        this.estimationRepository = estimationRepository;
    }
      
    @Override
    public void onApplicationEvent(EstimationPerformedEvent event) {
        estimationRepository.save(event.getEstimation());
    }        
    
}
