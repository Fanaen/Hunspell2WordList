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
package wordlist;

import wordlist.parser.AffParser;
import wordlist.parser.DicParser;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class WordList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Initialisation --
        AffParser aff = new AffParser();
        DicParser dic = new DicParser();
        String fileName = "fr-moderne";
        
        // Process the reference file --
        aff.processFile("data/" + fileName + ".aff");
        
        // Transfer references --
        dic.setStorage(aff.getStorage());
        
        // Process the word file --
        dic.processFile("data/" + fileName + ".dic");
    }
    
}
