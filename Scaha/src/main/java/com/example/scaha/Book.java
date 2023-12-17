package com.example.scaha;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Book {
    private String title;
    private String author;
    private String publicationDate;
    private String pageCount;
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getPageCount() {
        return pageCount;
    }
    private Book(String title, String author,String publicationDate, String pageCount) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
    }

    public String display() {
        String result = "Book Title: " + title + "\nAuthor: " + author + "\nDate of Publication: " + publicationDate + "\nPage Count: " + pageCount;
        return result;
    }

    public static class BookBuilder {
        private String title;
        private String author;
        private String publicationDate;
        private String pageCount;

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public BookBuilder setPublicationDate(String date) {
            this.publicationDate = date;
            return this;
        }

        public BookBuilder setPageCount(String pageCount) {
            this.pageCount = pageCount;
            return this;
        }


        public Book build() {
            if (title == null) {
                throw new IllegalStateException("Title are required");
            }

            return new Book(title, author,publicationDate, pageCount);
        }
    }

    static class BookIterator implements Iterator<Book> {
        private int currentIndex;
        private final List<Book> books;

        public BookIterator(List<Book> books) {
            this.books = books;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < books.size();
        }

        @Override
        public Book next() {
            return books.get(currentIndex++);
        }
    }

    // Iterable implementation
    static class BookIterable implements Iterable<Book> {
        private final List<Book> books;

        public BookIterable(List<Book> books) {
            this.books = books;
        }

        @Override
        public Iterator<Book> iterator() {
            return new BookIterator(books);
        }
    }
}

