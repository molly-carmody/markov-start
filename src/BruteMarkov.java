import java.util.ArrayList;
import java.util.Random;

public class BruteMarkov implements MarkovInterface<String> {
	private String myText;
	private Random myRandom;
	private int myOrder;
	
	private static String PSEUDO_EOS = "";
	private static long RANDOM_SEED = 1234;
	
	public BruteMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public BruteMarkov() {
		this(3);
	}
	
	public void setTraining(String text) {
		myText = text;
	}
	public int size() {
		return myText.length();
	}
	/*Here's a description of what this code does:

Pick a random k-letter sequence from the entire file/string to start generating random text. This is the current.
Find each occurrence of this current (random k-letter sequence) and record the character that follows it in follows.
Pick one of these following-characters at random, use it to build the random text, and make it the last character of the k-letter sequence used to repeat the process, where the first k-1 letters come from the seed.
repeat until enough random text is generated or we generate an end-of-file.*/
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);

		String current = myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current);
			if (follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.append(nextItem);
			current = current.substring(1)+ nextItem;
		}
		return sb.toString();
	}
	//returns a list of all the characters that follow key (come after)
	public ArrayList<String> getFollows(String key){
		ArrayList<String> follows = new ArrayList<String>();
		
		int pos = 0;  // location where search for key in text starts
		
		while (pos < myText.length()){
			int start = myText.indexOf(key,pos);
			if (start == -1){
				//System.out.println("didn't find "+key);
				break;
			}
			if (start + key.length() >= myText.length()){
				//System.out.println("found end with "+key);
				follows.add(PSEUDO_EOS);
				break;
			}
			// next line is string equivalent of myText.charAt(start+key.length())
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+1;  // search continues after this occurrence
		}
		return follows;
	}

	@Override
	public int getOrder() {
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);	
	}
	
}
