package com.wordpress.fcosfc.poc.nlp.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sentiment implements Serializable {

    private static final long serialVersionUID = 8799656128674716638L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sentimentId;
    
    private String sentimentName;
    private int predictedClass;
    private String sentence;
    
    @ManyToOne
    @JoinColumn(name = "estimationId", referencedColumnName = "estimationId")
    private Estimation estimation;

    public Sentiment() {
    }

    public Sentiment(String sentimentName, int predictedClass, String sentence, Estimation estimation) {
        this.sentimentName = sentimentName;
        this.predictedClass = predictedClass;
        this.sentence = sentence;
        this.estimation = estimation;
    }          

    public long getSentimentId() {
        return sentimentId;
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

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }        

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.sentimentId ^ (this.sentimentId >>> 32));
        hash = 71 * hash + Objects.hashCode(this.sentimentName);
        hash = 71 * hash + this.predictedClass;
        hash = 71 * hash + Objects.hashCode(this.sentence);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sentiment other = (Sentiment) obj;
        if (this.sentimentId != other.sentimentId) {
            return false;
        }
        if (this.predictedClass != other.predictedClass) {
            return false;
        }
        if (!Objects.equals(this.sentimentName, other.sentimentName)) {
            return false;
        }
        if (!Objects.equals(this.sentence, other.sentence)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Sentiment{" + "sentimentId=" + sentimentId + ", sentimentName=" + sentimentName + ", predictedClass=" + predictedClass + ", sentence=" + sentence + '}';
    }        
        
}
