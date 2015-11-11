/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Lifaen
 */
public class NoFileSetException extends Exception {
    
    public NoFileSetException() {
        super("No file set for the WordListGenerator.");
    }
}
