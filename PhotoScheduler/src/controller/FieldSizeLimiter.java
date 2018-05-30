/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/** 
 *
 * @author Diego Dias
 */
@SuppressWarnings("serial")
public class FieldSizeLimiter extends PlainDocument {

    private int size;
    private String matching;
    private final boolean upperCase;

    /**
     *
     * @param size Describes the size of the field where this PlainDocument will
     * be used
     * @param matching Describes the pattern that each character must satisfy
     * @param upperCase If true the text will be replaced by its uppercase
     * version
     */
    public FieldSizeLimiter(int size, String matching, boolean upperCase) {
        this.size = size;
        this.matching = matching;
        this.upperCase = upperCase;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (offset < size) {
            if (str.matches(matching)) {
                if (upperCase) {
                    super.insertString(offset, str.toUpperCase(), attr);
                } else {
                    super.insertString(offset, str, attr);
                }
            }
        }
    }
}