package com.learning.example.spring.controller;

public class test1 {


    public static  void main(String [] args ) {


        int n = 10;

        int[] ar1 = new int[n];
        int i;
        for (i = 0; i < n; i++) {

            ar1[i] = i;

        }

        for (int j =0; j<ar1.length;j++){

            if (ar1[j-1]-j>0){
                System.out.println("ar["+j+"] value is "+ar1[j]);

            }else{
                ar1[j] = ar1[j-1]+j;
                System.out.println("ar["+j+"] value is "+ar1[j]);

            }
        }
    }
}
