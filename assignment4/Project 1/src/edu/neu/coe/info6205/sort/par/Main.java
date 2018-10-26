package edu.neu.coe.info6205.sort.par;

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        //if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
        //Scanner scan=new Scanner(System.in);
        //ParSort.cutoff=scan.nextInt();
        Random random = new Random(0l);



        int[] sortNumber = {100, 200, 400, 800, 1600, 3200, 6400, 12800};
        for (int n = 0; n < sortNumber.length; n++) {
            for (int x = 1; x <= 9; x++) {
            //for (int n = 0; n < sortNumber.length; n++) {
                for (int m = sortNumber[n] / 20; m <= sortNumber[n] / 2; m += sortNumber[n] / 20) {
                    //for (int x = 1; x <= 9; x++) {
                    ParSort.cutoff = m;
                    //ParSort.cutoff = sortNumber[n]/4;

                    ParSort.defineThread(x);
                    int[] array = new int[sortNumber[n]];
                    for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
                    long time1 = System.nanoTime();
                    ParSort.sort(array, 0, array.length - 1);
                    long time2 = System.nanoTime();
                    //for (int i : array) System.out.println(i);
                    //if (array[0]==11) System.out.println("Success!");
                    System.out.print("Array size is: " + sortNumber[n] + "Cutoff is: " + ParSort.cutoff +
                            ", sort time is: " + (time2 - time1)/1000000.0 + " ms"
                            + ", the thread is: " + ParSort.executor.getParallelism());
                    System.out.println();

                }
            }
        }
    }
}
