import java.io.File;

public class Driver {
	public static void main(String [] args) throws Exception{
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double[] c1 = {6,0,0,5};
		int[] e1 = {0, 3, 4, 5};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {0,-2,0,0,-9};
		int[] e2 = {2, 4, 8, 16};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s\n");
		else
			System.out.println("1 is not a root of s\n");

		double[] c3 = {6,1,2,5};
		int[] e3 = {1, 3, 4, 5};
		Polynomial p3 = new Polynomial(c3, e3);

		double[] c4 = {1,2,-3,-4.1,5};
		int[] e4 = {6,7,8,9,10};
		Polynomial p4 = new Polynomial(c4, e4);

		p3.display();
		p4.display();

		Polynomial p5 = p3.multiply(p4);
		System.out.println("mult of p3, p4 = ");
		p5.display();

		File f = new File("/home/sean_the_sheep/b07lab2dir/b07lab1/test.txt");
		Polynomial p6 = new Polynomial(f);
		p6.display();

		Polynomial p7 = new Polynomial(new double[]{5, -3, 7}, new int[]{0, 2, 8});
		p7.display();
		p7.saveToFile("/home/sean_the_sheep/b07lab2dir/b07lab1/saveFile.txt");

		Polynomial p8 = new Polynomial();
		p8.display();
		p8.saveToFile("/home/sean_the_sheep/b07lab2dir/b07lab1/saveFile2.txt");

		File f2 = new File("/home/sean_the_sheep/b07lab2dir/b07lab1/saveFile2.txt");
		Polynomial p9 = new Polynomial(f2);
		p9.display();
	}
}
