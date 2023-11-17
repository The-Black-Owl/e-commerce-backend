package api.backend.dto.checkout;

import lombok.Data;

@Data
public class StripeResponse {
    private String sessionID;

    public StripeResponse(String sessionID) {
        this.sessionID = sessionID;
    }
}
