package com.wordpress.fcosfc.poc.nlp.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.wordpress.fcosfc.poc.nlp.model.Sentiment;
import com.wordpress.fcosfc.poc.nlp.service.SentimentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Sentiments analyzer")
@Route(value = "analyzer", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SentimentsAnalyzerView extends VerticalLayout {

    private static final int CHAR_LIMIT = 500;

    private final SentimentsService sentimentsService;

    private final TextArea sentimentTxt;
    Grid<Sentiment> estimatedSentimentsGrid;
    private final Button analyzeBtn, clearBtn;

    @Autowired
    public SentimentsAnalyzerView(SentimentsService sentimentsService) {
        this.sentimentsService = sentimentsService;

        sentimentTxt = new TextArea("Please, type your sentiments in English language");
        sentimentTxt.setWidthFull();
        sentimentTxt.setRequired(true);
        sentimentTxt.setMaxLength(CHAR_LIMIT);
        sentimentTxt.setValueChangeMode(ValueChangeMode.EAGER);
        sentimentTxt.addValueChangeListener(e -> {
            e.getSource().setHelperText(e.getValue().length() + "/" + CHAR_LIMIT);
        });        
        
        estimatedSentimentsGrid = new Grid<>(Sentiment.class, false); 
        estimatedSentimentsGrid.addColumn(Sentiment::getSentence).setHeader("Sentence");
        estimatedSentimentsGrid.addColumn(Sentiment::getSentimentName).setHeader("Sentiment");   
        estimatedSentimentsGrid.setSelectionMode(Grid.SelectionMode.NONE);
        estimatedSentimentsGrid.setWidthFull();          
        estimatedSentimentsGrid.setVisible(false);

        analyzeBtn = new Button("Analyze");
        analyzeBtn.addClickListener(e -> {
            estimateSentiments(sentimentTxt.getValue());
        });

        clearBtn = new Button("Clear");
        clearBtn.addClickListener(e -> {
            clearTexts();
        });
        clearBtn.setVisible(false);

        setMargin(true);

        add(sentimentTxt, analyzeBtn, estimatedSentimentsGrid, clearBtn);
    }

    void estimateSentiments(String paragraph) {
        if (paragraph.isEmpty()) {
            clearTexts();
        } else {
            List<Sentiment> sentimentsList = sentimentsService.estimateSentiments(paragraph);
            System.out.println("Tamaño de la lista: " + sentimentsList.size());
            
            
            estimatedSentimentsGrid.setItems(sentimentsList);
            estimatedSentimentsGrid.setVisible(true);           

            clearBtn.setVisible(true);
        }
    }

    void clearTexts() {
        sentimentTxt.setValue("");
        estimatedSentimentsGrid.setVisible(false);
        
        clearBtn.setVisible(false);
    }

}
