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
package fr.fanaen.wordlist.model;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class AffixOption {
    
    // -- Attributes --
    private boolean prefix = true;
    private final String toStrip;
    private final String affix; 
    private final String newAffixes; 
    private final String conditions;
    private final String identifiers;
    
    // -- Constructors --

    public AffixOption(String stripping, String affix, String conditions, String identifiers) {
        this.toStrip = stripping;
        this.conditions = conditions;
        this.identifiers = identifiers;
        
        String[] affixSplit = affix.split("/");
        this.affix = affixSplit[0];
        this.newAffixes = affixSplit.length > 1 ? affixSplit[1] : "";
    }
    
    // -- Methods --
    
    public boolean isApplyable(Word word) {
        boolean applyable = false;
        if(conditions.equals(".")) applyable = true;
        
        if(prefix) {
            applyable = word.getContent().matches(conditions +".*");
        }
        else {
            applyable = word.getContent().matches(".*" + conditions);
        }
        
        //System.out.print("  * " + applyable + " for " + word.getContent() + " ");
        //display();
        
        return applyable;
    }
    
    public Word apply(Word word) {
        String content = word.getContent();
        
        //System.out.print("  * Apply " + (prefix ? "prefix" : "suffix") + " to " + content + " ");
        //display();
        
        if(prefix) {
            content = getAffix() + content.substring(getStripping().length());
        }
        else {
            content = content.substring(0, content.length() - getStripping().length()) + getAffix();
        }
        word.setContent(content);
        
        word.setAffixes(newAffixes);
        
        return word;
    }
    
    public void display() {
        System.out.println("(" + toStrip + ", " + affix + ", " + conditions + ", " + identifiers +")");
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

    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }
    
    
    
    
}
