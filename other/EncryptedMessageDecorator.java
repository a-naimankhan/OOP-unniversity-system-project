package other;

// Decorator — encrypts message body with Caesar cipher (shift=3) for confidential messages
public class EncryptedMessageDecorator extends MessageDecorator {

    private static final int SHIFT = 3;

    public EncryptedMessageDecorator(IMessage message) {
        super(message);
    }

    // returns cipher-text instead of plain text
    @Override
    public String getText() {
        return encrypt(wrapped.getText());
    }

    // shows encrypted content with header
    @Override
    public String getFormattedContent() {
        return "[ENCRYPTED] From: " + wrapped.getSender().getFullName()
            + "\nSubject: " + wrapped.getSubject()
            + "\nContent: " + encrypt(wrapped.getText());
    }

    // simple Caesar cipher applied to alphabetical characters only
    private String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char) ((c - base + SHIFT) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
