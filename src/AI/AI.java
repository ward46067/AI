package AI;

import sentence.ParseSentence;
import ward.console.Console;
import word.Word;

public class AI{
    
    public static void main(String[] args){
        Console console = new Console();
        console.setSize(500, 500);
        console.setTitle("AI");
        console.build();
        
        
        
        Word all = new Word("all");
        Word getting = new Word("getting");
        all.addWordBefore(getting);
        
        while(true){
            ParseSentence sentence = new ParseSentence(console.answer());
        }
    }
}