import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintStream;

public class Polynomial {
	double coefficients[];
	int exponents[];
	
	public Polynomial() {
		coefficients = new double[]{0};
		exponents = new int[]{0};
	}
	
	public Polynomial(double coefficients[], int exponents[]) {
		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];

		for(int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
		}

		for(int j = 0; j < exponents.length; j++) {
			this.exponents[j] = exponents[j];
		}
	}

	public Polynomial(File f) throws Exception{
		Scanner scanner = new Scanner(f);
        String line = scanner.nextLine();
        scanner.close();

		// Splits at + or - //
        String[] terms = line.split("(?=[+-])");

		// for (String term : terms){
		// 	System.out.println(term);
		// }

        coefficients = new double[terms.length];
        exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];
            double co = 0;
            int ex = 0;

            if (term.contains("x")) {
                String[] parts = term.split("x");

				// for (String part : parts){
				// 	System.out.println(part);
				// }

				// Parse coefficient. //
                if (parts.length >= 1)
                    co = parseCo(parts[0]);

				// Parse exponent. //
                if (parts.length >= 2)
                    ex = Integer.parseInt(parts[1]);
            } 
			else 
                co = parseCo(term);

            coefficients[i] = co;
            exponents[i] = ex;
        }
    }

	public double parseCo(String term) {
        if (term.equals(""))
            return 1.0;
		else if (term.equals("-"))
            return -1.0;
		else 
            return Double.parseDouble(term);
    }
	
	public Polynomial add(Polynomial p) {
		int max_len = Math.max(coefficients.length, p.coefficients.length);
	    double[] res = new double[max_len];
		
		for (int i = 0; i < coefficients.length; i++) {
			res[i] += coefficients[i];
		}
		for (int j = 0; j < p.coefficients.length; j++) {
			res[j] += p.coefficients[j];
		}
		
		return new Polynomial(res, new int[]{0});
	}
	
	public double evaluate(double x) {
		double evaluation = 0;
		for (int k = 0; k < coefficients.length; k++) {
			evaluation += coefficients[k] * Math.pow(x, k);
		}
		return evaluation;
	}
	
	public boolean hasRoot(double root) {
		if (evaluate(root) == 0) {
			return true;
		}
		return false;
	}

	public Polynomial multiply(Polynomial p2) {
		int len1 = coefficients.length;
		int len2 = p2.coefficients.length;
		int resLen = len1 * len2; // <- Max possible len of the resulting poly 
	
		double[] resCo = new double[resLen];
		int[] resEx = new int[resLen];
		int termNum = 0;

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				int exponent = exponents[i] + p2.exponents[j];
				double coefficient = coefficients[i] * p2.coefficients[j];
	
				// Find index of cur exp //
				int index = -1;
				for (int k = 0; k < resLen; k++) {
					if (resEx[k] == exponent) {
						index = k;
						break;
					} 
					else if (resEx[k] == 0) {
						break;
					}
				}
	
				// Update coefficient at index //
				if (index != -1) {
					resCo[index] += coefficient;
				} else {
					// Insert the new term in the result
					for (int k = 0; k < resLen; k++) {
						if (resEx[k] == 0) {
							resCo[k] = coefficient;
							resEx[k] = exponent;
							termNum++;
							break;
						}
					}
				}
			}
		}

		// Resize resCo and resEx according EXACT # of terms. //
		resCo = Arrays.copyOf(resCo, termNum);
    	resEx = Arrays.copyOf(resEx, termNum);
	
		return new Polynomial(resCo, resEx);
	}

	public void display(){
		int co_size = this.coefficients.length;

		// Print out coefficients and exponents. //
		for (int i = 0; i < co_size; i++){
			System.out.println("co" + i + " = " + this.coefficients[i]);
			System.out.println("ex" + i + " = " + this.exponents[i]);
		}
	}
	
	public void saveToFile(String filename) throws Exception{
		PrintStream ps = new PrintStream(filename);
		ps.println(this.poly2str());
	}

	public String poly2str() {
		String polyStr = "";
	
		for (int i = 0; i < this.coefficients.length; i++) {
			double co = this.coefficients[i];
			int ex = this.exponents[i];
	
			if (co != 0) {
				if (!polyStr.isEmpty()) {
					if (co > 0) polyStr += "+";
				}
	
				if (co != 1 || ex == 0) polyStr += co;
	
				if (ex > 0) {
					polyStr += "x";
					if (ex > 1) polyStr += ex;
				}
			}
		}
		return polyStr;
	}
}

