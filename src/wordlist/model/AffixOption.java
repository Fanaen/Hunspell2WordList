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

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class AffixOption {
    
    // -- Attributes --
    private final String toStrip;
    private final String affix; 
    private final String conditions;
    private final String identifiers;
    
    // -- Constructors --

    public AffixOption(String stripping, String affix, String conditions, String identifiers) {
        this.toStrip = stripping;
        this.affix = affix; 
        this.conditions = conditions;
        this.identifiers = identifiers;
    }
    
    // -- Methods --
    
    public boolean isPrefixApplyable(String word, String identifier) {
        if(conditions.equals("0")) return true;
        //return true;
        return word.matches(conditions +".+");
    }

    public boolean isSuffixApplyable(String word, String identifier) {
        if(conditions.equals("0")) return true;
        //return true;
        return word.matches(".+" + conditions);
    }
    
    public String applyPrefix(String word, String identifier) {
        return getAffix() +  word.substring(getStripping().length()) + " <" + getStripping() + "> " + identifiers + " " + conditions + " " + word.matches(conditions +".+");
    }

    public String applySuffix(String word, String identifier) {
        return word.substring(0, word.length() - getStripping().length()) + getAffix() + " <" + getStripping() + "> " + identifiers + " " + conditions + " " + word.matches(".+" + conditions);
    }
    
    // -- Getters & Setters --

    public String getStripping() {
        if(toStrip.equals("0")) return "";
        return toStrip;
    }

    public String getAffix() {
        if(affix.equals("0")) return "";
        return affix;
    }

    public String getConditions() {
        return conditions;
    }

    public String getIdentifiers() {
        return identifiers;
    }
    
    
}
