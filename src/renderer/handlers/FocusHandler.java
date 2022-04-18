package renderer.handlers;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusHandler implements FocusListener {
    public boolean isFocused;

    @Override
    public void focusGained(FocusEvent focusEvent) {
        isFocused = true;
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        isFocused = false;
    }
}
