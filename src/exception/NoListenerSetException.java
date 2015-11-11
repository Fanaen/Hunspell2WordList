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
public class NoListenerSetException extends Exception {
    
    public NoListenerSetException() {
        super("No listener set for the WordListGenerator.");
    }    
}
