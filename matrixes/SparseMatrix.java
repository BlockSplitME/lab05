package suai.lab05.matrixes;
import suai.lab05.excpt.*;
import suai.lab05.interfaces.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class SparseMatrix implements IMatrix{
	
	protected static class NonZeroElement{
		public int row;
		public int column;
		public int value;

		public NonZeroElement(int col,int row,int val){
			this.row = row; this.column = col; this.value = val;
		}
		public String toString(){
			return "" + this.value;
		}
	} 

	protected LinkedList<NonZeroElement> matrix;
	protected int rowSize;
	protected int colSize;

	public SparseMatrix(int col, int row){
		this.matrix = new LinkedList<NonZeroElement>();
		this.rowSize = row;
		this.colSize = col;
	}

	@ Override
	public final int getColumnSize(){
		return this.colSize;
	}
	@ Override
	public final int getRowSize(){
		return this.rowSize;
	}
	@ Override
	public final void setElement(int curColumn, int curRow, int curValue){
		if((curRow >= this.rowSize || curRow < 0) || (curColumn < 0 || curColumn >= this.colSize)){
			MatrixException e = new MatrixException("Memory access error");
			throw e;
		}
		if(curValue == 0){
			this.remove(curColumn, curRow);
			
			return;
		}
		NonZeroElement cur = new NonZeroElement(curColumn, curRow, curValue);
	    ListIterator<NonZeroElement> iter = this.matrix.listIterator();
	    while (iter.hasNext()) {
	      NonZeroElement element = iter.next();
	        if(cur.value == element.value && cur.row == element.row && cur.column == element.column){ 
	        	return;
	        } else if(cur.value != element.value && cur.row == element.row && cur.column == element.column){ 
	        	iter.set(cur);
	        	return;
	        } else if(cur.row < element.row && cur.column == element.column){
	        	iter.previous();
	        	iter.add(cur);
	        	iter.next();
	        	return;
	        } else if(cur.column < element.column){ 
	        	iter.previous();                    
	        	iter.add(cur);
	        	iter.next();
	        	if(this.rowSize < curRow+1){
	        		this.rowSize = curRow+1;
	    		}
				return;
	        }
	    }
	    this.matrix.addLast(cur);
		return;
	}
	@ Override
	public final int getElement(int col, int row){
		if((row < this.rowSize && row >= 0) || (col >= 0 && col < this.colSize)){
			for(NonZeroElement element : this.matrix){
				if(element.column > col){
					return 0;
				} else if(element.column == col){
					if(element.row == row){
						return element.value;
					} else if(element.row > row){
						return 0;
					}
				}
			}
			return 0;			
		} else {
			MatrixException e = new MatrixException("Memory access error"); 
			throw e;
		}
	}

	private final void remove(int col, int row){
		for(Iterator<NonZeroElement> iter = this.matrix.iterator();iter.hasNext();){
			NonZeroElement element = iter.next();
			if(element.row == row && element.column == col){
	        	iter.remove();
	    		return;
	    	}
		}
	}

	public final void fill(int max){
		for (int i = 0; i <= (max-1); i++) {
			for (int j = 0; j <= (max-1); j++) {
				if(i != j){
					this.setElement(i, j, (int)(Math.random()* 9) + 1);
				}
			}
		}
	}
	@ Override
	public SparseMatrix sum(IMatrix tmp){
		if((this.colSize == tmp.getColumnSize()) && (this.rowSize == tmp.getRowSize())){
			SparseMatrix cur = new SparseMatrix(this.colSize, this.rowSize );
			Iterator<NonZeroElement> iter = this.matrix.iterator();
	    	NonZeroElement element = new NonZeroElement(-1, -1, 0);
	    	if(iter.hasNext()){
	    		element = iter.next();	    			
	    	}
	    	for (int i = 0; i < this.colSize; i++) {
	    		for (int j = 0; j < this.rowSize; j++) {
	    			if(i == element.column && j == element.row){ 
	    				cur.setElement(i, j, (tmp.getElement(i, j) + element.value) );
	    				if(iter.hasNext()){
	    					element = iter.next();
	    				}
	    			} else {
	    				cur.setElement(i, j, (tmp.getElement(i, j)));
	    			}
	    		}
	    	}
	    	return cur;
		} else { MatrixException e = new MatrixException("Memory access error"); throw e; }
	}
	@ Override
	public SparseMatrix product(IMatrix tmp){
		if(this.getRowSize() == tmp.getColumnSize()){

			SparseMatrix cur = new SparseMatrix(this.getColumnSize(), tmp.getRowSize());
			for(int i = 0; i < cur.getColumnSize(); i++){
				for(int j = 0; j < cur.getRowSize(); j++){
					for (int k = 0; k < this.getRowSize(); k++) {
						cur.setElement(i,j,(cur.getElement(i,j)+(this.getElement(i,k) * tmp.getElement(k,j))));
					}
				}
			}
			return cur;
		} else { MatrixException e = new MatrixException("Matrix sizes are different"); throw e; }
		
	}
	

	@ Override
	public String toString(){
		StringBuilder str = new StringBuilder(); 
	    Iterator<NonZeroElement> iter = this.matrix.iterator();
	    NonZeroElement element = new NonZeroElement(-1, -1, 0);
	    if(iter.hasNext()){
	    	element = iter.next();
	    }
	    for (int i = 0; i < this.colSize; i++) {
	    	for(int j = 0; j < this.rowSize; j++){
	    		if(i == element.column && j == element.row){
	    			str.append(element.value);
	    			str.append(" ");
	    			if(iter.hasNext()){
	    				element = iter.next();	    			
	    			}
	    		} else {
	    			str.append("0 ");
	    		}
	    	}
	    	str.append("\n");
	    }
	    return str.toString(); 
	}
	@ Override
	public boolean equals(Object tmp){
		if(tmp instanceof SparseMatrix)
	        return ((this.toString()).equals(tmp.toString())); 
	      return false;
	}

}