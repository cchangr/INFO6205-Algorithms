/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205;

import java.util.Random;

/**
 *
 * @author chen
 */
public class SCHashTable<Key, Value> {
    
    private int M;    //number of chains
    private Node[] st = new Node[M];   //array of chains
    private int[] count;
    
    
    public SCHashTable(int m){
        this.M = m;
        this.count = new int[M];
        st = new Node[M];
        for(int q=0; q<M; q++){
            count[q] = 0;
        }
        
        
    }

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    private int hash(Key key){ 
        return (key.hashCode() & 0x7fffffff) % M;}

    public Value get(Key key){ 
        int i = hash(key); 
        for (Node x = st[i]; x != null; x = x.next) 
            if (key.equals(x.key)) 
            return (Value) x.val; 
        return null; 
    }
    
    public void put(Key key, Value val){ 
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) 
            if (key.equals(x.key)){ 
                x.val = val; 
                return; 
            } 
        st[i] = new Node(key, val, st[i]); 
        count[i] = count[i] + 1;
    }
    
    public boolean eSlots(){
        for(int i : count){
            if(i < 1)
                return true;   
        }
        return false;
    }
    
    public boolean tSlots(){
        for(int i : count){
            if(i > 1)
                return true;   
        }
        return false;
    }
    
    public static void drive(){
        int m[]  = {10,20,40,80,160,320,640,1280,2560};
        int time = 5000;
        
        for(int w=0; w<m.length; w++){
                double firstCollision = 0;
                double bAllFill = 0;
            for(int v=0; v<time; v++){
                SCHashTable<Double, Integer> ht = new SCHashTable<>(m[w]);
                int number1 = 0;
                int number2 = 0;
                while(ht.eSlots()){
                    
                    Random r = new Random();
                    double x = r.nextDouble();
                    
                    ht.put(x,1);
                    if(!ht.tSlots()){
                        number1++;
                    }
                    number2++;
                }
                firstCollision += number1;
                bAllFill += number2;
            }         
            System.out.print(firstCollision/time+",");
            System.out.print(bAllFill/time);
            System.out.println();    
            }
            
        }
    }
