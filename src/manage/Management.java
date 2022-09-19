/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import data.AuthorName;
import data.Books;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import util.Filter;

/**
 *
 * @author Loi Lam
 */
public class Management {
    
    private ArrayList<AuthorName> author = new ArrayList<>();
    private ArrayList<Books> book = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);

    String t = String.format("|%20s|%20s|%12s|%20s|", "Book ID", "NAME BOOK", "PRICE", "AUTHOR NAME");
    
    public void add() {
        try {
            String bookID, title, autID;
            double price;
            FileReader f = new FileReader("book.txt");
            BufferedReader br = new BufferedReader(f);
            String line;
            StringTokenizer stk;
            while ((line=br.readLine())!= null) {
                stk = new StringTokenizer(line, ",");
                bookID = stk.nextToken();
                title = stk.nextToken();
                price = Double.parseDouble(stk.nextToken());
                autID = stk.nextToken();
                Books b = new Books(bookID, title, price, autID);
                book.add(b);
            }
        } catch (Exception e) {
        }
    }

    public void addAuthor() {
        try {
            String authorID, authorName;
            FileReader f = new FileReader("author.txt");
            BufferedReader br = new BufferedReader(f);
            String line;
            StringTokenizer stk;
            while ((line=br.readLine())!= null) {
                stk = new StringTokenizer(line, ";");
                authorID = stk.nextToken();
                authorName = stk.nextToken();
                AuthorName an = new AuthorName(authorName, authorID);
                author.add(an);
            }
        } catch (Exception e) {
        }
    }
    
    public String subIDToName(String sub) {
        String subName = null;
        for (int i = 0; i < author.size(); i++) {
            if(author.get(i).getAuthorID().equalsIgnoreCase(sub)) {
                subName = author.get(i).getAuthorName();
                return author.get(i).getAuthorName();
            }
        }
        return subName;
    }
    
    public void showBookList() {
        System.out.println(t);
        for (Books b : book) {
            String msg = String.format("|%20s|%20s|%12.2f|%20s|", b.getBookID(), b.getTitle(), 
                    b.getPrice(), subIDToName(b.getAuthorID()));
            System.out.println(msg);
        }
    }
    
    public void showBook(String bookID) {
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID().equalsIgnoreCase(bookID)) {
                String msg = String.format("|%20s|%20s|%12.2f|%20s|", book.get(i).getBookID(), book.get(i).getTitle(), 
                        book.get(i).getPrice(), subIDToName(book.get(i).getAuthorID()));
                System.out.println(msg);
            }
        }
    }
    
    public void addBook() {
        String isbn, title, autID;
        double price;
        int posISBN;
        int posID;
        do {            
            isbn = Filter.getString("Input the Book ID: ", "Book ID cannot blank!!");
            posISBN = checkDuplicate(isbn);
            if(posISBN >= 0)
                System.out.println("The Book ID really exists.");
        } while (posISBN != -1);
        title = Filter.getString("Input the title: ", "Title cannot blank!!");
        price = Filter.getDouble("Input the Price: ", "Price upper 10 and lower 100", 10, 100);
        do {            
            autID = Filter.getString("Input the Author ID: ", "Author ID cannot blank!!");
            posID = checkAutID(autID);
            if(posID == -1)
                System.out.println("The Author ID not exists!!!");
        } while (posID < 0);
        book.add(new Books(isbn, title, price, autID));
    }
    
    public void addNewBook() {
        boolean check = true;
        while(check) {
            addBook();
            check = Filter.checkYesNo("Are you want continue?", "Input Y for continue or N for stop add Book:");
        }
    }
    
    public int checkDuplicate(String bookID) {
        if(book.isEmpty())
            return -1;
        for (int i = 0; i < book.size(); i++)
            if(book.get(i).getBookID().equalsIgnoreCase(bookID))
                return i;
        return -1;
    }
    
    public int checkAutID(String autID) {
        if(book.isEmpty())
            return -1;
        for (int i = 0; i < author.size(); i++) {
            if(author.get(i).getAuthorID().equalsIgnoreCase(autID))
                return i;
        }
        return -1;
    }
    
    public void updateBook() {
        String isbn, title, autID;
        double price;
        int posID;
        Books b;
        isbn = Filter.getString("Input the Book ID: ", "Book ID cannot blank!!");
        b = searchBookByBookID(isbn);
        if(b == null)
            System.out.println("Not found!!!");
        else {
            System.out.println("Here is the book before update:");
            System.out.println(t);
            showBook(isbn);
            System.out.println("Input the new Title");
            title = sc.nextLine();
            if(title.equals("")) {
                b.getTitle();
                System.out.println(b.getTitle());
            } else {
                b.setTitle(title);
            }
            System.out.println("Input the new Price");
            price = Filter.getDouble("Input the Price: ", "Price upper 10 and lower 100", 10, 100);
            if(price == 0) {
                b.getPrice();
                System.out.println(b.getPrice());
            }
            else {
                b.setPrice(price);
            }
            System.out.println("Input the New Author ID");
            autID = sc.nextLine();
            if(autID.equals("")){
                b.getAuthorID();
                System.out.println(b.getAuthorID());
            } else {
                do {            
                    posID = checkAutID(autID);
                    if(posID == -1)
                    System.out.println("The Author ID not exists!!!");
                } while (posID < 0);
                b.setAuthorID(autID);
            }
            System.out.println("The information of Book after updated:");
            System.out.println(t);
            showBook(isbn);
        }
    }
    
    public Books searchBookByBookID(String bookID) {
        if(book.isEmpty())
            return null;
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID().equalsIgnoreCase(bookID))
                return book.get(i);
        }
        return null;
    }
    
    public void deleteBook() {
        String bookID;
        int pos;
        boolean check = false;
        Books b;
        bookID = Filter.getString("Input the bookID: ", "bookID cannot blank!!");
        pos = checkDuplicate(bookID);
        b = searchBookByBookID(bookID);
        if(pos == -1)
            System.out.println("Not found!!");
        else {
            System.out.println(t);
            showBook(bookID);
            check = Filter.checkYesNo("Are you want to delete this Book?", "Input Y for continue or N for stop remove this Book:");
            if(check) {
                book.remove(b);
                System.out.println("Book had been removed!!!");
            }
        }
        
    }
    
    public Books searchBookByTitle(String title) {
        if(book.isEmpty())
            return null;
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID().contains(title))
                return book.get(i);
        }
        return null;
    }
    
    public void showBookByTitle(String title) {
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getTitle().equalsIgnoreCase(title)) {
                String msg = String.format("|%20s|%20s|%12.2f|%20s|", book.get(i).getBookID(), book.get(i).getTitle(), 
                        book.get(i).getPrice(), subIDToName(book.get(i).getAuthorID()));
                System.out.println(msg);
            }
        }
    }
    
    public void showBookByPrice(double p) {
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getPrice() >= p) {
                String msg = String.format("|%20s|%20s|%12.2f|%20s|", book.get(i).getBookID(), book.get(i).getTitle(), 
                        book.get(i).getPrice(), subIDToName(book.get(i).getAuthorID()));
                System.out.println(msg);
            }
        }
    }
    
    public void searchBook() {
        int i = 0;
        String title;
        Books b;
//        title = Filter.getString("Input the title: ", "Title cannot blank!!");
        double p;
        System.out.println("find book with price upper:");
        p = Filter.getDouble("Input the Price: ", "Price upper 10 and lower 100", 10, 100);
        System.out.println(t);
        for (Books o : book) {
//            if(o.getPrice() >= p) {
////                b = searchBookByTitle(title);
////                showBookByTitle(o.getTitle());
//                i++;
//            }
            if(o.getPrice() >= p) {
                String msg = String.format("|%20s|%20s|%12.2f|%20s|", o.getBookID(), o.getTitle(), 
                        o.getPrice(), subIDToName(o.getAuthorID()));
                System.out.println(msg);
                i++;
            }
        }
        if( i == 0)
            System.out.println("Not found!!");
    }
    
    public void storeBook() {
        try {
            File f = new File("book.txt");
            PrintWriter pw = new PrintWriter(f); 
            for (Books b : book) {
                String s = b.getBookID()+ "," +
                        b.getTitle()+","+
                        b.getPrice()+","+
                        b.getAuthorID();
                pw.println(s);
            }
            pw.close();
        } catch (Exception e) {
        }
        System.out.println("\nSaved to file.");
    }
}
