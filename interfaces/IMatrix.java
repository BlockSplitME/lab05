package suai.lab05.interfaces;
import suai.lab05.matrixes.*;

public interface IMatrix{
	void setElement(int col, int row, int val);
	int getElement(int col, int row);

	int getRowSize();
	int getColumnSize();

	IMatrix sum(IMatrix tmp);
	IMatrix product(IMatrix tmp);
}
