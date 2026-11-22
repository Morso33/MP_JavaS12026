import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A list of books recommended to a target audience. Cloning produces a deep copy. */
public class Recommendation implements Prototype<Recommendation> {
    private String targetAudience;
    private final List<Book> books = new ArrayList<>();

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    /** Deep copy: every Book is cloned, so the copy shares no mutable state with the original. */
    @Override
    public Recommendation clone() {
        Recommendation copy = new Recommendation(targetAudience);
        for (Book b : books) {
            copy.books.add(b.clone());
        }
        return copy;
    }

    public String getTargetAudience() { return targetAudience; }

    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }

    public void addBook(Book book) { books.add(book); }

    /** Removes the book at the given 0-based index; returns it, or null if the index is invalid. */
    public Book removeBook(int index) {
        if (index < 0 || index >= books.size()) return null;
        return books.remove(index);
    }

    public Book getBook(int index) {
        return (index < 0 || index >= books.size()) ? null : books.get(index);
    }

    /** Read-only view; changes must go through add/remove. */
    public List<Book> getBooks() { return Collections.unmodifiableList(books); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Recommendations for: " + targetAudience);
        if (books.isEmpty()) return sb.append("\n   (no books)").toString();
        for (int i = 0; i < books.size(); i++) {
            sb.append("\n  ").append(i + 1).append(". ").append(books.get(i));
        }
        return sb.toString();
    }
}
