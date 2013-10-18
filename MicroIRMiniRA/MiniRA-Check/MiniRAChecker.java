import syntaxtree.Node;




public class MiniRAChecker {
	public static void main(String [] args){
		try {
	         Node root = new MiniRAParser(System.in).Goal();	         
	         
	      }
	      catch (ParseException e) {
	         System.out.println(e.toString());
	      }	
	}
}
