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
package fr.fanaen.wordlist.parser;

import java.util.HashSet;
import java.util.Set;
import fr.fanaen.wordlist.model.Affix;
import fr.fanaen.wordlist.model.AffixOption;
import fr.fanaen.wordlist.model.ReferenceStorage;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class AffParser extends Parser {
        
    // -- Attributes --
    protected Set<String> flagSet;
    
    // -- Constructors --
    
    public AffParser() {
        storage = new ReferenceStorage();
        flagSet = new HashSet<>();
    }
    
    // -- Methods --
    
    @Override
    protected void processLine(String line, int lineNumber) {

        // Handle spaces and empty lines --
        line = line.trim();
        if(line.equals("")) return;
        
        // Split and analyse the flag --
        String[] seg = line.split(" ");
        String flag = seg[0];
        if(flagSet.contains(flag)) {
            processFlag(seg, line);
        }
        else {
            addFlag(seg, line);
        }
    }
    
    // -- Getters & Setters --

    private void processFlag(String[] seg, String line) {
        String flag = seg[0];
        
        switch(flag) {
            case "AM":
                storage.addAM(line.substring(3).trim());
                break;
            case "AF":
                storage.addAF(line.substring(3).trim());
                break;
            case "PFX":
            case "SFX":
                processAffixes(seg, line);
                break;
            default:
        }        
    }

    private void addFlag(String[] seg, String line) {
        String flag = seg[0];
        flagSet.add(flag);
        
        switch(flag) {
            case "AM":
                storage.initAM(Integer.parseInt(seg[1]));
                break;
            case "AF":
                storage.initAF(Integer.parseInt(seg[1]));
                break;
            case "PFX":
            case "SFX":
                processAffixes(seg, line);
                break;
            default:
                //System.out.println("Flag " + flag);
                
        }
    }

    private void processAffixes(String[] seg, String line) {
        // Preparation --
        if(seg[2].equals("N") || seg[2].equals("Y")) {

            String id = seg[1];
            int nbItems = Integer.parseInt(seg[3]);
            boolean prefix = seg[0].equals("PFX");

            storage.initAffix(id, nbItems, prefix);
        } 
        // New affixe --
        else {
            Affix affix = storage.getAffix(seg[1]);

            String stripping = seg[2];
            String affixStr = seg[3];
            String conditions = seg.length >= 5 ? seg[4] : "."; // Handle no conditions (always true)
            String identifiers = seg.length >= 6 ? seg[5] : "";

            affix.addOption(new AffixOption(stripping, affixStr, conditions, identifiers));
        }
    }
}
