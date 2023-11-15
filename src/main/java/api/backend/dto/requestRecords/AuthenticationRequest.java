package api.backend.dto.requestRecords;

// this is used for field reception from client
public record AuthenticationRequest(
        String email
        ,char[] password) {
}
