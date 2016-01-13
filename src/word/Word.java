package word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.*;

public class Word {
    private String word;
    private Letter[] letters;
    private int type;
    private File file;
    
    private Word[] wordsBefore;
    private Word[] wordsAfter;
    private int[] timesBefore;
    private int[] timesAfter;
    
    public Word(String w, int t){
        word = w;
        type = t;
        letters = new Letter[word.length()];
        file = new File("Data/Word/" + word.toUpperCase() + ".xml");
        
        for(int i = 0; i < word.length(); i++){
            letters[i] = new Letter(word.charAt(i));
        }
        
        //check if the file exsists
        
        if(file.exists()){
            load();
        }else{
            save();
        }
        
        save();
    }
    
    public String getWord(){
        return word;
    }
    
    public Letter[] getLetters(){
        return letters;
    }
    
    private void save(){
        Element elmWord = new Element("word");
        
        Element elmWORD = new Element("word");
        elmWORD.appendChild(word);
        elmWord.appendChild(elmWORD);
        
        for(int i = 0; i < word.length(); i++){
            Element elmChar = new Element("char");
            elmChar.appendChild(letters[i].getLetterStr());
            elmWord.appendChild(elmChar);
        }
        
        Element elmType = new Element("type");
        elmType.appendChild(Integer.toString(type));
        elmWord.appendChild(elmType);
        
        Element elmBefore = new Element("before");
        Attribute totalBefore = new Attribute("totalBefore", String.valueOf(wordsBefore.length));
        elmWord.appendChild(elmBefore);
        
        Element elmWordBefore = new Element("wordBefore");
        
        for(int i = 0, i < wordsBefore.length; i++){
            
        }
        
        Document doc = new Document(elmWord);
        OutputStream output;
        
        try {
            output = new FileOutputStream(file);
            
            Serializer serializer;
            try {
                serializer = new Serializer(output, "ISO-8859-1");
                serializer.setIndent(4);
                serializer.setMaxLength(64);
                try { 
                    serializer.write(doc);
                } catch (IOException ex) {
                    Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("Saved");
    }
    
    private void load(){
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Xml xmlReader = new Xml(fileInput, "word");
    }
    
    public Word commonWordBefore(int i){
        return this; //temp
    }
    
    public Word commonWordAfter(int i){
        return this; //temp
    }
    
    public Word commonBefore(Word w){
        return this; //temp
    }
    
    public Word commonAfter(Word w){
        return this; //temp
    }
    
    public void addWordBefore(Word w){
        
    }
    
    public void addWordAfter(Word w){
        
    }
}
