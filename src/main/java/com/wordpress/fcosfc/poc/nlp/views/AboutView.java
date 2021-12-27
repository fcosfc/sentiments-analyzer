package com.wordpress.fcosfc.poc.nlp.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTargetValue;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("Sentiments analyzer"));
        add(new Paragraph("This is a proof of concept for an application that analyze sentiments in a paragraph"));
        
        Anchor anchor = new Anchor("https://stanfordnlp.github.io/CoreNLP/", "Natural Language Processing open source libraries provided by Stanford CoreNLP");
        anchor.setTarget(AnchorTargetValue.forString("_blank"));           
        add(anchor);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
