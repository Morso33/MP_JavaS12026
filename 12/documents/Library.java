import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private final Map<String, IDocument> documents = new HashMap<>();

    public IDocument addDocument(String id, LocalDate creationDate, String content) {
        return store(new Document(id, creationDate, content));
    }

    // Factory method: the real document is only reachable through the proxy
    public IDocument addProtectedDocument(String id, LocalDate creationDate, String content, User... allowedUsers) {
        IDocument proxy = store(new DocumentProxy(new Document(id, creationDate, content)));
        AccessControlService acl = AccessControlService.getInstance();
        for (User user : allowedUsers) {
            acl.grantAccess(id, user.getUsername());
        }
        return proxy;
    }

    public IDocument getDocument(String id) {
        return documents.get(id);
    }

    public Collection<IDocument> getDocuments() {
        return documents.values();
    }

    private IDocument store(IDocument document) {
        if (documents.containsKey(document.getId())) {
            throw new IllegalArgumentException("Document '" + document.getId() + "' already exists");
        }
        documents.put(document.getId(), document);
        return document;
    }
}
