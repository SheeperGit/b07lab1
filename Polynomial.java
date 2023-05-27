public class Polynomial {
	double coefficients[];
	
	public Polynomial() {
		coefficients = new double[]{0};
	}
	
	public Polynomial(double arr[]) {
		coefficients = new double[arr.length];
		for(int i = 0; i < arr.length; i++) {
			coefficients[i] = arr[i];
		}
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
		
		return new Polynomial(res);
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
}

