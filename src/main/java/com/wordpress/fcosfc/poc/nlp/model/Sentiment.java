package com.wordpress.fcosfc.poc.nlp.model;

public class Sentiment {

    private String name;
    private int predictedClass;
    private String sentence;

    public Sentiment(String name, int predictedClass, String sentence) {
        this.name = name;
        this.predictedClass = predictedClass;
        this.sentence = sentence;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPredictedClass() {
        return predictedClass;
    }

    public void setPredictedClass(int predictedClass) {
        this.predictedClass = predictedClass;
    }    

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }        
    
}
