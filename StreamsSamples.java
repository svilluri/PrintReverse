import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StreamsSamples {

	public static void main(String args[]) {

		String inputString = "my first program";
		
		System.out.print("Input: "+inputString+"\n");
		
		printReverseUsingSplit(inputString, "Split");
		printReverseUsingStreams(inputString, "Streams", false);
		printReverseUsingStreams(inputString, "Parallel Streams", true);
		printReverseUsingStack(inputString, "Stack", true);
	}


	/**
	 * This method reverses input using String Split logic to tokenize and loop thru the tokens
	 * 
	 * @param inputString
	 * @param logic
	 */
	private static void printReverseUsingSplit(String inputString, String logic) {
		String delim = "[ ]+";
		String[] tokens = inputString.split(delim);
		
		int count = 0;
		StringBuilder strBuilder = new StringBuilder();
		for (String token : tokens) {
			if ( count++ > 0 ) {
				strBuilder.append(" ");
			}
			char[] characters = token.toCharArray();
			
			for ( int idx = characters.length; idx>0; idx--) {
				strBuilder.append(characters[idx-1]);
			}
		}

		System.out.println("Output using " +logic+": "+strBuilder.toString());
		
	}
	
	/**
	 * This method reverses input using String Split to tokenize and Streams/Parallel Streams logic
	 * 
	 * @param inputString
	 * @param logic
	 */
	private static void printReverseUsingStreams(String inputString, String logic, boolean parallelStream) {
		String delim = "[ ]+";
		String[] tokens = inputString.split(delim);
		List<String> tokenList = Arrays.asList(tokens);
		
		StringBuilder strBuilder = new StringBuilder();
		if ( !parallelStream ) {
			tokenList.stream().forEach( e -> appendReverse(strBuilder,e));
		}
		else {
			tokenList.parallelStream().forEach( e -> appendReverse(strBuilder,e));
		}

		System.out.println("Output using " +logic+": "+strBuilder.toString());
		
	}
	
	/**
	 * This method reverses input using String Split to tokenize and Stack logic
	 * 
	 * @param inputString
	 * @param logic
	 */	
	private static void printReverseUsingStack(String inputString, String logic, boolean parallelStream) {
		String delim = "[ ]+";
		String[] tokens = inputString.split(delim);
		List<String> tokenList = Arrays.asList(tokens);
		
		Stack<String> stackVal = new Stack<String>();
		tokenList.stream().forEach( e -> appendReverseUsingStack(stackVal, e));
		
		StringBuilder strBuilder = new StringBuilder();
		
		stackVal.stream().forEach(e -> strBuilder.append(e+" "));

		System.out.println("Output using " +logic+": "+strBuilder.toString().trim());		
	}
		
	
	private static void appendReverse(StringBuilder strBuilder, String token) {
		if ( strBuilder.toString().length() > 0 ) {
			strBuilder.append(" ");
		}
		
		char[] characters = token.toCharArray();
		
		for ( int idx = characters.length; idx>0; idx--) {
			strBuilder.append(characters[idx-1]);
		}	
	}
		
	private static void appendReverseUsingStack(Stack<String> stackVal, String token) {

		char[] characters = token.toCharArray();
		
		StringBuilder strBuilder = new StringBuilder();
		for ( int idx = characters.length; idx>0; idx--) {
			strBuilder.append(characters[idx-1]);
		}
		
		stackVal.push(strBuilder.toString());		
	}
}
