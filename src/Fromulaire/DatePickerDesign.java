/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fromulaire;


import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import raven.datetime.component.date.DatePicker;

public class DatePickerDesign {
    public static void styleDatePicker(DatePicker datePicker) {
        
        datePicker.setBackground(Color.WHITE);
        
        datePicker.setForeground(new Color(50, 50, 200)); 
        
        datePicker.setFont(new Font("SansSerif", Font.BOLD, 14)); 
       



datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.SINGLE_DATE_SELECTED);
datePicker.setSeparator("to date");







         // ... More styling options
    }
}
