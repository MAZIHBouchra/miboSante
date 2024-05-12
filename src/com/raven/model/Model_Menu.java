package com.raven.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.URL;

public class Model_Menu {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

    private String icon;
    private String name;
    private MenuType type;

    public Icon toIcon() {

        URL location = getClass().getResource("/com/raven/icon/" + icon + ".png");
        if (location != null) {
            ImageIcon icon = new ImageIcon(location);
            return icon;
        } else {
            System.err.println("Image not found: "+ "/com/raven/icon/" + icon + ".png");
            return null;
        }    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
