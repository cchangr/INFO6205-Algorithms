/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author chen
 */
public class WQUPC {
    private final int[] parent;   // parent[i] = parent of i
    private final int[] size;   // size[i] = size of subtree rooted at i
    private int count;  // number of components
    private static final Random random = new Random();
    int times = 0;
    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WQUPC(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void show() {
        for (int i=0; i<parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], size[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {//p=3
        validate(p);
        int root = p;//root = 3
        while (root != parent[root]) {//
            root = parent[root];//root = 9
        }
        while (p != root) {
            int newp = parent[p];//newp = 9
            parent[p] = root;//parent[3]=9
            p = newp;//p=9
        }
        return root;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        times++;
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
    
    
    public static void main(String args[]){
        
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        //int[] number = {46,178,256,367,478,688,950,1200,1400, 2500, 3800, 5600,
        //6900, 7800, 12000, 16000};
        
        int rn = 0;
       // for(int j=0;j<number.length;j++){
            double end = 0;
        for(int i=0;i<2000;i++){
            rn = count(number);
            end = end + rn;
        }
        double k = 0.5 * number * Math.log(number);
        System.out.println("input number is:" + number + " the sites is: " + end/2000.0);
        System.out.println(k);
    }
    //}
    /**
     *
     * @param n
     * @return 
     */
    public static int count(int n){
        
        WQUPC abc = new WQUPC(n);
        while(abc.count != 1){
            int x,y;
            x = random.nextInt(n);
            y = random.nextInt(n);
            if(abc.connected(x,y) == false){
                abc.union(x, y);
            };
        }
    return abc.times;
    }  
}


