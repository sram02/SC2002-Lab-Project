package hospital;

public class ReplenishmentRequest {
    private String medicineName;
    private int requestedQuantity;
    private Pharmacist requester;

    public ReplenishmentRequest(String medicineName, int requestedQuantity, Pharmacist requester) {
        this.medicineName = medicineName;
        this.requestedQuantity = requestedQuantity;
        this.requester = requester;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public Pharmacist getRequester() {
        return requester;
    }
}
