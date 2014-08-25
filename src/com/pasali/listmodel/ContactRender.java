package com.pasali.listmodel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
class ContactRenderer extends JLabel implements ListCellRenderer<Object>{
	  private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	  public ContactRenderer() {
	    setOpaque(true);
	    setIconTextGap(12);
	  }

	  public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {
		  Contact entry = (Contact) value;
	    setText(entry.getName());
	    setIcon(entry.getImage());
	    if (isSelected) {
	      setBackground(HIGHLIGHT_COLOR);
	      setForeground(Color.white);
	    } else {
	      setBackground(Color.white);
	      setForeground(Color.black);
	    }
	    return this;
	  }
	}
