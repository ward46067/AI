package sentence;

import java.util.ArrayList;
import word.Word;

public class ParseSentence {
    private ArrayList words = new ArrayList();
    
    public ParseSentence(String s){
        s = s.trim();
        
        while(s.indexOf(" ") != -1){
            String temp = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf(" "), s.length());
            s = s.trim();
            
            temp = temp.toLowerCase();
            temp = temp.replace(".", "");
            temp = temp.replace(",", "");
            temp = temp.replace("/", "");
            temp = temp.replace(";", "");
            temp = temp.replace(":", "");
            temp = temp.replace("<", "");
            temp = temp.replace(">", "");
            temp = temp.replace("?", "");
            temp = temp.replace("\"", "");
            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
            temp = temp.replace("-", "");
            temp = temp.replace("_", "");
            temp = temp.replace("!", "");
            temp = temp.replace("@", "");
            temp = temp.replace("#", "");
            temp = temp.replace("$", "");
            temp = temp.replace("%", "");
            temp = temp.replace("^", "");
            temp = temp.replace("&", "");
            temp = temp.replace("*", "");
            temp = temp.replace("`", "");
            temp = temp.replace("~", "");
            temp = temp.replace("|", "");
            temp = temp.replace("", "");
            words.add(temp);
        }
        
        words.add(s);
        
        //check if number
        for(int i = 0; i < words.size(); i++){
            try{
                int num = Integer.parseInt((String) words.get(i)); //is a number
                words.remove(i);
            } catch (NumberFormatException e) {
                
            }
        }
        
        for(int i = 0; i < words.size(); i++){
            String temp = (String) words.get(i);
            
            Word tempWord = new Word(temp);
            words.set(i, tempWord);
        }
        if(words.size() > 1){
            for(int i = 0; i < words.size(); i++){
                Word w1, w2, w3;
                if(i == 0){
                    w1 = (Word) words.get(i);
                    w2 = (Word) words.get(i + 1);
                    w1.addWordAfter(w2);
                } else if((i + 1) == words.size()){
                    w1 = (Word) words.get(i);
                    w2 = (Word) words.get(i - 1);
                    w1.addWordBefore(w2);
                } else {
                    w1 = (Word) words.get(i);
                    w2 = (Word) words.get(i + 1);
                    w3 = (Word) words.get(i - 1);

                    w1.addWordAfter(w2);
                    w1.addWordBefore(w3);
                }
            }
            System.out.println("Added words");
        }
    }
    
    public String printSentence(){
        String s = "";
        for(int i = 0; i < words.size(); i++){
            s += words.get(i) + " ";
        }
        return s;
    }
}
