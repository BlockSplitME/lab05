package suai.lab05.matrixes;
import suai.lab05.excpt.*;
import suai.lab05.interfaces.*;

public class SquareMatrix extends UsualMatrix implements IMatrix{
	
	public SquareMatrix(int n){
		super(n,n);
	}

	public final int getSize(){
		return colSize;
	}
	@ Override
	public final SquareMatrix sum(IMatrix tmp){
		if ((this.colSize == tmp.getColumnSize())&&(this.rowSize == tmp.getRowSize())){
				SquareMatrix cur = new SquareMatrix(this.getSize());
				for(int i = 0; i < this.getSize(); i++){
					for(int j = 0; j < this.getSize(); j++){
						cur.setElement(i,j,(this.getElement(i,j) + tmp.getElement(i,j)));
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}
	@ Override
	public final SquareMatrix product( IMatrix tmp){	
			if (this.rowSize == tmp.getColumnSize()){
				SquareMatrix cur = new SquareMatrix(this.getSize());
				for(int i = 0; i < this.getColumnSize(); i++){
					for(int j = 0; j < this.getRowSize(); j++){
						for (int k = 0; k < this.getRowSize(); k++) {
							cur.setElement(i,j,(cur.getElement(i,j)+(this.getElement(i,k) * tmp.getElement(k,j))));
						}
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}

	@ Override
	public boolean equals(Object tmp) {
	    if(tmp instanceof SquareMatrix)
			return ((this.toString()).equals(tmp.toString()));   
    	return false;
	}
}