/*
 * Copyright (c) 2018. Phasmid Software
 */
package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.sort.simple.SelectionSort;
import edu.neu.coe.info6205.sort.simple.Sort;
import java.util.function.Function;

public class Benchmark<T> {

    private final static int a = 6400;
    static Integer[] ordered = new Integer[a];
    static Integer[] rordered = new Integer[a];
    static Integer[] random = new Integer[a];
    static Integer[] prandom = new Integer[a];
    static Integer[] temp = new Integer[a];
 
    public Benchmark(Function<T, Void> f) {
        this.f = f;
    }

    public long run(T t, int n) {

        long num = 0;
        for (int i = 0; i < n; i++) {
            long sTime = System.nanoTime();
            this.f.apply(t);
            long fTime = System.nanoTime();
            num += (fTime - sTime);

        }
        return num / n; // TODO  
    }

    private final Function<T, Void> f;

    public static void main(String[] args) {
        int m = 100; // This is the number of repetitions: sufficient to give a good mean value of timing
        // TODO You need to apply doubling to n
        for (int i = 200; i <= a; i = i * 2) {
            for (int j = 0; j < i; j++) {

                ordered[j] = j;
                rordered[j] = i - j - 1;
                int k = (int) (Math.random() * a);
                random[j] = k;

            } // TODO populate the array with real random data

            System.arraycopy(random, 0, prandom, 0, a/2);
            System.arraycopy(ordered, a/2, prandom, a/2, a/2);

            benchmarkSort(ordered, i, "InsertionSort, ordered", new InsertionSort<>(), m);
            benchmarkSort(ordered, i, "SelectionSort, ordered", new SelectionSort<>(), m);

            benchmarkSort(rordered, i, "InsertionSort, reverse", new InsertionSort<>(), m);
            benchmarkSort(rordered, i, "SelectionSort, reverse", new SelectionSort<>(), m);

            benchmarkSort(random, i, "InsertionSort, random", new InsertionSort<>(), m);
            benchmarkSort(random, i, "SelectionSort, random", new SelectionSort<>(), m);

            benchmarkSort(prandom, i, "InsertionSort, part_random", new InsertionSort<>(), m);
            benchmarkSort(prandom, i, "SelectionSort, part_random", new SelectionSort<>(), m);

        }
    }

    private static void benchmarkSort(Integer[] xs, Integer n, String name, Sort<Integer> sorter, int m) {
         
         
        Function<Integer, Void> sortFunction = (Integer x) -> {
            System.arraycopy(xs, 0, temp, 0, xs.length);
            sorter.sort(temp, 0, x);
            return null;
        };
        Benchmark<Integer> bm = new Benchmark<>(sortFunction);
        long x = bm.run(n, m);
        System.out.println(name + ": " + x/1e6 + " millisecs for n=" + n);
    }
}
    
