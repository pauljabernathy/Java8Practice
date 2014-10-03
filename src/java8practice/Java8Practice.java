/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java8practice;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 *
 * @author paul
 */
public class Java8Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Java8Practice j = new Java8Practice();
        j.doAppleCompare();
    }
    
    public void doAppleCompare() {
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };
        
        Comparator<Apple> byWeight2 = (Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight());
        
        ArrayList<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple("green", 5));
        apples.add(new Apple("yellow", 4));
        apples.add(new Apple("red", 10));
        showApples(apples);
        apples.sort(byWeight);      //use of the new List.sort(Comparator) function; unfortunately, it returns void so you can't do showApples(apples.sort(...))
        showApples(apples);
        apples.sort(byWeight2);
        showApples(apples);
        apples.sort((Apple a1, Apple a2) -> a1.getColor().compareTo(a2.getColor()));
        showApples(apples);
        
        //System.out.println(showPredicate((Apple a) -> a.getColor().equals("green")), new Apple("green", 1));
        System.out.println(ApplePredicate((Apple a) -> a.getColor().equals("green"), new Apple("green", 2)));
        System.out.println(ApplePredicate((Apple a) -> a.getColor().equals("green"), new Apple("greeng", 2)));
        
        List<Apple> result = filterApples(apples, (Apple a) -> a.getColor().equals("red"));
        showApples(result);
        showApples(filterApples(apples, (Apple a) -> a.getWeight() > 4));
        
        Apple red1 = new Apple("red", 1);
        Apple red2 = new Apple("red", 2);
        Apple green1 = new Apple("green", 1);
        
        apples.sort(java.util.Comparator.comparing(Apple::getWeight));
        showApples(apples);
        
    }
    
    private void showApples(List<Apple> apples) {
        System.out.println();
        for(Apple apple : apples) {
            System.out.println(apple);
        }
    }
    
    private <T> boolean showPredicate(Predicate<T> p, T t) {
        return p.test(t);
    }
    
    private boolean ApplePredicate(Predicate<Apple> p, Apple a) {
        return a.getColor().equals("green");
    }
    
    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : apples) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
