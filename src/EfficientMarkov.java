import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientMarkov implements MarkovInterface<String>{

	private String myText;
	private String CharFollow;
	private Random myRandom;
	private int myOrder;

	private static String PSEUDO_EOS = "";
	private static long RANDOM_SEED = 1234;
	private static HashMap<String, ArrayList<String>> MarkMap = new HashMap<String, ArrayList<String>>();

	public EfficientMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	public EfficientMarkov() {
		this(3);
	}

	@Override
	public void setTraining(String text) {
	myText = text;
		// TODO Auto-generated method stub

		//take last two letters and teh 3 gram seed and thn add the letter that follws the 3gram seed
		//if 3 gram is last aka value = null(on efolllowing), we end the text with that 3 gram --- stop generating and end
for(int k =0;k<myText.length()-myOrder;k++){
	 //creates temporary part of text that going to add
	String TextKey = myText.substring(k,k+myOrder); //sets temp text to a gram
	
	
		if(k+myOrder+1>=myText.length()){ //once intialized or if doesn't need to be, checks if its at the end of the text or not
		CharFollow = PSEUDO_EOS;		//if at the end, the following character is PSEUDO_EOS
		}
		else{
		CharFollow = text.substring(k+myOrder,k+myOrder+1); //if not at the end, the follow character is the following character
		}
		if(!(MarkMap.containsKey(TextKey))){ //initializes 
			MarkMap.put(TextKey, new ArrayList<String>());
		}
	MarkMap.get(TextKey).add(CharFollow); //once spot created or not, char follow named, it can now add the folllowin character to the value spot for that key
}
	
}	

	public int size() {
		return myText.length();
	}
	//wrong because not inlcuding pseudo eos?




	@Override
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



	// TODO Auto-generated method stub


	@Override
	public ArrayList<String> getFollows(String key) {
		// TODO Auto-generated method stub
		ArrayList<String> follows = new ArrayList<String>();
		follows = MarkMap.get(key);
		return follows;
	}
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		// TODO Auto-generated method stub
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);

	}
	


}
