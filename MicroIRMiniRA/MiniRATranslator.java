import syntaxtree.Node;
import visitor.LivenessAnalyser;
import visitor.RegisterSaver;
import visitor.Translator;
import datastore.ProcedureBlock;


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
				p.computeIntervals();
				p.registerAllocation();
				//p.print();
				p.clear();
			}
			RegisterSaver rs = new RegisterSaver();
			rs.livenesses = la.livenesses;
			root.accept(rs);
			Translator t = new Translator();
			t.livenesses = la.livenesses;
			root.accept(t);
		}catch (ParseException e){
			System.out.println("Parse error");
		}
		
	}
}
