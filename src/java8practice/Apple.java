/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java8practice;

import java.util.function.Predicate;

/**
 *
 * @author paul
 */
public class Apple { //implements Comparable {
    private String color;
    private int weight;
    
    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String toString() {
        return this.color + " " + this.weight;
    }
    
    /*Predicate<Apple> getPredicate() {
        //return () -> false; //does not compile
        return new Predicate<Apple>() {
            public boolean test(Apple a) {
                return false;
            }
        };
    }*/
}
