/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Loi Lam
 */
public class Books extends Author{
    
    private String bookID;
    private String title;
    private double price;

    public Books() {
    }

    public Books(String bookID, String title, double price, String authorID) {
        super(authorID);
         this.bookID = bookID;
        this.title = title;
        this.price = price;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String nameBook) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
