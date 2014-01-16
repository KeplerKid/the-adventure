package com.bigeauofn.adventure.map.geometry;

import java.util.Random;

public class GeomtryUtility {

    public static void pointTest(long seed) {
        java.awt.Point awtPoint = new java.awt.Point();
        IntegerPoint integerPoint = new IntegerPoint();
        IntPoint geoPoint = new IntPoint();

        int x, y, sum;

        // Random : use same seed for each test.
        Random random = new Random(seed);
        sum = 0;

        /*----------------------AWT POINT : 100,000 sigma (getX + getY)-----------------*/
        long startTime = System.nanoTime(), endTime;

        for (int i = 0; i < 100000; i++) {
            awtPoint.setLocation(random.nextInt() % 16, random.nextInt() % 16);
            x = (int) awtPoint.getX();
            y = (int) awtPoint.getY();
            sum = sum + x + y;
        }

        endTime = System.nanoTime();
        /*------------------------------AWT POINT END----------------------------------*/

        // Print results and reset
        System.out.println("AWT POINT time (nanoseconds) : " + (endTime - startTime));
        System.out.println("\tSum : " + sum);
        System.out.println();


        sum = 0;
        random = new Random(seed);
        //------------------------

        /*-------------------IntegerPoint : 100,000 sigma(getX + getY)---------------------*/
        startTime = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            integerPoint.setLocation(random.nextInt() % 16, random.nextInt() % 16);
            x = integerPoint.getX();
            y = integerPoint.getY();
            sum = sum + x + y;
        }

        endTime = System.nanoTime();
        /*-------------------IntegerPoint End----------------------------------------------*/

        // Print results and reset
        System.out.println("INT POINT time (nanoseconds) : " + (endTime - startTime));
        System.out.println("\tSum : " + sum);
        System.out.println();

        sum = 0;
        random = new Random(seed);
        //-----------------------

        /*-------------------Geometry IntPoint : 100,000 sigma(getX + getY)---------------*/
        startTime = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            geoPoint.setLocation(random.nextInt() % 16, random.nextInt() % 16);
            x = geoPoint.getX();
            y = geoPoint.getY();
            sum = sum + x + y;
        }

        endTime = System.nanoTime();
        /*-------------------Geometry IntPoint End----------------------------------------*/

        // Print results
        System.out.println("GEO POINT time (nanoseconds) : " + (endTime - startTime));
        System.out.println("\tSum : " + sum);
        System.out.println();
        //--------------
    }

}
