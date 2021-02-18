/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onemonthapart;

/**
 *
 * @author Dana Altier
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.Math;

public class OneMonthApart{

    public static int monthApart(int monthOne, int dayOne, int monthTwo, int dayTwo) {
        int monthDays = -1; 
        int monthMin = Math.min(monthOne, monthTwo); 
        // Gets the lesser month

        if (monthOne < 1 || monthOne > 12 || monthTwo < 1 || monthTwo > 12) { 
            // If not a possible month
            throw new IllegalArgumentException("Out of range");
        }
        
        if (dayOne < 1 || dayOne > 31 || dayTwo < 1 || dayTwo > 31) { 
            // If not a possible day
            throw new IllegalArgumentException("Out of range");
        }

        switch(monthMin) { 
            // Gets the corresponding last day of month
            case 1:
                monthDays = 31;
                break;
            case 2:
                monthDays = 28;
                break;
            case 3:
                monthDays = 31;
                break;
            case 4:
                monthDays = 30;
                break;
            case 5:
                monthDays = 31;
                break;
            case 6:
                monthDays = 30;
                break;
            case 7:
                monthDays = 31;
                break;
            case 8: 
                monthDays = 31;
                break;
            case 9:
                monthDays = 30;
                break;
            case 10:
                monthDays = 31;
                break;
            case 11:
                monthDays = 30;
                break;
            case 12:
                monthDays = 31;
                break;
        }

        int dayFirst = -1; // Plan: Last day of month-day of lower month + days on next month
        int daySecond = -1;
        if (monthOne < monthTwo) {
            dayFirst = dayOne;
            daySecond = dayTwo;
        } else if (monthOne > monthTwo) {
            dayFirst = dayTwo;
            daySecond = dayOne;
        }

        if (Math.abs(monthOne - monthTwo) > 1) { // More than a month apart, return 1
            return 1;
        } else if (monthOne == monthTwo) { // Same month, less than one month apart, return -1
            return -1;
        } else if (Math.abs(monthOne - monthTwo) == 1) { // Adjacent months

            if (((monthDays - dayFirst) + daySecond) < monthDays) { 
                // Gets difference in days
                return -1; //Less than one month apart
            } else {
                if (((monthDays - dayFirst) + daySecond) == monthDays) {
                    return 0; //Exactly one month apart
                }
                else {
                    return 1; //More than one apart
                }
            }
        }
        return 0;
    }
    
    public static int monthApartLeap(int monthOne, int dayOne, int monthTwo, int dayTwo) {
        int monthDays = -1; 
        int monthMin = Math.min(monthOne, monthTwo); 
        // Gets the lesser month

        if (monthOne < 1 || monthOne > 12 || monthTwo < 1 || monthTwo > 12) { 
            // If not a possible month
            throw new IllegalArgumentException("Out of range");
        }
        
        if (dayOne < 1 || dayOne > 31 || dayTwo < 1 || dayTwo > 31) { 
            // If not a possible day
            throw new IllegalArgumentException("Out of range");
        }

        switch(monthMin) { 
            // Gets the corresponding last day of month
            case 1:
                monthDays = 31;
                break;
            case 2:
                monthDays = 29;
                break;
            case 3:
                monthDays = 31;
                break;
        }

        int dayFirst = -1; // Plan: Last day of month-day of lower month + days on next month
        int daySecond = -1;
        if (monthOne < monthTwo) {
            dayFirst = dayOne;
            daySecond = dayTwo;
        } else if (monthOne > monthTwo) {
            dayFirst = dayTwo;
            daySecond = dayOne;
        }

        if (Math.abs(monthOne - monthTwo) > 1) { // More than a month apart, return 1
            return 1;
        } else if (monthOne == monthTwo) { // Same month, less than one month apart, return -1
            return -1;
        } else if (Math.abs(monthOne - monthTwo) == 1) { // Adjacent months

            if (((monthDays - dayFirst) + daySecond) < monthDays) { 
                // Gets difference in days
                return -1; //Less than one month apart
            } else {
                if (((monthDays - dayFirst) + daySecond) == monthDays) {
                    return 0; //Exactly one month apart
                }
                else {
                    return 1; //More than one apart
                }
            }
        }
        return 0;
    }
    
    public static int comparison(Calendar c1, Calendar c2) {
        
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        
        if (year1 < 1 || year2 < 1) { 
            // If not a possible year
            throw new IllegalArgumentException("Out of range");
        }
        
        if (Math.abs(year1 - year2) > 1) {
            //Years are too far apart
            return -1;
        } else if (year1 - year2 == 1) {
            if (month1 == 1 && month2 == 12) {
                //Adjacent years, December of smaller year, January of next year
                if ((day1 + (31 - day2)) == 31) {
                    //One month apart
                    return 0;
                }
                else if ((day1 + (31 - day2)) < 31) {
                    //Less than one month apart
                    return -1;
                }
                else {
                    //More than one month apart
                    return 1;
                }
            }
        }
        else {
            //Same year
            if (year1 % 4 == 0) {
                //Testing for leap year, special case if one of the months is February
                if (month1 == 2 || month2 == 2) {
                    return monthApartLeap(month1, day1, month2, day2);}
                else {
                    return monthApart(month1, day1, month2, day2);
                }
            } else {
                return monthApart(month1, day1, month2, day2);
            }
        }
        return 0;
    }
    
    public static void main(String []args){
         
        Calendar cal1 = new GregorianCalendar();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        cal1.set(2012, 2, 1); //Month starts as 0 for Calendar, so 3 is actually April
        Calendar cal2 = new GregorianCalendar();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        cal2.set(2012, 3, 1);
        System.out.println(comparison(cal1, cal2));
    }
}
