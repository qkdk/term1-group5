package com.sy.firstweek.fri;

import java.util.Arrays;
import java.util.Scanner;

public class fri_baek_11399 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n;
		n = sc.nextInt();
		
		int[] arr = new int[n];
		int sum = 0;
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++) {
				sum += arr[j];
			}
		}
		System.out.println(sum);
	}
}
