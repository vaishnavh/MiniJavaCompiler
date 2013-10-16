import datastore.ProcedureBlock;
import syntaxtree.Node;
import visitor.LivenessAnalyser;


public class MiniRATranslator {
	public static void main(String args[]){
		try{
			Node root = new microIRParser(System.in).Goal();
			LivenessAnalyser la = new LivenessAnalyser();
			root.accept(la); //First develop all ins and outs			
			for(String key : la.livenesses.keySet()){
				ProcedureBlock p = la.livenesses.get(key);
				p.dissolveJumps();
				p.computeGraph();
				p.print();
				p.computeIntervals();
			}
		}catch (ParseException e){
			System.out.println("Parse error");
		}
		
	}
}
