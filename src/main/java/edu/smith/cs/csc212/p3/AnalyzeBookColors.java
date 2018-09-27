package edu.smith.cs.csc212.p3;

import java.io.File;

/**
 * We're going to analyze the colors used in Project Gutenberg books.
 * When you switch the book, make sure to get the UTF-8 ".txt" file, not HTML or PDF.
 * 
 * @author jfoley
 *
 */
public class AnalyzeBookColors {
	/**
	 *  This is the book you want to analyze.
	 *  You should change this!
	 */
	public static final String bookPath = "src/main/resources/Frankenstein.txt";
	
	/**
	 * This is where you analyze the use of colors in your book.
	 * @param book - the file to process.
	 */
	public static void analyze(File book) {
		// Your logic goes here!
		
		// Open a WordCloud and view it?
		// WordCloud viewer = new WordCloud(data);
		// Set window title:
		// viewer.setTitle(book.getName());
		// Run the simulation:
		// viewer.start();
	}
	
	/**
	 * This is my entry-point. Do not change this.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// This logic is so that I can point your code to my own data while testing.
		String target;
		if (args.length == 0) {
			// But that usually it uses your book!
			target = AnalyzeBookColors.bookPath;
		} else {
			target = args[0];
		}
		
		// Make sure the file exists and is happy.
		File book = new File(target);
		if (!book.canRead()) {
			System.err.println("Book can not be read: "+book);
			System.exit(-1);
		}
		if (!book.exists()) {
			System.err.println("Book does not exist: "+book);
			System.exit(-2);
		}
		if (!book.isFile()) {
			System.err.println("Book is not a file: "+book);
			System.exit(-3);
		}
		
		// Call your code.
		analyze(book);
	}
}
