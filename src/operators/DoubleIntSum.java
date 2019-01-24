package operators;

import java.util.Scanner;

public class DoubleIntSum {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double mealCost = 12.00; //scan.nextDouble(); // original meal price
        int tipPercent = 20; //scan.nextInt(); // tip percentage
        int taxPercent = 8; //scan.nextInt(); // tax percentage
        scan.close();
      
        // Write your calculation code here.
        double tip = (mealCost*tipPercent)/100;
        double tax = (mealCost*taxPercent)/100;
        double totalSum = mealCost+tip+tax;
        float f = (float)totalSum;
        System.out.println("fdghfg "+tipPercent);	
            System.out.println("dfgfdg "+taxPercent);
        System.out.println(tip);
            System.out.println(tax);
                System.out.println(totalSum);
                    System.out.println(f);
        // cast the result of the rounding operation to an int and save it as totalCost 
        int totalCost = (int) Math.round(f);
        System.out.println("The total meal cost is "+totalCost+" dollars.");
        // Print your result
    }
}
