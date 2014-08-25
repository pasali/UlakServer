package com.pasali.listmodel;

import javax.swing.ImageIcon;

class Contact {
	  private final String name,id;

	  private final String imagePath = "contact.png";

	  private ImageIcon image;

	  public Contact(String name, String id) {
	    this.name = name;
	    this.id = id;
	  }
	  
	  public String getId() {
		  return id;
	  }
	  public String getName() {
	    return name;
	  }

	  public ImageIcon getImage() {
	    if (image == null) {
	      image = new ImageIcon(imagePath);
	    }
	    return image;
	  }

	}
