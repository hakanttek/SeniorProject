/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seniorproject.gui;

import com.seniorproject.clustring.K_MeansClustring;
import com.seniorproject.data.importing.ExcelColumn;

/**
 *
 * @author hakantek
 */
public class Console {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double[] weights = new ExcelColumn(System.getProperty("user.dir"), "data", "weight - height", "weight (kg)").standardize();
        double[] heights = new ExcelColumn(System.getProperty("user.dir"), "data", "weight - height", "height (m)").standardize();
        
        double[][] data = new double[weights.length][2];
        for(int i=0; i<data.length; i++){
            data[i][0] = weights[i];
            data[i][1] = heights[i];
        }

        
        int k_value = 4;
        
        K_MeansClustring kmc = new K_MeansClustring(k_value, data);
        kmc.run();
        
        System.out.println("Missing value: " + kmc.getTotalMissingValue() + "\n");
        System.out.println("Weight\t\t\tHeight\t\t\tCluster");
        for(int i=0;i<kmc.getClustersOfData().length; i++){
            System.out.println(data[i][0] + "\t" + data[i][1] + "\t" + kmc.getClustersOfData()[i]);
        }
                

        
    }
    
}
