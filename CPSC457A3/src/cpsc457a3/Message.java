package cpsc457a3;

public class Message {
    private final String key;
    private final int value;
    private final int senderId;

    public Message(String key, int value, int senderId) {
        this.key = key;
        this.value = value;
        this.senderId = senderId;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public int getSenderId() {
        return senderId;
    }
}
