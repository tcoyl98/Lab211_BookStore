/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import util.Filter;

/**
 *
 * @author Loi Lam
 */
public class OptionMenu {
    
    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();
    
    public OptionMenu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
    
    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }
    
    public void printMenu() {
        if(optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("\n=============");
        System.out.println("Welcome to " + menuTitle);
        for (String x : optionList) {
            System.out.println(x);
        }
    }
    
    public int getChoice() {
        int maxOption = optionList.size();
        String inputMsg = "Choose [1..." + maxOption +"]: " ;
        String errorMsg = "You are required to choose the option 1 .." + maxOption;
        return Filter.getAnInteger(inputMsg,errorMsg,1,maxOption);
    }
}
