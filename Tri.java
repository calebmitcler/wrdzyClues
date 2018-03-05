import java.util.*;
import java.io.*;

public class Tri{

	public static void main(String[] args) throws IOException{

		String strFromFile = "";
		Scanner read = new Scanner(new File("allwords.txt")).useDelimiter("\n");
		List<String> words = new ArrayList<String>();

		while(read.hasNext()){
			strFromFile = read.next();
			words.add(strFromFile);
		}
		read.close();

		List<String> allTriGrams = new ArrayList<String>();
		for(String word:words){
			for(String gram:triGram(word)){
				allTriGrams.add(gram);
			}
		}
		PrintWriter pw = new PrintWriter("clues.txt");
		for(String tGram:sortGrams(allTriGrams)){
			//System.out.println(tGram);
			pw.println(tGram);
		}
		pw.close();

		

	}//end main

	public static List<String> triGram(String str){
		String[] letters = str.split("");
		List<String> grams = new ArrayList<String>();
		for(int i=0;i<letters.length -2;i++){
			grams.add(str.substring(i,i+3));
		}
		return grams;
	}//end triGram

	public static List<String> sortGrams(List<String> allGrams){

		Map<String, Integer> counter = new HashMap<String, Integer>();

		for(String str:allGrams){
			counter.put(str, 1 + (counter.containsKey(str) ? counter.get(str):0));
		}
		List<String> list = new ArrayList<String>(counter.keySet());
		Collections.sort(list, new Comparator<String>(){
			@Override
			public int compare(String x, String y){
				return counter.get(y)-counter.get(x);
			}
		});
		return list;

	}//end sortGrams



}