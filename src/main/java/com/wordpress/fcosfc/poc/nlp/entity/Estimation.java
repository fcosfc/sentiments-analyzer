package com.wordpress.fcosfc.poc.nlp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity that represents an estimation of sentiments in a paragraph
 * 
 * @author Paco Saucedo
 */
@Entity
public class Estimation implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long estimationId;
       
    @Temporal(TemporalType.TIMESTAMP)
    private final Date estimationDate = new Date();
    
    private String paragraph;        
    
    @OneToMany(mappedBy = "sentimentId")  
    private List<Sentiment> sentimentsList;

    public Estimation() {
    }

    public Estimation(String paragraph) {
        this.paragraph = paragraph;
    }

    public long getEstimationId() {
        return estimationId;
    }

    public Date getEstimationDate() {
        return estimationDate;
    }        

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<Sentiment> getSentimentsList() {
        return sentimentsList;
    }

    public void setSentimentsList(List<Sentiment> sentimentsList) {
        this.sentimentsList = sentimentsList;
    }        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.estimationId ^ (this.estimationId >>> 32));
        hash = 37 * hash + Objects.hashCode(this.estimationDate);
        hash = 37 * hash + Objects.hashCode(this.paragraph);
        hash = 37 * hash + Objects.hashCode(this.sentimentsList);
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
        final Estimation other = (Estimation) obj;
        if (this.estimationId != other.estimationId) {
            return false;
        }
        if (!Objects.equals(this.paragraph, other.paragraph)) {
            return false;
        }
        if (!Objects.equals(this.estimationDate, other.estimationDate)) {
            return false;
        }
        if (!Objects.equals(this.sentimentsList, other.sentimentsList)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Estimation{" + "estimationId=" + estimationId + ", estimationDate=" + estimationDate + ", paragraph=" + paragraph + '}';
    }        

}
