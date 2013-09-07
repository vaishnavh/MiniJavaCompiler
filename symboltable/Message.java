package symboltable;

public class Message {
		public String className, methodName;
		public IdentifierType identifierType;
		public ReturnType returnType;
		public int arg;
		public Message(String cn, String mn, IdentifierType it, ReturnType rt){
			className = cn;
			methodName = mn;
			identifierType = it;
			returnType = rt;
			arg = 0;
		}
		
		public Message(String cn, String mn){
			className = cn;
			methodName = mn;
			identifierType = IdentifierType.NONE;
			returnType = ReturnType.NONE;
		}
		
		
		public static enum IdentifierType{
			CLASS, METHOD, VARIABLE, DATATYPE, NONE
		}
		
		public static enum ReturnType{
			NAME, TYPE, NONE
		}
}

//Whenever return type is NAME strign is returned as it is!