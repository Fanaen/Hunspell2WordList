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
package fr.fanaen.wordlist;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import fr.fanaen.wordlist.model.Word;

/**
 *
 * @author Fanaen <contact@fanaen.fr>
 */
public class WordListCLI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean consoleOutput = true;
        String input = "";
        String output = "";
        WordListGeneratorListener listener = null;
        
        // First argument : file to read --
        if(args.length > 0) {
            input = args[0];
            
            // Second argument : file to print --
            if(args.length > 1) {
                output = args[1];
            }
        } 
        else {
            // Display a help message --
            System.out.println("Command help: hunspell2wordlist [InputFilePath] [OutputFilePath]");
        }
        
        // If the input file's name is incorrect --
        while(input.isEmpty()) {
            
            // Dictionary in console can't be used if we have to ask something --
            consoleOutput = false;
            
            // Ask the path --
            System.out.print("Enter the input file's path: ");
            Scanner in = new Scanner(System.in, "UTF-8");
            input = in.nextLine();
        }
        
        // If the dictionary should be printed in a file and the output info is empty --
        if(!consoleOutput) {
            while(output.isEmpty() || listener == null) {
                System.out.print("Enter the output file's path: ");
                Scanner in = new Scanner(System.in, "UTF-8");
                output = in.nextLine();
                
                try {
                    listener = createOutputToFileListener(output);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        // If the listener is not set --
        if(listener == null) {
            listener = createOutputToConsoleListener();
        }
        
        // Start the process --
        try {
            WordListGenerator generator = new WordListGenerator();
            
            generator.setFileName(input);
            generator.addListener(listener);
            generator.readFile();
            
            if(!consoleOutput) {
                generator.displayStatistics();
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }         
    }

    private static WordListGeneratorListener createOutputToFileListener(final String output) throws FileNotFoundException, UnsupportedEncodingException {
        return new WordListGeneratorListener() {
            PrintWriter writer = new PrintWriter(output, "UTF-8");
            
            @Override
            public void onNewWord(Word newWord) {
                writer.println(newWord.getContent());
            }

            @Override
            public void onGenerationEnd() {
                writer.close();
            }
        }; 
    }
    
    private static WordListGeneratorListener createOutputToConsoleListener() {
        return new WordListGeneratorListener() {
            @Override
            public void onNewWord(Word newWord) {
                System.out.println(newWord.getContent());
            }

            @Override
            public void onGenerationEnd() {
                // Nothing to do --
            }
        }; 
    }
    
}
