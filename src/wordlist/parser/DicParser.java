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
package wordlist.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wordlist.model.Affix;
import wordlist.model.Word;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class DicParser extends Parser {
    
    // -- Attributes --
    protected Pattern p;
    protected Matcher m;
    protected long lineCount = 0;
    protected long wordCount = 0;
    
    // -- Constructors --
    
    public DicParser() {
        p = Pattern.compile("^([^/\\t]+)/?(\\S*)\\t(.*)$");
    }
    
    // -- Methods --
    
    @Override
    protected void processLine(String line) {
        m = p.matcher(line.trim());
        
        if(m.matches()) {
            Word word = new Word(m.group(1), m.group(2),  m.group(3));
            
            word.processIdentifiers(storage);
            word.processAffixes(storage);
            
            //System.out.print("> ");
            //word.display();
            List<Word> words = word.processWords(storage);
            for (Word w : words) {
                //System.out.print("  * ");
                //w.display();
                wordCount++;
            }
            lineCount++;
        }   
    }
    
    public void displayCount() {
        System.out.println("DicParser stats:");
        System.out.println(" * " + wordCount + " words");
        System.out.println(" * " + lineCount + " lines");
    }
    
    // -- Getters & Setters --
    
}
