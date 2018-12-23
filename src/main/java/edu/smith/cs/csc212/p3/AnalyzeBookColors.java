package edu.smith.cs.csc212.p3;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * We're going to analyze the colors used in Wildflowers which is a Project Gutenberg books. 
 * 
 * 
 *
 */
public class AnalyzeBookColors {
	/**
	 * This class finds my book and analyses it. counts is going to be the Hashmap
	 * that contains a color and how many times it occurs in our book
	 */

	public static final String bookPath = "src/main/resources/Wildflowers.txt";
	public static Map<String, Integer> counts = new HashMap<String, Integer>();

	/**
	 * This is where you analyze the use of colors in your book.
	 * 
	 * @param book - the file to process.
	 */
	public static void analyze(File book) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookPath));
			// this reads through the entire book
			String line;
			while (true) {
				line = reader.readLine();
				if (line == null) {
					break;
				}

				List<String> words = (WordSplitter.splitTextToWords(line));
				/**
				 * Here, we will check if each word in our book is a color If we see a color
				 * that we already have we will increase its count by one, but if we fins a
				 * color we havent seen already, we will enter it into countns
				 */
				for (String w : words) {
					if (WordCloud.colorMap.COLORS.containsKey(w)) {
						if (counts.containsKey(w)) {
							int before = counts.get(w);
							counts.put(w, before + 1);
						} else {
							counts.put(w, 1);
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find Wildflowers");
			System.exit(-1);
			;
		} catch (IOException e) {
			System.err.println("Couldnt read Wildflowers");
			System.exit(-1);
		}

		List<WordCount> best = new ArrayList<WordCount>();
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			best.add(new WordCount(entry.getKey(), entry.getValue()));
		}
		Collections.sort(best);
		Collections.reverse(best);
		for (WordCount these : best) {
			System.out.println(these);
		}

	}

// Created this so that I can sort it.
	static class WordCount implements Comparable<WordCount> {
		String word;
		int count;

		public WordCount(String w, int c) {
			this.count = c;
			this.word = w;
		}

		public int compareTo(WordCount o) {
			return Integer.compare(count, o.count);
		}

		public String toString() {
			return this.word + ":" + this.count;
		}

	}

	/**
	 * This is the entry-point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// This logic is points code to data while testing.
		String target;
		if (args.length == 0) {
			// But that usually it uses your book!
			target = AnalyzeBookColors.bookPath;
		} else {
			target = args[0];
		}

		// This makes sure the file exists and is happy.
		File book = new File(target);
		if (!book.canRead()) {
			System.err.println("Book can not be read: " + book);
			System.exit(-1);
		}
		if (!book.exists()) {
			System.err.println("Book does not exist: " + book);
			System.exit(-2);
		}
		if (!book.isFile()) {
			System.err.println("Book is not a file: " + book);
			System.exit(-3);
		}
		analyze(book);
		WordCloud viewer = new WordCloud(counts);
		// Setting window title:
		viewer.setTitle(book.getName());
		// Runs the simulation:
		viewer.start();

	}
}
