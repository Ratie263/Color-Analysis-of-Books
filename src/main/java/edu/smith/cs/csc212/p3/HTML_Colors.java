package edu.smith.cs.csc212.p3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import me.jjfoley.gfx.GFX;

/**
 * Color names in colors.csv taken from
 * <a href="https://www.w3schools.com/colors/colors_names.asp">W3 Schools</a>
 * 
 * @author jfoley
 *
 */
public class HTML_Colors {
	/**
	 * Here we are going to be getting the colors of words from the csv file and saving it into a COLORS array
	 * This array will then be used to match up 'color' words to actual colors
	 */
	public Map<String, Color> COLORS = new HashMap<String, Color>();

	public HTML_Colors() {
		//This is the path to our book
		File path = new File("src/main/resources/colors.csv");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			// This will read through colors.csv and put all color names and their values into the COLORS Hashmap
			String line;
			while (true) {
				line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] pieces = line.split(",");
				int colorNumber = Integer.parseInt(pieces[1].substring(1), 16);
				
				this.COLORS.put(pieces[0].toLowerCase(), new Color(colorNumber));
				
			}
	
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find HTML Colors CSV.");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Couldn't read HTML Colors CSV.");
			System.exit(-1);
		}

	}

	public Color fromName(String name) {
		Color maybeFromTable = this.COLORS.get(name);
		if (maybeFromTable != null) {
			return maybeFromTable;
		}
		return maybeFromTable;
	}

}
