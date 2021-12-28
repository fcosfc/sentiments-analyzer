package com.wordpress.fcosfc.poc.nlp.control.event;

import com.wordpress.fcosfc.poc.nlp.entity.Estimation;
import org.springframework.context.ApplicationEvent;

/**
 * Event for a finished estimation
 * 
 * @author Paco Saucedo
 */
public class EstimationPerformedEvent extends ApplicationEvent {
    
    private final Estimation estimation;

    public EstimationPerformedEvent(Estimation estimation, Object source) {
        super(source);
        this.estimation = estimation;
    }

    public Estimation getEstimation() {
        return estimation;
    }        
    
}
