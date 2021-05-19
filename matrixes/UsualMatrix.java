package suai.lab05.matrixes;
import suai.lab05.interfaces.*;
import suai.lab05.excpt.*;

public class UsualMatrix implements IMatrix{
	protected int rowSize;
	protected int colSize;
	protected int[][] matrix;

	public UsualMatrix(int column, int row) { 
		this.colSize = column;
		this.rowSize = row;
		this.matrix = new int[colSize][rowSize];
	}

	@ Override
	public final void setElement(int column, int row, int value){
		if((row < this.rowSize && row >= 0) || (column >= 0 && column < this.colSize)){
			this.matrix[column][row] = value;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e;}
	}

	@ Override
	public final int getElement(int column, int row){
		if((row < this.rowSize && row >= 0) || (column >= 0 && column < this.colSize)){
			return this.matrix[column][row];
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}

	@ Override
	public final int getColumnSize(){
		return this.colSize;
	}

	@ Override
	public final int getRowSize(){
		return this.rowSize;
	}

	public final int[] getColumn(int row){
		if (row < this.rowSize && row >= 0){
			int[] tmp = new int[this.colSize];
			for (int i = 0; i < this.colSize; i++) {
				tmp[i] = this.matrix[i][row];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}

	public final int[] getRow(int column){
		if (column < this.colSize && column >= 0){
			int[] tmp = new int[this.rowSize];
			for (int j = 0; j < this.rowSize; j++) {
				tmp[j] = this.matrix[column][j];
			}
			return tmp;
		} else {
			MatrixException e = new MatrixException("Memory access error"); throw e;
		}
	}


	public UsualMatrix sum(IMatrix tmp){	
			if ((this.colSize == tmp.getColumnSize())&&(this.rowSize == tmp.getRowSize())){
				UsualMatrix cur = new UsualMatrix(this.colSize, this.rowSize);
				for(int i = 0; i < this.colSize; i++){
					for(int j = 0; j < this.rowSize; j++){
						cur.setElement(i,j,(this.getElement(i,j) + tmp.getElement(i,j)));
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}

	
	public UsualMatrix product(IMatrix tmp){
			if (this.rowSize == tmp.getColumnSize()){
				UsualMatrix cur = new UsualMatrix(this.colSize, tmp.getRowSize());
				for(int i = 0; i < cur.colSize; i++){
					for(int j = 0; j < cur.rowSize; j++){
						for (int k = 0; k < this.rowSize; k++) {
							cur.setElement(i,j,(cur.getElement(i,j)+(this.getElement(i,k) * tmp.getElement(k,j))));
						}
					}
				}
				return cur;
			} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
	}

	@ Override
	public String toString(){ 
		StringBuilder str = new	StringBuilder();	
		for(int i = 0; i < this.getColumnSize(); i++){
			for(int j = 0; j < this.getRowSize(); j++){
				str.append(this.getElement(i,j));
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString(); 
	}
	@ Override
	public boolean equals(Object tmp) {
	    if(tmp instanceof UsualMatrix)
			return ((this.toString()).equals(tmp.toString()));   
    	return false;
	}
}