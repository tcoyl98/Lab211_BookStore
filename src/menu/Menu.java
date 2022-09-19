/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import manage.Management;
import ui.OptionMenu;

/**
 *
 * @author Loi Lam
 */
public class Menu {
    
    public static void main(String[] args) {
        
        OptionMenu optionMenu = new OptionMenu("HKT Book Store Management");
        optionMenu.addNewOption("1. Show the book list");
        optionMenu.addNewOption("2. Add new book");
        optionMenu.addNewOption("3. Update book");
        optionMenu.addNewOption("4. Delete book");
        optionMenu.addNewOption("5. Search book");
        optionMenu.addNewOption("6. Search book");
        optionMenu.addNewOption("Other - Quit");
        
        Management manage = new Management();
        
        manage.add();
        manage.addAuthor();
        
        int choice;

        do {
            optionMenu.printMenu();
            choice = optionMenu.getChoice();
            switch (choice) {
                case 1:
                        manage.showBookList();
                        break;
                case 2:
                        manage.addNewBook();
                        break;
                case 3:
                        manage.updateBook();
                        break;
                case 4:
                        manage.deleteBook();
                        break;
                case 5:
                        manage.searchBook();
                        break;
                case 6:
                        manage.storeBook();
                        break;
            }
        } while (choice != 7);
    }
    
}
