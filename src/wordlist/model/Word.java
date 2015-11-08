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
package wordlist.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class Word implements Cloneable {
    
    // -- Attributes --
    private String content;
    private String affixes; 
    private String identifiers;
    
    // -- Constructors --

    public Word(String content, String affixes, String identifiers) {
        this.content = content;
        this.affixes = affixes;
        this.identifiers = identifiers;
    }
    
    // -- Methods --
    
    public void processIdentifiers(ReferenceStorage storage) {
         try {
            // Try to convert it to Int 
            int reference = Integer.parseInt(identifiers);
            identifiers = storage.getAM(reference);
                        
        } catch(NumberFormatException ex) {
            // If not parsable, leave as is --
            identifiers = "#"+ identifiers;
        }
    }

    public void processAffixes(ReferenceStorage storage) {
        try {
            // Try to convert it to Int 
            int reference = Integer.parseInt(affixes);
            affixes = storage.getAF(reference);
                        
        } catch(NumberFormatException ex) {
            // If not parsable, leave as is --
            affixes = "#" + affixes;
        }
    }
    
    public List<Word> processWords(ReferenceStorage storage) {
        List<Word> result = new LinkedList<>();
        
        // Parse affixes --
        for (Affix a : storage.getAffixes(affixes)) {
            // Apply each affix (multiple options) --
            for (Word w : a.apply(this, storage)) {
                result.add(w);
            }
        }
        
        return result;
    }

    public void display() {
        System.out.println(content + ": " + affixes + " " + identifiers);
    }

    @Override
    protected Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException ex) {
            ex.printStackTrace(System.err);
        }
        return o;
    }    
    
    // -- Getters & Setters --

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAffixes() {
        return affixes;
    }

    public void setAffixes(String affixes) {
        this.affixes = affixes;
    }

    public String getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(String identifiers) {
        this.identifiers = identifiers;
    }
}
