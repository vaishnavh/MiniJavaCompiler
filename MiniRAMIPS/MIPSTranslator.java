import syntaxtree.Node;
import visitor.Translator;



public class MIPSTranslator {
	public static void main(String args[]){
		try{
			Node root = new MiniRAParser(System.in).Goal();
			Translator tr = new Translator();
			root.accept(tr);
			printEnd();
		}catch (ParseException e){
			System.out.println("Parse error");
		}
		
	}
	
	
	static void printEnd(){
		String x = "\n\n         .text\n         .globl _halloc\n_halloc:\n         li $v0, 9\n         syscall\n         jr $ra"+
"\n         .text\n         .globl _print\n_print:\n         li $v0, 1\n         syscall\n         la $a0, newl\n         li $v0, 4"+
"\n         syscall\n         jr $ra\n\n         .data\n         .align   0\nnewl:    .asciiz \"\\n\"\n         .data\n         .align   0"+
"\nstr_er:  .asciiz \" ERROR: abnormal termination\\n\"";
		System.out.println(x);
	}
}
