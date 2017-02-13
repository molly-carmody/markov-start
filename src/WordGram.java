
public class WordGram implements Comparable<WordGram> {
	private String[] myWords;
	private int myHash;

	public WordGram(String[] source, int start, int size){
		for(int k=start;k<size;k++){
			this.myWords[k] = source[k];
		}    
	}

	public int hashCode(){
		int hash = 0;
		for(int k=0; k < this.myWords.length; k++) {
			hash = 100*hash + this.myWords[k].hashCode();
		}
		myHash = hash;
		return myHash;
	}

	public boolean equals(Object other){
		if(!(other instanceof WordGram)){
			return false;	
		}
		WordGram wg = (WordGram) other;
		for(int k=0; k<this.myWords.length;k++){
			if(!wg.myWords[k].equals(this.myWords[k])){
				return false;
			}
		}
		if(this.hashCode()==other.hashCode()){
			return true;
		}
		return true;
	}
	@Override
	public int compareTo(WordGram o){
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


		if(this.myWords.length>o.myWords.length){
			value = 1;
		}
return value;
	}
	
	
	public int length(){
		int size = this.myWords.length;
		return size;
	}
	
	
	@Override
	public String toString(){
		String printString = "";
		for (int i=0;i<this.myWords.length;i++){
			printString = printString + "," + "\""+this.myWords[i]+"\"";
		}
		return "{"+printString+"}";
	}
	
	
	public WordGram shiftAdd(String last){
		this.myWords[this.myWords.length] = last;
		WordGram wg = new WordGram(this.myWords,0,this.myWords.length);
		return wg;
		
	}



}
