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
import java.util.List;
import java.util.Set;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class ReferenceStorage {
    
    // -- Attributes --
    protected List<String> AMArray;
    protected List<String> AFArray;
    
    // -- Constructors --
    public ReferenceStorage() {
        AMArray = new ArrayList();
        AFArray = new ArrayList();
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
    
    public boolean isReady() {
        return true;
    }
    
    // -- Getters & Setters --

    public String getAM(int reference) {
        return AMArray.get(reference - 1);
    }
    
    public String getAF(int reference) {
        return AFArray.get(reference - 1);
    }
    
}
