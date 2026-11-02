import java.time.LocalDate;

public class DocumentProxy implements IDocument {
    private final Document document;

    DocumentProxy(Document document) {
        this.document = document;
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (!AccessControlService.getInstance().isAllowed(document.getId(), user.getUsername())) {
            throw new AccessDeniedException(user.getUsername(), document.getId());
        }
        return document.getContent(user);
    }
}
