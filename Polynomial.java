package com.company;
import java.util.*;

/** The Polynomial Java application performs
*various task using polynomial expressions
*@author
*   Jacob Smith
**/

public class Polynomial {

    // elements in the array are stored  at the starting position coeff[0]
    private double [] coeff;
    // the value stored is the highest term with a non-zero coefficient in the array
   int highest_Term = 0;

    /** Initialize instance variables when object is created
     *
     */
    public Polynomial(){

        coeff = new double [5];
    }

    /** Initialize instance variables when object is created
     *
     * @param a0
     * Initializes a polynomial that only contains the constant a0
     */
    public Polynomial(double a0){

        coeff = new double [5];
        coeff[0] = a0;

    }

    /** Initialize instance variables to the instance variables of the object that the reference variable refers to
     *
     * @param p
     * P refers to the same object that is passed to the method
     */
    public Polynomial(Polynomial p){

        coeff = Arrays.copyOf(p.coeff, p.coeff.length);
        highest_Term = p.highest_Term;
    }

    /**  The amount is added to the coefficient at the specified exponent
     *
      * @param amount
     * a double value that is to be added to the coefficient
     * @param exponent
     * the location in the array that is the desired target
     */
    public void add_to_coef(double amount, int exponent){

           if (exponent < coeff.length){
               coeff[exponent] += amount;
               if (exponent > highest_Term)
                   highest_Term = exponent;
           }

           else {
               coeff = Arrays.copyOf(this.coeff, this.coeff.length + exponent);
               coeff[exponent] += amount;
              highest_Term = exponent;
           }

    }

    /** Assigns the value of coefficient to the specified exponent
     *
      * @param coefficient
     * double value that is to be assigned to the coefficient
     * @param exponent
     * the location in the array that is the desired target
     */
    public void assign_coef(double coefficient, int exponent){
        if (exponent < coeff.length){
            coeff[exponent] = coefficient;
            if (exponent > highest_Term)
                highest_Term = exponent;
        }

        else {
            coeff = Arrays.copyOf(this.coeff, this.coeff.length + exponent);
            coeff[exponent] = coefficient;
            highest_Term = exponent;
        }
    }

    /** Searches the array for the coefficient of the specified exponent
     *
     * @param exponent
     * the location in the array that is the desired target
     * @return
     * returns the coefficient for the specified exponent
     * return zero if term does'nt exist
     *
     */
    public double coefficient(int exponent){
        if (exponent < coeff.length){
            return coeff[exponent];
        }
        else
            return 0;
    }


    /** Evaluates the polynomial with the given value for x
     *
     * @param x
     * a double value for which the polynomial is evaluated
     * @return
     * returns the sum of terms evaluated at the given value for x
     */
    public double eval(double x){
        double sum = 0;

        for (int i = highest_Term; i > 0; i--){
            sum = (coeff[i] + sum) * x;
        }
        return sum + coeff[0];
    }

    /** Converts the given polynomial to a string
     *
     * @return
     * return a variable of type string that contains the polynomial
     */
    public String toString(){
        String poly = "" + coeff[0];

        if (highest_Term == 0){
            return poly;
        }
        else if (coeff[highest_Term] == 1){
             poly = "x^" + highest_Term;
        }

        else {
             poly = coeff[highest_Term] + "x^" + highest_Term;
        }


        for (int i = highest_Term - 1; i > 0; i--){
            if(coeff[i] != 0){
                if (coeff[i] == 1){
                    poly = poly + " + x^" + i;
                }
                else
                poly = poly + " + " + coeff[i] + "x^" + i;
            }
        }

        return poly + " + " + coeff[0];
    }

    /** Adds one polynomial to another
     *
     * @param p
     * reference variable to an object of type Polynomial
     * @return
     * returns a reference variable to an object that contains the sum of the two polynomials
     */
    public Polynomial add(Polynomial p) {
        Polynomial a = new Polynomial();

        if (coeff.length > p.coeff.length) {
            a.coeff = Arrays.copyOf(coeff, coeff.length);


            for (int i = p.highest_Term; i >= 0; i--) {
                a.coeff[i] += p.coeff[i];
            }
            if (highest_Term > p.highest_Term) {
                a.highest_Term = highest_Term;
            }
            else {
                a.highest_Term = p.highest_Term;
            }
        }


        else {

            a.coeff = Arrays.copyOf(p.coeff, p.coeff.length);

            for (int i = highest_Term; i >= 0; i--){
                a.coeff[i] += coeff[i];
            }

            if (highest_Term > p.highest_Term) {
                a.highest_Term = highest_Term;
            }
            else {
                a.highest_Term = p.highest_Term;
            }
        }

        return a;
    }

    /** Multiplies two polynomials
     *
     * @param p
     * reference variable to an object of type Polynomial
     * @return
     * returns a reference variable to an object that contains the product of the two polynomials
     */
    public Polynomial multiply(Polynomial p){
        Polynomial a = new Polynomial();

            for (int i = highest_Term; i >= 0; i--){
                if (coeff[i] != 0 ) {
                    for (int j = p.highest_Term; j >= 0; j--) {
                        if (p.coeff[j] != 0)
                        a.add_to_coef((coeff[i] * p.coeff[j]), (i + j));
                    }
                }
            }

        return a;
    }

}

class Main {

    public static void main(String[] args) {
	// write your code here

        Polynomial s = new Polynomial(1);
        s.assign_coef(3,1);
        s.assign_coef(1, 3);
        s.assign_coef(6,6);

        Polynomial t = new Polynomial(1);
        t.assign_coef( 2, 1);
        t.assign_coef(2, 2);

        Polynomial y;
        y = t.add(s);

        Polynomial x = t.multiply(y);

        System.out.println(s.toString());
        System.out.println(y.toString());
        System.out.println(x.toString());



        Polynomial j = new Polynomial(s);

        Polynomial n = new Polynomial();
        n.assign_coef(0.5, 1);
        n.add_to_coef(0.37, 2);
        n.add_to_coef(0.57, 3);
        n.assign_coef(-5.13, 4);
        n.add_to_coef(1.03, 4);


        System.out.println(j.toString());
        System.out.println(n.toString());
        System.out.println(n.eval(2.4));
        System.out.println(j.coefficient(2));
        System.out.println(j.coefficient(6));





    }
}
