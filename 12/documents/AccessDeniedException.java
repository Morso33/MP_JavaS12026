public class AccessDeniedException extends Exception {
    public AccessDeniedException(String username, String documentId) {
        super("User '" + username + "' is not allowed to access document '" + documentId + "'");
    }
}
