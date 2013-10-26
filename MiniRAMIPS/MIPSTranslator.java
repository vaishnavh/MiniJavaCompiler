import syntaxtree.Node;



public class MIPSTranslator {
	public static void main(String args[]){
		try{
			Node root = new MiniRAParser(System.in).Goal();
			
			
		}catch (ParseException e){
			System.out.println("Parse error");
		}
		
	}
	
	
	void printEnd(){
		String x = "         .text\n         .globl _halloc\n_halloc:\n         li $v0, 9\n         syscall\n         j $ra"+
"         .text\n         .globl _print\n_print:\n         li $v0, 1\n         syscall\n         la $a0, newl\n         li $v0, 4"+
"         syscall\n         j $ra\n\n         .data\n         .align   0\nnewl:    .asciiz \"\\n\"\n         .data\n         .align   0"+
"\nstr_er:  .asciiz \" ERROR: abnormal termination\\n\"";
		System.out.println(x);
	}
}
