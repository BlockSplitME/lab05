package suai.lab05.excpt;

public class MatrixException extends RuntimeException{
	private String error;

	public MatrixException(String error){
		this.error = error;
		System.out.println("This is a MatrixException...");
	}
	public void getMassage(){
		System.err.println("Error:" + error);
	}
}