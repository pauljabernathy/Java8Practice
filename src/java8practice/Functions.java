/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java8practice;

import java.util.function.DoubleFunction;

/**
 *
 * @author paul
 */
public class Functions {
    
    public static double integrate(DoubleFunction<Double> f, double lower, double upper) {
        if(lower >= upper) {
            return 0;
        }
        double increment = .01;
        double sum = 0;
        double x = lower;
        while(x <= upper) {
            sum += f.apply(x) * increment;
            x += increment;
        }
        return sum;
    }
    
    //yes, this is a very crude approximation and won't always work
    public static double differentiate(DoubleFunction<Double> f, double x) {
        double epsilon = .001;
        double lower = f.apply(x - epsilon);
        double upper = f.apply(x + epsilon);
        return (upper - lower) / (2 * epsilon);
    }
}
