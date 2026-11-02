import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccessControlService {
    private static AccessControlService instance;

    // document id -> usernames allowed to read it
    private final Map<String, Set<String>> permissions = new HashMap<>();

    private AccessControlService() {
    }

    public static synchronized AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }

    public void grantAccess(String documentId, String username) {
        permissions.computeIfAbsent(documentId, id -> new HashSet<>()).add(username);
    }

    public void revokeAccess(String documentId, String username) {
        Set<String> users = permissions.get(documentId);
        if (users != null) {
            users.remove(username);
        }
    }

    public boolean isAllowed(String documentId, String username) {
        Set<String> users = permissions.get(documentId);
        return users != null && users.contains(username);
    }
}
