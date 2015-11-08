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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class DicParser extends Parser {
    
    // -- Attributes --
    protected Pattern p;
    protected Matcher m;
    
    // -- Constructors --
    
    public DicParser() {
        p = Pattern.compile("^([^/\\t]+)/?([^\\t])\\t(.*)$");
    }
    
    // -- Methods --
    
    @Override
    protected void processLine(String line) {
        m = p.matcher(line.trim());
        
        if(m.matches()) {
            String word = m.group(1);
            String affixes = m.group(2);
            String identifier = m.group(3);
            
            identifier = processIdentifier(identifier);
            
            System.out.println("# " + word + ": " + affixes + " " + identifier);    
        }   
    }
    
    // -- Getters & Setters --

    private String processIdentifier(String identifier) {
        try {
            // Try to convert it to Int 
            int reference = Integer.parseInt(identifier);
            return storage.getAM(reference);
                        
        } catch(NumberFormatException ex) {
            // If not parsable, leave as is --
            return identifier;
        }        
    }
    
}
