package me.petersoj.runner;


import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Runner {
	
	public static void main(String[] args) {
		Rune r = new Rune();
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getLocation().x - 350, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getLocation().y - 225);
		r.setSize(700, 450);
		r.setVisible(true);
	}

}
