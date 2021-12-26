package com.wordpress.fcosfc.poc.nlp.views.main;

import com.wordpress.fcosfc.poc.nlp.views.MainLayout;
import com.vaadin.flow.component.button.Button;
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

    private final TextArea sentimentTxt, estimatedSentimentsTxt;
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

        estimatedSentimentsTxt = new TextArea("These are the estimated sentiments");
        estimatedSentimentsTxt.setWidthFull();
        estimatedSentimentsTxt.setReadOnly(true);
        estimatedSentimentsTxt.setVisible(false);

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

        add(sentimentTxt, analyzeBtn, estimatedSentimentsTxt, clearBtn);
    }

    void estimateSentiments(String paragraph) {
        if (paragraph.isEmpty()) {
            clearTexts();
        } else {
            List<Sentiment> estimatedSentiments = sentimentsService.estimateSentiments(paragraph);

            StringBuilder builder = new StringBuilder();
            for (Sentiment sentiment : estimatedSentiments) {
                builder.append("Name: ");
                builder.append(sentiment.getName());
                builder.append(", class: ");
                builder.append(sentiment.getPredictedClass());
                builder.append(", sentence: ");
                builder.append(sentiment.getSentence());
                builder.append("\n");
            }

            estimatedSentimentsTxt.setValue(builder.toString());
            estimatedSentimentsTxt.setVisible(true);

            clearBtn.setVisible(true);
        }
    }

    void clearTexts() {
        sentimentTxt.setValue("");
        estimatedSentimentsTxt.setVisible(false);
        clearBtn.setVisible(false);
    }

}
