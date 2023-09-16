package tools;

import config.Configuration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;

public class TextPanel extends JPanel {
	
	private JTextPane textPane;

	
	private StyledDocument doc;
	private Style style;
	
	public TextPanel() {
		setPreferredSize(new Dimension(300, 300));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
//		text area
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		
//		styling
		
		//center text
		
//		SimpleAttributeSet center = new SimpleAttributeSet();
//		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
//		doc.setParagraphAttributes(0, doc.getLength(), center, true);
		
		// from: http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextPane/Create_JEditorPane_vertical_alignment.htm
		textPane.setEditorKit(new MyEditorKit());
		doc = textPane.getStyledDocument();
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		doc.setParagraphAttributes(0, doc.getLength() - 1, attrs, false);
		
//		font
		setJTextPaneFont(textPane, Configuration.getTextFont(14), Color.BLACK);
		
//		border
		Border outerBorder = BorderFactory.createEmptyBorder(0,0,0,0);
		setBorder(outerBorder);


		
		
		add(new JScrollPane(textPane), BorderLayout.CENTER);
	}
	
	public void displayResults(String results) {

		
		textPane.setText(results);
		
	}
	

	public int getLineCount() {
		return TextUtilities.getLines(textPane);
	}
	
	
	
// from http://javatechniques.com/blog/setting-jtextpane-font-and-color/
    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }
    
    
}




class MyEditorKit extends StyledEditorKit {

	  public ViewFactory getViewFactory() {
	    return new StyledViewFactory();
	  }

	  static class StyledViewFactory implements ViewFactory {

	    public View create(Element elem) {
	      String kind = elem.getName();
	      if (kind != null) {
	        if (kind.equals(AbstractDocument.ContentElementName)) {

	          return new LabelView(elem);
	        } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
	          return new ParagraphView(elem);
	        } else if (kind.equals(AbstractDocument.SectionElementName)) {

	          return new CenteredBoxView(elem, View.Y_AXIS);
	        } else if (kind.equals(StyleConstants.ComponentElementName)) {
	          return new ComponentView(elem);
	        } else if (kind.equals(StyleConstants.IconElementName)) {

	          return new IconView(elem);
	        }
	      }

	      return new LabelView(elem);
	    }

	  }
	}

class CenteredBoxView extends BoxView {
	  public CenteredBoxView(Element elem, int axis) {

	    super(elem, axis);
	  }

	  protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets,
	      int[] spans) {

	    super.layoutMajorAxis(targetSpan, axis, offsets, spans);
	    int textBlockHeight = 0;
	    int offset = 0;

	    for (int i = 0; i < spans.length; i++) {
	      textBlockHeight += spans[i];
	    }
	    offset = (targetSpan - textBlockHeight) / 2;
	    for (int i = 0; i < offsets.length; i++) {
	      offsets[i] += offset;
	    }

	  }
	}

