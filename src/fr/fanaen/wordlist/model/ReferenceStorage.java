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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class ReferenceStorage {
    
    // -- Attributes --
    protected List<String> AMArray;
    protected List<String> AFArray;
    protected Map<String, Affix> affixArray;
    
    // -- Constructors --
    public ReferenceStorage() {
        AMArray = new ArrayList();
        AFArray = new ArrayList();
        affixArray = new HashMap<>();
    }
    
    // -- Methods --

    public void initAM(int nbItems) {
        AMArray = new ArrayList(nbItems);
    }
    
    public void addAM(String items) {
        AMArray.add(items);
    }
    
    public void initAF(int nbItems) {
        AFArray = new ArrayList(nbItems);
    }
    
    public void addAF(String items) {
        AFArray.add(items);
    }
    
    public void initAffix(String id, int nbItems, boolean prefix) {
        affixArray.put(id, new Affix(prefix, nbItems));
    }

    public void displayStatistics() {
        System.out.println("References stats:");
        System.out.println(" * " + AMArray.size() + " AM references");
        System.out.println(" * " + AFArray.size() + " AF references");
        System.out.println(" * " + affixArray.size() + " affixes");
        
        int count = 0;
        for (Entry<String,Affix> entry : affixArray.entrySet()) {
            count += entry.getValue().getNbOptions();
        }
        System.out.println(" * " + count + " affix options");
    }
    
    public List<Affix> getAffixes(String affixes) {
        List<Affix> list = new LinkedList<>();
        
        for (Entry<String,Affix> entry : affixArray.entrySet()) {
            if(affixes.contains(entry.getKey())) {
                list.add(entry.getValue());
            }
        }
        
        return list;
    }
    
    // -- Getters & Setters --

    public String getAM(int reference) {
        return AMArray.get(reference - 1);
    }
    
    public String getAF(int reference) {
        return AFArray.get(reference - 1);
    }
    
    public Affix getAffix(String id) {
        return affixArray.get(id);
    }
    
    public boolean isReady() {
        return true;
    }
}
