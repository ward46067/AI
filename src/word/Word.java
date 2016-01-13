package word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.*;

public class Word {
    private String word;
    private Letter[] letters;
    private int type;
    private File file;
    
    private ArrayList wordsBefore = new ArrayList();
    private ArrayList wordsAfter = new ArrayList();
    private ArrayList timesBefore = new ArrayList();
    private ArrayList timesAfter = new ArrayList();
    private ArrayList synonym = new ArrayList();
    
    public Word(String w){
        word = w.toLowerCase();
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
    
    public String getCapitalized(){
        String s = word.substring(0, 1).toUpperCase();
        s += word.substring(1, word.length());
        return s;
    }
    
    private void save(){
        //file.delete();
        
        Element elmWord = new Element("word");
        
        Element elmWORD = new Element("word");
        elmWORD.appendChild(word);
        elmWord.appendChild(elmWORD);
        
        for(int i = 0; i < word.length(); i++){
            Element elmChar = new Element("char");
            elmChar.appendChild(letters[i].getLetterStr());
            elmWord.appendChild(elmChar);
        }
        
        //word type
        Element elmType = new Element("type");
        elmType.appendChild(Integer.toString(type));
        elmWord.appendChild(elmType);
        
        //words before
        Element elmBefore = new Element("before");
        Attribute totalBefore = new Attribute("totalBefore", String.valueOf(wordsBefore.size()));
        elmBefore.addAttribute(totalBefore);
        elmWord.appendChild(elmBefore);
        
        
        
        for(int i = 0; i < wordsBefore.size(); i++){
            Element elmWordBefore = new Element("wordBefore" + i);
            elmWordBefore.appendChild((String) wordsBefore.get(i));
            Attribute occurence = new Attribute("occur", String.valueOf(timesBefore.get(i)));
            elmWordBefore.addAttribute(occurence);
            elmBefore.appendChild(elmWordBefore);
        }
        
        //words after
        Element elmAfter = new Element("after");
        Attribute totalAfter = new Attribute("totalAfter", String.valueOf(wordsAfter.size()));
        elmAfter.addAttribute(totalAfter);
        elmWord.appendChild(elmAfter);
        
        for(int i = 0; i < wordsAfter.size(); i++){
            Element elmWordAfter = new Element("wordAfter" + i);
            elmWordAfter.appendChild((String) wordsAfter.get(i));
            Attribute occurence = new Attribute("occur", String.valueOf(timesAfter.get(i)));
            elmWordAfter.addAttribute(occurence);
            elmAfter.appendChild(elmWordAfter);
        }
        
        //make doc
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
        
        //System.out.println("Saved");
    }
    
    private void load(){
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Xml wordXML = new Xml(fileInput, "word");
        
        int wb = wordXML.child("before").integer("totalBefore");
        int wa = wordXML.child("after").integer("totalAfter");
        
        for(int i = 0; i < wb; i++){
            wordsBefore.add(i, wordXML.child("before").child("wordBefore" + i).content());
            timesBefore.add(i, wordXML.child("before").child("wordBefore" + i).integer("occur"));
            
        }
        
        for(int i = 0; i < wa; i++){
            wordsAfter.add(i, wordXML.child("after").child("wordAfter" + i).content());
            timesAfter.add(i, wordXML.child("after").child("wordAfter" + i).integer("occur"));
            System.out.println();
        }
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
        String s = w.getWord();
        s = s.toUpperCase();
        int i = wordsBefore.lastIndexOf(s);
        
        if(i != -1){
            int o = Integer.parseInt(String.valueOf(timesBefore.get(i)));
            o++;
            timesBefore.remove(i);
            timesBefore.add(i, o);
        } else {
            wordsBefore.add(s);
            timesBefore.add(1);
        }
        //System.out.println("Added word " + s.toLowerCase() + ".");
        save();
    }
    
    public void addWordAfter(Word w){
        String s = w.getWord();
        s = s.toUpperCase();
        int i = wordsAfter.lastIndexOf(s);
        
        if(i != -1){
            int o = Integer.parseInt(String.valueOf(timesAfter.get(i)));
            o++;
            timesAfter.remove(i);
            timesAfter.add(i, o);
        } else {
            wordsAfter.add(s);
            timesAfter.add(1);
        }
        //System.out.println("Added word " + s.toLowerCase() + ".");
        save();
    }
    
    public void setType(int t){
        type = t;
    }
}
