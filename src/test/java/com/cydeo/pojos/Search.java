package com.cydeo.pojos;

import java.util.List;

public class Search {

    private List<Spartan> content; // have the list of elements from the Spartan class

    private int totalElement;

    public List<Spartan> getContent() {
        return content;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public int getTotalElement() {
        return totalElement;
    }


    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }


    @Override
    public String toString() {
        return "Search{" +
                "content=" + content +
                ", totalElement=" + totalElement +
                '}';
    }

}
