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
public class CPFFormatter extends DocumentFilter {

    public CPFFormatter() {

    }

   @Override  
   public void insertString(FilterBypass fb, int offset, String text,  
         AttributeSet attr) throws BadLocationException {  
      fb.insertString(offset, text.replaceAll(text, ""), attr);  
   }  
  
   @Override  
   public void replace(FilterBypass fb, int offset, int length, String text,  
         AttributeSet attrs) throws BadLocationException {  
      if (text.matches("[0-9]*+") && offset<14)  {
         switch (offset) {
           case 2: fb.replace(offset, length, text+".", attrs);
               return;
           case 6: fb.replace(offset, length, text+".", attrs);
               return;
           case 10: fb.replace(offset, length, text+"-", attrs);
               return;
           default : fb.replace(offset, length, text, attrs);
         }
      }
      else {
         fb.replace(offset, length, "", attrs);
      }

      
   }
}