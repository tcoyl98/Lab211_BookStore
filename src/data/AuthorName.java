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
public class AuthorName extends Author{
    
    private String authorName;

    public AuthorName() {
    }

    public AuthorName(String authorName) {
        this.authorName = authorName;
    }

    public AuthorName(String authorName, String authorID) {
        super(authorID);
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    
}
