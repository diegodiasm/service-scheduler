/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

/**
 *
 * @author Diego Dias
 */
@SuppressWarnings("serial")
public class TimeFormatter extends DocumentFilter {

    private static String replacement;

    public TimeFormatter() {
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text,
            AttributeSet attr) throws BadLocationException {

        fb.insertString(offset, text.replaceAll(text, "00:00"), attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {


        if (text.matches("[0-9]*+") && fb.getDocument().getLength() < 5) {

            replacement = (offset == 0) ? text.replaceAll("[3-9]", "")
                    : (offset == 1) && (fb.getDocument().getText(0, 1).matches("2")) ? 
                       text.replaceAll("[4-9]", "").matches("[0-3]") ? text+":" : ""
                    : (offset == 1) && (!fb.getDocument().getText(0, 1).matches("2")) ? text + ":"
                    : (offset == 2) ? text.replaceAll(text, ":")
                    : (offset == 3) ?  text.replaceAll("[6-9]", "")
                    : text;

            fb.replace(offset, length, replacement, attrs);
        }
    }
}