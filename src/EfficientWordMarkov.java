import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientWordMarkov implements MarkovInterface<WordGram> {
	private String myText;
	private String[] myTextArray;
	private Random myRandom;
	private int myOrder;
	
	private  HashMap<WordGram, ArrayList<String>> EfWordMap = new HashMap<WordGram, ArrayList<String>>();
	private  String PSEUDO_EOS = "";
	private long RANDOM_SEED = 1234;
	
	public EfficientWordMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public EfficientWordMarkov(){
		this(3);
	}
	
	
		public void setTraining(String text) {
			myText = text;
			myTextArray = text.split("\\s+");

			WordGram WGKey;
			EfWordMap = new HashMap<WordGram, ArrayList<String>>();

			for(int k =0;k<myTextArray.length-myOrder+1;k++){
				//creates temporary part of text that going to add
				WGKey =  new WordGram(myTextArray, k, myOrder); //sets temp text to a gram
				if(!(EfWordMap.containsKey(WGKey))){ //initializes 
					EfWordMap.put(WGKey, new ArrayList<String>());
				}
			
			 if(k+myOrder>=myTextArray.length){ //once intialized or if doesn't need to be, checks if its at the end of the text or not
				 EfWordMap.get(WGKey).add(PSEUDO_EOS);//if at the end, the following character is PSEUDO_EOS

				}
			 else{
					
				 EfWordMap.get(WGKey).add(myTextArray[k+myOrder]); //if not at the end, the follow character is the following character
				}
				
				//once spot created or not, char follow named, it can now add the folllowin character to the value spot for that key
			}
		}	
		 //once spot created or not, char follow named, it can now add the folllowin character to the value spot for that key


	public int size(){
		return myText.length();
	}
	@Override
	public String getRandomText(int length) {
		String[] myTextArray = myText.split("\\s+");
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myTextArray.length - myOrder);

		WordGram current = new WordGram(myTextArray, index, myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current.toString()+" ");

		for(int k=0; k <length-myOrder; k++){
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
			sb.append(nextItem + " ");
			current = current.shiftAdd(nextItem);
		}
		return sb.toString();
	

	
	}

	@Override
	public ArrayList<String> getFollows(WordGram key) {
		// TODO Auto-generated method stub
ArrayList<String> following = EfWordMap.get(key);
		return following;

	
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
