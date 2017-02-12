import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//use brutemarkov but with a map instead of a loop
public class EfficientMarkov implements MarkovInterface<String> {
	private static String PSEUDO_EOS = "";
	private String myText;
	private ArrayList<String> CharFollow;
	private Random myRandom;
	private int myOrder;
	private static long RANDOM_SEED = 5678;
	private HashMap<String, ArrayList<String>> MarkMap = new HashMap<String, ArrayList<String>>();
	
	public EfficientMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	@Override
	public void setTraining(String text) {
		// TODO Auto-generated method stub
		
//take last two letters and teh 3 gram seed and thn add the letter that follws the 3gram seed
	//if 3 gram is last aka value = null(on efolllowing), we end the text with that 3 gram --- stop generating and end
	for(int k =0;k<text.length()-myOrder;k++){
		if(k==(text.length()-myOrder)){ //checks if its the last "Set"/kgram
			myText = text.substring(k,k+(myOrder-1));
			CharFollow.add(PSEUDO_EOS);
			MarkMap.put(myText, CharFollow);
		}
			myText = text.substring(k,k+(myOrder-1));
			CharFollow.add(k, Character.toString(text.charAt(k+myOrder)));
			MarkMap.put(myText, CharFollow);	
			
		}	
	
	
		//wrong because not inlcuding pseudo eos?
		
	}
	

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

	}

}
