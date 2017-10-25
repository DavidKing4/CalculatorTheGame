import java.util.Scanner;
import java.lang.Math;

public class CalculatorTheGame{

	public static int[] ans;

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.print("Starting number:");
		Double n = s.nextDouble();
		System.out.print("Goal:");
		int g = s.nextInt();
		System.out.print("Number of permitted moves:");
		int m = s.nextInt();
		System.out.print("Number of opperations:");
		int x = s.nextInt();

		int[] tans = new int[m];
		ans = tans;

		System.out.println("Enter on their own line each opperation and the associated value(s) seperated by a space:");
		String[] ops = new String[x];
		int[][] opsVals = new int[x][2];
		for(int i = 0; i < x; i++){
			for(int j = 0; j < 2; j++){
				opsVals[i][j] = 11235813;//random number that will never be a value of an opperation where as 0 might be.
			}
		}

		String t;
		String[] ta;
		s.nextLine();
		for(int i = 0; i < x; i++){
			t = s.nextLine();
			ta = t.split(" ");
			ops[i] = ta[0];
			for(int j = 1; j < ta.length; j++){
				opsVals[i][j - 1] = Integer.parseInt(ta[j]);
			}
		}
		System.out.println("==============================");
		if(mainFunc(0, n, g, m, x, ops, opsVals)){
			for(int i = 0; i < m; i++){
				System.out.print(ops[ans[i]]);
				if(opsVals[ans[i]][0] != 11235813)
					System.out.print(" " + opsVals[ans[i]][0]);
				if(opsVals[ans[i]][1] != 11235813)
					System.out.print(" " + opsVals[ans[i]][1]);
				System.out.println();
			}
		}else{
			System.out.println("impossible");
		}
	}

	public static boolean mainFunc(int d, double n, int g, int m, int x, String[] ops, int[][] opsVals){
		if(n == g)
			return true;
		if(m == 0)
			return false;
		for(int i = 0; i < x; i++){
			ans[d] = i;
			if(mainFunc(d + 1, calc(n, ops[i], opsVals[i][0], opsVals[i][1]), g, m - 1, x, ops, opsVals))
				return true;
		}
		return false;
	}

	public static double calc(double n, String op, int opsVal0, int opsVal1){

		if(op.compareTo("+") == 0){
			return(n + opsVal0);

		}else if(op.compareTo("-") == 0){
			return(n - opsVal0);

		}else if(op.compareTo("^") == 0){
			return(Math.pow(n, opsVal0));

		}else if(op.compareTo("/") == 0){
			return(n / opsVal0);

		}else if(op.compareTo("x") == 0){
			return(n * opsVal0);

		}else if(op.compareTo("+/-") == 0){
			return(n * -1);

		}else if(op.compareTo("<<") == 0){
			if(Math.abs(n) < 10)
				return 0;
			return(Integer.parseInt(Integer.toString((int)n).substring(0, Integer.toString((int)n).length() - 1)));

		}else if(op.compareTo("add") == 0){
			return(Integer.parseInt(Integer.toString((int)n) + Integer.toString(opsVal0)));

		}else if(op.compareTo("reverse") == 0){
			if(n >= 0){
				StringBuilder sb = new StringBuilder(Integer.toString((int)n));
				return(Integer.parseInt((sb.reverse()).toString()));
			}else if(n < 0){
				StringBuilder sb = new StringBuilder(Integer.toString((int)n).substring(1, Integer.toString((int)n).length()));
				return(Integer.parseInt("-" + (sb.reverse()).toString()));
			}

		}else if(op.compareTo("=>") == 0){
			String tn = Integer.toString((int)n);
			String tops1 = Integer.toString(opsVal0);
			String tops2 = Integer.toString(opsVal1);
			StringBuilder sb = new StringBuilder(tn);
			for(int i = 0; i < tn.length() - tops1.length() + 1; i++){
				if(tn.substring(i, i + tops1.length()).compareTo(tops1) == 0){
					sb.replace(i, i + tops1.length(), tops2);
					i = i + tops1.length() - 1;	
				}
			}
			return(Integer.parseInt(sb.toString()));
		}
		return 0;
	}
}