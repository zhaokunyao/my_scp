import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {
    private String placeHolder;

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeHolder == null || placeHolder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.DARK_GRAY);
        g.drawString(placeHolder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top + 5);
    }

}
