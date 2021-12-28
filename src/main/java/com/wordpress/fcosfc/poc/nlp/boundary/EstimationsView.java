package com.wordpress.fcosfc.poc.nlp.boundary;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.wordpress.fcosfc.poc.nlp.entity.Estimation;
import com.wordpress.fcosfc.poc.nlp.entity.Sentiment;
import com.wordpress.fcosfc.poc.nlp.control.repository.EstimationRepository;
import com.wordpress.fcosfc.poc.nlp.control.repository.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * View to query previous estimations of sentiments
 * 
 * @author Paco Saucedo
 */
@PageTitle("Estimations")
@Route(value = "estimations", layout = MainLayout.class)
public class EstimationsView extends SplitLayout {       
    
    private final SentimentRepository sentimentRepository;
    private final Grid<Estimation> estimationsGrid;
    private final Grid<Sentiment> sentimentGrid;

    @Autowired
    public EstimationsView(EstimationRepository estimationRepository, SentimentRepository sentimentRepository) {
        this.sentimentRepository = sentimentRepository;
        
        estimationsGrid = new Grid<>(Estimation.class, false);
        estimationsGrid.addColumn(Estimation::getEstimationDate).setHeader("Estimation date");
        estimationsGrid.addColumn(Estimation::getParagraph).setHeader("Paragraph");        
        estimationsGrid.setItems(estimationRepository.findAllByOrderByEstimationDateDesc());    
        estimationsGrid.asSingleSelect().addValueChangeListener(e -> loadSentiments(e));
        addToPrimary(estimationsGrid);
        
        sentimentGrid = new Grid<>(Sentiment.class, false); 
        sentimentGrid.addColumn(Sentiment::getSentence).setHeader("Sentence");
        sentimentGrid.addColumn(Sentiment::getSentimentName).setHeader("Sentiment");   
        sentimentGrid.setSelectionMode(Grid.SelectionMode.NONE);
        sentimentGrid.setVisible(false);
        addToSecondary(sentimentGrid);
    }        

    private void loadSentiments(AbstractField.ComponentValueChangeEvent<Grid<Estimation>, Estimation> e) {
        if (e == null) {
            sentimentGrid.setVisible(false);
        } else {
            sentimentGrid.setItems(sentimentRepository.findByEstimation(e.getValue()));            
            sentimentGrid.setVisible(true);
        }
    }
    
}
