package other;

// Decorator — marks message as urgent by prepending "[URGENT]" to subject and content
public class UrgentMessageDecorator extends MessageDecorator {

    public UrgentMessageDecorator(IMessage message) {
        super(message);
    }

    // prefixes subject with urgency tag
    @Override
    public String getSubject() {
        return "[URGENT] " + wrapped.getSubject();
    }

    // wraps content with urgency banner
    @Override
    public String getFormattedContent() {
        return "!!! URGENT MESSAGE !!!\n" + wrapped.getFormattedContent();
    }
}
