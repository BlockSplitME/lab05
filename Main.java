package suai.lab05;
import suai.lab05.matrixes.*;
import suai.lab05.excpt.*;

public class Main{
	public static void main(String[] args){
		try{
			System.out.println("Usual Matrix check:");
			UsualMatrix a1 = new UsualMatrix(6, 3);
			UsualMatrix b = new UsualMatrix(3, 4);
			for(int i = 0; i < 3; i++){
				b.setElement(i, i, 2);
				a1.setElement(i, i, 3);
			}
			b.setElement(2, 1, 5);
			b.setElement(0, 2, 4);
			b.setElement(0, 1, 6);
			a1.setElement(0, 1, 4);
			a1.setElement(3, 2, 7);
			a1.setElement(2, 0, 12);
			a1.setElement(5, 1, 13);
			System.out.println("a1:\n"+a1);
			
			System.out.println("b:\n"+b);
			
			UsualMatrix a = a1.product(b);
			System.out.println("a = a1*b:\n"+a);
			
		
			UsualMatrix c = new UsualMatrix(6, 4);
			for(int i = 0, j = 2; i < 2; i++, j++){
				c.setElement(i, i, 2);
				c.setElement(j, i, 3);
				c.setElement(i, j, 5);
			}
			System.out.println("c:\n"+c);
			
			
			System.out.println("c+a: \n" + c.sum(a));

			System.out.println("Square Matrix check:");

			
			SquareMatrix d = new SquareMatrix(3);
			d.setElement(2, 1, 5);
			d.setElement(0, 2, 4);
			d.setElement(0, 1, 6);
			System.out.println("d:\n"+d);
				
			
			SquareMatrix e = new SquareMatrix(3);
			e.setElement(0, 1, 2);
			e.setElement(1, 2, 4);
			e.setElement(0, 2, 7);
			System.out.println("e:\n"+e);
			
			
			System.out.println("d+e:\n"+d.sum(e));
			
			System.out.println("d*e:\n" + d.product(e));

			UsualMatrix m = new UsualMatrix(3, 3);
			m.setElement(2, 1, 5);
			m.setElement(0, 2, 4);
			m.setElement(0, 1, 6);
			System.out.println("m:\n"+m);
			
			System.out.println("d+m:\n"+d.sum(m));
			
			System.out.println("m+d:\n"+m.sum(d));
			
			System.out.println("d*m:\n" + d.product(m));
			System.out.println("m*d:\n" + m.product(d));

			UsualMatrix v = new UsualMatrix(1, 1);
			v.setElement(0, 0, 3);
			System.out.println("v:\n" + v);
			System.out.println("v+v:\n" + v.sum(v));
			System.out.println("v*v:\n" + v.product(v));

			System.out.println("Sparse Matrix check:");

			
			
			SparseMatrix test = new SparseMatrix(50, 50);
			test.fill(2);
			System.out.println("test: \n" + test);

			SparseMatrix t1 = new SparseMatrix(3, 3);
			t1.fill(2);
			System.out.println("t1: \n" + t1);

			SparseMatrix t2 = new SparseMatrix(3, 3);
			t2.fill(2);
			System.out.println("t2: \n" + t2);

			System.out.println("t1+t2: \n" + t1.sum(t2));
			System.out.println("t1*t2: \n" + t1.product(t2));
			System.out.println("t2*t1: \n" + t2.product(t1));

			System.out.println("m:\n"+m);
			
			System.out.println("m+t2: \n" + m.sum(t2));
			System.out.println("m*t2: \n" + m.product(t2));
			System.out.println("t2*m: \n" + t2.product(m));
			
		}
		catch(MatrixException error){
			error.getMassage();
			error.printStackTrace();
		}
		finally{
			System.out.println("\nEND\n");
		}
	}
}
