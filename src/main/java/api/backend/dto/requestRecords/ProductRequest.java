package api.backend.dto.requestRecords;

public record ProductRequest(
        String productName,
        Long SKU,
        String productDescription,
        String category,
        double price) {
}
