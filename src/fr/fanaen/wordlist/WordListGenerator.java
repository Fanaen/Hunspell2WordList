/* 
 * The MIT License
 *
 * Copyright 2015 Fanaen <contact@fanaen.fr>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.fanaen.wordlist;

import fr.fanaen.wordlist.exception.NoFileSetException;
import fr.fanaen.wordlist.exception.NoListenerSetException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import fr.fanaen.wordlist.model.ReferenceStorage;
import fr.fanaen.wordlist.model.Word;
import fr.fanaen.wordlist.parser.AffParser;
import fr.fanaen.wordlist.parser.DicParser;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class WordListGenerator implements WordListGeneratorListener {
    
    // -- Attributes --
    
    protected String fileName;
    protected List<WordListGeneratorListener> listenerList;
    
    protected AffParser affParser;
    protected DicParser dicParser;
    protected ReferenceStorage storage;
    
    protected long wordCount = 0;
    private boolean statisticsDisplay = false;

    // -- Constructors --
    
    public WordListGenerator() {
        this.fileName = "";
        this.listenerList = new LinkedList<>();
    }
    
    public WordListGenerator(String fileName) {
        this();
        this.fileName = fileName;
    }
    
    public WordListGenerator(String fileName, WordListGeneratorListener listener) throws Exception {
        this(fileName);
        addListener(listener);
    }
    
    // -- Methods --.
    
    public void addListener(WordListGeneratorListener listener) throws Exception {
        if(listener == null) throw new Exception("Listener cannot be null");
        listenerList.add(listener);
    }
    
    public void readFile(String fileName) throws NoFileSetException, NoListenerSetException, IOException {
        setFileName(fileName);
        readFile();
    }
    
    public void readFile() throws NoFileSetException, NoListenerSetException, IOException {
        // Check readiness --
        if(listenerList.isEmpty()) 
            throw new NoListenerSetException();
        else if(fileName.isEmpty())
            throw new NoFileSetException();
        
        // Initialisation --
        affParser = new AffParser();
        dicParser = new DicParser(this);
        
        // Process the reference file --
        affParser.processFile(fileName + ".aff");

        // Transfer references --
        storage = affParser.getStorage();
        dicParser.setStorage(storage);

        if(statisticsDisplay) // STATS --
            storage.displayStatistics();
        
        // Process the word file --
        dicParser.processFile(fileName + ".dic");
        
        // Throw end event --
        for (WordListGeneratorListener l : listenerList) {
            l.onGenerationEnd();
        }

        if(statisticsDisplay) { // STATS --
            dicParser.displayCount();

            System.out.println("WordListGenerator stats:");
            System.out.println(" * " + wordCount + " words");
        }
    }

    @Override
    public void onNewWord(Word newWord) {
        for (WordListGeneratorListener l : listenerList) {
            l.onNewWord(newWord);
        }
        wordCount++;
    }    

    @Override
    public void onGenerationEnd() {
        // Nothing to do, WordListGenerator is the only to throw this event --
    }
    
    // -- Getters & Setters --

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String newfileName) {
        fileName = newfileName;
    }

    public void setStatisticsDisplay(boolean statisticsDisplay) {
        this.statisticsDisplay = statisticsDisplay;
    }

    public boolean isStatisticsDisplay() {
        return statisticsDisplay;
    }
}
