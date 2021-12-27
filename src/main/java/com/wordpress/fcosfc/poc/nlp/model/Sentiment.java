package com.wordpress.fcosfc.poc.nlp.model;

public class Sentiment {

    private String sentimentName;
    private int predictedClass;
    private String sentence;

    public Sentiment(String sentimentName, int predictedClass, String sentence) {
        this.sentimentName = sentimentName;
        this.predictedClass = predictedClass;
        this.sentence = sentence;
    }

    public String getSentimentName() {
        return sentimentName;
    }

    public void setSentimentName(String sentimentName) {
        this.sentimentName = sentimentName;
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
