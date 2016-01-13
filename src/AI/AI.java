package AI;

import sentence.ParseSentence;
import ward.console.Console;

public class AI{
    
    public static void main(String[] args){
        Console console = new Console();
        console.setSize(500, 500);
        console.setTitle("AI");
        console.build();
        
        while(true){
            ParseSentence sentence = new ParseSentence(console.answer());
        }
    }
}