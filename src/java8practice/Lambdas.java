/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.*;

import static java8practice.Java8Practice.processRunnable;

/**
 *
 * @author pabernathy
 */
public class Lambdas {
    
    public static void main(String[] args) {
        //doPredicates(); 
        sortApples();
        //someMethodReferences();
    }
    
    public static void doPredicates() {
        IntPredicate evens = (int i) -> i % 2 == 0;
        Predicate<Integer> odds = (Integer i) -> i % 2 == 1;
        assert(evens.test(2));
        assert(odds.test(1));
        System.out.println(evens.test(2));
        assert(evens.test(2) == false) : "was false";
        assert(odds.test(2)) : "was false";
        System.out.println(odds.test(2));
        
    }
    
    public static void doRunnables() {
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("Runnable 1");
            }
        };
        Runnable r2 = () -> System.out.println("Runnable 2");
        
        processRunnable(r1);
        processRunnable(r2);
        processRunnable(() -> System.out.println("Runnable 3"));
        processRunnable(() -> {});      //This compiles.  Runnable::run() takes nothing and returns void.  The {} in () -> {} are necessary.
        processRunnable(() -> {
            System.out.println("Where was runnable 4?");
            System.out.println("Runnable 5");
        });
        
        IntPredicate evens = (int i) -> i % 2 == 0;
        Predicate<Integer> odds = (Integer i) -> i % 2 == 1;
        
    }
    
    public static void sortApples() {
        List<Apple> apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort((Apple a, Apple b) -> a.getWeight().compareTo(b.getWeight()));
        System.out.println("apples.sort((Apple a, Apple b) -> a.getWeight().compareTo(b.getWeight()));");
        System.out.println(apples);
        
        System.out.println(apples);
        apples.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        System.out.println("apples.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));");
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort(Comparator.comparing((Apple a) -> a.getWeight()));
        System.out.println("apples.sort(Comparator.comparing((Apple a) -> a.getWeight()));");
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("apples.sort(Comparator.comparing(Apple::getWeight));");
        System.out.println(apples);
        
        //now color
        System.out.println();
        apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort((Apple a, Apple b) -> a.getColor().compareTo(b.getColor()));
        System.out.println("apples.sort((Apple a, Apple b) -> a.getColor().compareTo(b.getColor()));");
        System.out.println(apples);
        
        //can also leave out the types
        apples = Java8Practice.getSampleList();
        apples.sort((a, b) -> a.getColor().compareTo(b.getColor()));
        System.out.println("apples.sort((a, b) -> a.getColor().compareTo(b.getColor()));");
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort(Comparator.comparing((Apple a) -> a.getColor()));
        System.out.println("apples.sort(Comparator.comparing((Apple a) -> a.getColor()));");
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println("apples.sort(Comparator.comparing(Apple::getColor));");
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println("\n" + apples);
        //apples.sort(Comparator.comparing((Apple a, Apple b) -> a.getColor().compareTo(b.getColor())));    does not compile
        //Comparator.comparing apparently needs a method reference, not a lambda
        
        //System.out.println((apples.get(0)) -> getColor());  does not compile  
        
        apples.sort(Comparator.comparing(Apple::getColor).thenComparing(Apple::getWeight));
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        System.out.println("\n" + apples);
        apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        //System.out.println("\n" + apples);
        apples.sort(Comparator.comparing(Apple::getColor).thenComparing(Apple::getWeight).reversed());
        System.out.println(apples);
        
        apples = Java8Practice.getSampleList();
        //System.out.println("\n" + apples);
        apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight).reversed());
        System.out.println(apples);
    }
    
    public static void someMethodReferences() {
        List<String> strings = java.util.Arrays.asList("a", "b", "A", "B", "c", "C");
        System.out.println(strings);
        strings.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println(strings);
        
        strings = java.util.Arrays.asList("a", "b", "A", "B", "c", "C");
        System.out.println(strings);
        strings.sort(String::compareTo);
        System.out.println(strings);
        
        strings = java.util.Arrays.asList("a", "b", "A", "B", "c", "C");
        System.out.println(strings);
        strings.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(strings);
        
        strings = java.util.Arrays.asList("a", "b", "A", "B", "c", "C");
        System.out.println(strings);
        strings.sort(String::compareToIgnoreCase);
        System.out.println(strings);
    }
    
    public static List<Apple> getListOfApples(List<Integer> weights, Function<Integer, Apple> f) {
        List<Apple> apples = new ArrayList<>();
        for(Integer weight : weights) {
            apples.add(f.apply(weight));
        }
        return apples;
    }
    
}
