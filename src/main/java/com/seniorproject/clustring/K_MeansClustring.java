package com.seniorproject.clustring;

import java.util.Random;

public class K_MeansClustring {
    /* # of clusters */
    public final int k_value;
    
    /* total missing value of clusters */
    private double totalMissingValue;
    public double getTotalMissingValue() {
        return totalMissingValue;
    }
    
    /* # of iterations */
    int numberOfIterations;
    /* data: rows -> samples, columns -> data types */
    private double data[][];
    public double[][] getData() {
        return data;
    }
        
    /* centers of clusters: rows -> clusters, colums -> data types */
    private double centersOfClusters[][];
    public double[][] getCentersOfClusters() {
        return centersOfClusters;
    }
    
    /* clusters of data */
    private int clustersOfSamples[];
    public int[] getClustersOfData() {
        return clustersOfSamples;
    }
    
    public K_MeansClustring(int k_value , double[][] data) {
        /* assign # of clusters, data and number of iterations */
        this.k_value = k_value;
        this.data = data;
        this.numberOfIterations = 0;
        
        /* create center of cluster array */
        this.centersOfClusters = new double[k_value][data[0].length];
        
        /* create cluster of centers array */
        this.clustersOfSamples = new int[data.length];
        
        /* create random number generator */
        Random random = new Random();
        
        /* assign centers of cluster randomly */
        for(int k=0; k<this.centersOfClusters.length; k++){
            for(int j=0; j<this.centersOfClusters[k].length; j++){
                this.centersOfClusters[k][j] = random.nextDouble()*100;
            }
        }
        
        /* assign all sample the nearest cluster */
        for(int i=0; i<data.length; i++){
            this.clustersOfSamples[i] = this.nearestCluster(i);
        }
    }
    
    /* run k means clustiring algorithm */
    public void run(){
        while(!stop()){
            /* assign centers of clusters average of samples of cluster */
            for(int k=0; k<this.k_value; k++){
                for(int j=0; j<this.centersOfClusters[k].length; j++){
                    this.centersOfClusters[k][j] = this.averageOfColumnofElementsOfCluster(k, j);
                }
            }
            
            /* assign all sample the nearest cluster */
            for(int i=0; i<data.length; i++){
                this.clustersOfSamples[i] = this.nearestCluster(i);
            }
            this.numberOfIterations++;
        }
        this.totalMissingValue = this.totalMissingValue();
    }
    
    /* if all centers of clusters is equal to average of eleent of clusters, stop; else continue */
    private boolean stop(){
        boolean stop = true;
        for(int k=0; k<k_value; k++){
            for(int j=0; j<this.centersOfClusters[k].length; j++){
                if(this.averageOfColumnofElementsOfCluster(k, j) != this.centersOfClusters[k][j]){
                    stop = false;
                    break;
                }
            }
        }
        return stop;
    }
    
    /* find average of column of element of cluster */
    private double averageOfColumnofElementsOfCluster(int cluster, int column){
        double average = 0;
        int numberOfSampleInTheCluster = 1;

        for(int i=0; i<this.clustersOfSamples.length; i++){
            if(this.clustersOfSamples[i] == cluster){
                numberOfSampleInTheCluster++;
                average += this.data[i][column];
            }
        } 
        average/=(double)numberOfSampleInTheCluster;
               
        return average;
    }
    
    /* find nearest cluster of sample */
    private int nearestCluster(int row){
        int nearestCluster = 0;
        double distance = this.distances(row, nearestCluster);
        for(int k=1; k<this.k_value; k++){
            double k_distance = this.distances(row, k);
            if(k_distance < distance){
                distance = k_distance;
                nearestCluster = k;
            }
        }
        return nearestCluster;
    }
    
    /* find distance between data and center of cluster */
    private double distances(int dataRow, int cluster){
        double distance = 0;
        for(int j=0; j<this.data[dataRow].length; j++){
            distance += Math.pow(this.data[dataRow][j] - this.centersOfClusters[cluster][j], 2);
        }

        return distance;
    }
    
    /* tcalculate total missing value */ 
    private double totalMissingValue(){
        double tMissingValue = 0;
        for(int i=0; i<this.data.length; i++){
            double missingValue = 0;
            for(int j=0; j<this.data[i].length; j++){
                missingValue+=Math.pow(this.data[i][j]-this.centersOfClusters[this.clustersOfSamples[i]][j],2);
            }
            tMissingValue += Math.sqrt(missingValue);
        }
        
        return tMissingValue;
    }
}
 