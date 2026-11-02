import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements FileSystemVisitor {
    private final Predicate<File> criterion;
    private final List<File> results = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion) {
        this.criterion = criterion;
    }

    @Override
    public void visit(File file) {
        if (criterion.test(file)) {
            results.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public List<File> getResults() {
        return results;
    }
}
