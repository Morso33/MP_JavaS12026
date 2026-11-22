/**
 * A single book. Part of the Prototype pattern: it can copy itself.
 * Fields are all immutable (String / int), so a field-wise copy is a true deep copy.
 */
public class Book implements Prototype<Book> {
    private String author;
    private String title;
    private String genre;
    private int publicationYear;

    public Book(String author, String title, String genre, int publicationYear) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    /** Copy constructor used by clone(). */
    private Book(Book other) {
        this(other.author, other.title, other.genre, other.publicationYear);
    }

    @Override
    public Book clone() {
        return new Book(this);
    }

    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }

    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + " (" + genre + ", " + publicationYear + ")";
    }
}
