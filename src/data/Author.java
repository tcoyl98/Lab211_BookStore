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
public abstract class Author implements Comparable<Author>{
    
    protected String authorID;

    public Author() {
    }

    public Author(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    @Override
    public int compareTo(Author that) {
        return this.authorID.compareToIgnoreCase(that.authorID);
    }
}
