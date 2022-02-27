package com.example.booklendingdeliveryapp;

public class Member {

    String title,image,author,publisher;
//,isbn

    public Member() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   public String getPublisher() {
       return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
//
//   public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
