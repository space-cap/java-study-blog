package pattern;

import java.util.Iterator;

class Book {
    private String name;
    public Book(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

class BookShelf implements Iterable<Book>{
    private Book[] books;
    private int last = 0;

    public BookShelf(int size) {
        books = new Book[size];
    }

    public void appendBook(Book book) {
        books[last++] = book;
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }
}

class BookShelfIterator implements Iterator<Book> {
    private BookShelf bookShelf;
    private int index = 0;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Book next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return bookShelf.getBookAt(index++);
    }
}


public class IteratorEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, IteratorEx1");

        BookShelf bookShelf = new BookShelf(10);
        for (int i = 0; i < 12; i++) {
            bookShelf.appendBook(new Book("Book" + i));
        }

        Iterator<Book> it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            System.out.println(book.getName());
        }

        System.out.println("===================================");



        for (Book book : bookShelf) {
            System.out.println(book.getName());
        }





    }
}
