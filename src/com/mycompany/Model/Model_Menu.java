//package com.mycompany.Model;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//
//public class Model_Menu {
//    private String icon;
//     private String name;
//     private MenuType type;
//
//     public static enum MenuType{
//         TITLE, MENU, EMPTY
//     }
//     public Model_Menu(String icon,String name,MenuType type){
//         this.icon=icon;
//         this.name=name;
//         this.type=type;
//     }
//     /*Getter of icon*/
//    public String getIcon() {
//        return icon;
//    }
//    /*Setter of icon */
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//    /*Getter of name*/
//    public String getName() {
//        return name;
//    }
//    /*Setter of name */
//    public void setName(String name) {
//        this.name = name;
//    }
//    /*Getter of type*/
//    public MenuType getType() {
//        return type;
//    }    
//    /*Setter of type */
//    public void setType(MenuType type) {
//        this.type = type;
//    }
//     public Icon toIcon() {
//      return new ImageIcon(getClass().getResource("C:\\\\Users\\\\univers\\\\OneDrive\\\\Documents\\\\NetBeansProjects\\\\Projet_Sante_fs\\\\Icons_Project\\\\"+icon+".png"));
//    }
//     
//}
