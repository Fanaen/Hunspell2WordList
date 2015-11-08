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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class Affix {
    
    // -- Attributes --
    protected List<AffixOption> optionList;
    protected boolean prefix;
    
    // -- Constructors --

    public Affix(boolean prefix, int nbItems) {
        optionList = new ArrayList<>(nbItems);
        this.prefix = prefix;
    }
    
    // -- Methods --

    public void addOption(AffixOption option) {
        optionList.add(option);
    }
    
    public int getNbOptions() {
        return optionList.size();
    }
    
    
    // -- Getters & Setters --

    public List<String> apply(String word, String identifier) {
        List<String> list = new LinkedList<>();
        
        for (AffixOption option : optionList) {
            if(prefix) {
                if(option.isPrefixApplyable(word, identifier))
                    list.add(option.applyPrefix(word, identifier));
            }
            else {
                if(option.isSuffixApplyable(word, identifier))
                    list.add(option.applySuffix(word, identifier));
            }            
        }
        
        return list;
    }
}
