package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private boolean dicotomica;
	private List<String> dizionario;
	
	public Dictionary() {
		dizionario = new LinkedList<String>();
	}
	public void loadDictionary(String language) {
		
		
		try {
			FileReader fr= new FileReader("rsc/English.txt");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				dizionario.add(word.toLowerCase());
			}
			br.close();
		
		}catch(IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
		
		
		try {
		FileReader ir= new FileReader("rsc/Italian.txt");
		BufferedReader ibr= new BufferedReader(ir);
		String italianWord;
		while((italianWord=ibr.readLine())!=null) {
			dizionario.add(italianWord.toLowerCase());
		}
		ibr.close();
		
		}catch(IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
		
	}


	public List<RichWord> spellCheckText(List<String>inputFileText){
		List<RichWord> parole = new LinkedList<RichWord>();
		RichWord r;
		for (String s : inputFileText) {
			
			if (dicotomica) {
				if (binarySearch(s.toLowerCase())) 
					r = new RichWord(s, true);
				else 
					r = new RichWord(s, false);
				parole.add(r);
				
			} else {
				if (dizionario.contains(s.toLowerCase())) 
					r = new RichWord(s, true);
				else 
					r = new RichWord(s, false);
				parole.add(r);
			}
}
	
		
		return parole;
	}
	private boolean binarySearch(String s) {
		int inizio = 0;
	     int fine = dizionario.size();

	     while (inizio!=fine){
	         int medio = inizio + (fine - inizio)/2;
	         if (s.compareToIgnoreCase(dizionario.get(medio))==0){
	             return true;
	         } else if (s.compareToIgnoreCase(dizionario.get(medio))>0){
	               inizio=medio +1;
	           } else {
	               fine=medio;
	           }
	     }
		
		return false;
}
}

