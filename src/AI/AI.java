package AI;

import ward.console.Console;
import word.Word;

public class AI{
    
    public static void main(String[] args){
        Console console = new Console();
        console.setSize(500, 500);
        console.setTitle("AI");
        console.build();
        
        Word word = new Word("Word", 0);
    }
}