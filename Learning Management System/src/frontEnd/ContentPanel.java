package frontEnd;

import javax.swing.JPanel;
/**
 * ContentPanel will be a panel that three main panels will inherit,
 * they will all redefine setEnabled
 * @author louis
 *
 */
public abstract class ContentPanel extends JPanel {
	abstract protected boolean setEnabled();
	
}
