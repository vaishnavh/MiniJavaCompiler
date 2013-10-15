import syntaxtree.Node;
import visitor.LivenessAnalyser;


public class MiniRATranslator {
	public static void main(String args[]){
		try{
			Node root = new microIRParser(System.in).Goal();
			LivenessAnalyser la = new LivenessAnalyser();
			root.accept(la); //First develop all ins and outs			
			for(String key : la.livenesses.keySet()){
				la.livenesses.get(key).print();
			}
		}catch (ParseException e){
			
		}
	}
}
