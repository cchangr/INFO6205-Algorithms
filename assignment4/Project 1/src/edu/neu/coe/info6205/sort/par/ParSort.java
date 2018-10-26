package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.*;

class ParSort {

    public static int cutoff;
    public static ForkJoinPool executor;
    private static int n;


    public static void defineThread(int n) {
        ParSort.n = n;
        executor = new ForkJoinPool(n);
    }

    public static void defineThread() {
        executor = new ForkJoinPool();
    }



    public static void sort(int[] array, int from, int to) {

        int size = to - from + 1;
        if (size <= cutoff) Arrays.sort(array, from, to+1);
        else {
            int mid = from + (to - from) / 2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid); // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, mid + 1, to); // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        int a = 0, b = 0;// TODO implement me
                        for (int i = 0; i < xs1.length + xs2.length; i++) {
                            if (a > xs1.length-1) {
                                result[i] = xs2[b++];
                            }
                            else if (b < xs2.length && xs2[b] <= xs1[a]){
                                result[i] = xs2[b++];
                            }
                            else {
                                result[i] = xs1[a++];
                            }
                        }
                        return result;
                    }).whenComplete((result, throwable) -> {
                        System.arraycopy(result, 0, array, 0, array.length);
            });
//            parsort.whenComplete((result, throwable) -> {
//                System.arraycopy(result, 0, array, 0, array.length);
//            }); //
            parsort.join();
        }

    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {

        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from + 1];
                    int l = from;
                    int i = 0;
                    System.arraycopy(array, l, result, i, result.length);
                    sort(result, 0, result.length - 1);// TODO implement me
                    return result;
                }, executor);
    }
}
