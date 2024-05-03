/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormInformation;


import raven.datetime.component.date.DatePicker;

import java.awt.*;

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
