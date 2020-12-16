package ua.gorbatov.library.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private Integer penalty;
    private List<Book> books;

    public Order(){}

    public Order(int id,LocalDate issueDate, LocalDate returnDate, boolean isReturned,
                 Integer penalty, List<Book> books) {
        this.id = id;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
        this.penalty = penalty;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", isReturned=" + isReturned +
                ", penalty=" + penalty +
                ", books=" + books +
                '}';
    }
}
