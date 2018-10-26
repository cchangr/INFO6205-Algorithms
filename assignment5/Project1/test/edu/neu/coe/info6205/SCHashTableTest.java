/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chen
 */
public class SCHashTableTest {
    
    /**
     * Test of eSlots method, of class SCHashTable.
     */
    @Test
    public void testESlots() {
        
        SCHashTable<Double, Integer> ht = new SCHashTable<>(5);
        Random r = new Random();
        double x = r.nextDouble();
        ht.put(x, 1);
        Assert.assertTrue(!ht.tSlots());
           
        SCHashTable<Double, Integer> ht1 = new SCHashTable<>(5);
        for(int i=0;i<6;i++){
            Random r1 = new Random();
            double x1 = r1.nextDouble();
            ht1.put(x1,1);
        }
        Assert.assertTrue(ht.eSlots());
    }

    /**
     * Test of tSlots method, of class SCHashTable.
     */
    @Test
    public void testTSlots() {
        SCHashTable<Double, Integer> ht2 = new SCHashTable<>(5);
        Random r = new Random();
        double x = r.nextDouble();
        ht2.put(x,1);
        Assert.assertTrue(ht2.eSlots());

        SCHashTable<Double, Integer> ht3 = new SCHashTable<>(5);
        for(int i=0;i<4;i++){
            Random r2 = new Random();
            double x2 = r2.nextDouble();
            ht3.put(x2,1);
        }
        Assert.assertTrue(ht3.eSlots());
    }

    
}
