import java.util.Arrays;

public class WordGram implements Comparable<WordGram> {
	private String[] myWords;
	private int myHash;

	public WordGram(String[] source, int start, int size){
		myWords = new String[size];
		for(int k=start;k<size+start;k++){
			this.myWords[k-start] = source[k];//put at 0th element in word, but the start from source
		}    
	}

	public int hashCode(){
		
		myHash = 0;
		for(int k=0; k < this.myWords.length; k++) {
			myHash = 73*myHash + this.myWords[k].hashCode();
		}

		return myHash;
	}
	

	public boolean equals(Object other){
		if(!(other instanceof WordGram)){
			return false;	
		}
		/*
		WordGram wg = (WordGram) other;
		for(int k=0; k<this.myWords.length;k++){
			if(!wg.myWords[k].equals(this.myWords[k])){
				return false;
			}
		}*/
		if((this.hashCode())==(other.hashCode())){
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(WordGram o){
		//return Arrays.toString(this.myWords).compareTo(Arrays.toString(o.myWords));
	//}
	int value = 0;
		if(this.myWords.length<o.myWords.length){
			value = -1;
		}
		if(this.myWords.length==o.myWords.length){
			for(int k=0; k<this.myWords.length;k++){
				char[] Charthis = this.myWords[k].toCharArray();
				char[] Charo = o.myWords[k].toCharArray();
				for(int j=0; k<Charthis.length;k++){
					if(Charthis[j]<Charo[j]){
						value--;
					}
					if(Charthis[j]>Charo[j]){
						value++;
					}
				}
			}
		}
		if(this.myWords.length==o.myWords.length){
			for(int k=0; k<this.myWords.length;k++){
			value = (this.myWords[k].compareTo(o.myWords[k]));
			}	
		}
	
		if(this.myWords.length>o.myWords.length){
			value = 1;
		}
return value;
	
	}
	public int length(){
		int size = this.myWords.length;
		return size;
	}
	

	public String toString(){
		String printString = "{";
		for (int i=0;i<this.myWords.length;i++){
			if(i<this.myWords.length-1){
				printString = printString + this.myWords[i]+",";
			}
			else{
				printString = printString + this.myWords[i];
			}
		}
		printString = printString +"}";
		return printString;
	}
	
	public WordGram shiftAdd(String last){
		this.myWords[this.myWords.length-1] = last; //had to -1 because the last element number is actual 1 less than its size
		WordGram wg = new WordGram(this.myWords,0,this.myWords.length);
		return wg;
		
	}



}
