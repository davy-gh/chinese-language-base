package cz.magix.chibot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cz.magix.chibot.constants.IndexName;
import cz.magix.chibot.model.Char;
import cz.magix.chibot.model.Meaning;
import cz.magix.chibot.model.Word;
import cz.magix.chibot.model.relation.HasMeaning;
import cz.magix.chibot.model.relation.IsPartOf;

@Component
public class ChiBotBean {
	@Autowired
	private Neo4jTemplate neo4j;

	@PostConstruct
	public void init() {
		findSubWords();
	}

	private void findSubWords() {
		EndResult<Word> result = neo4j.findAll(Word.class);
		Iterator<Word> wordIterator = result.iterator();

		int counter = 0;
		while (wordIterator.hasNext()) {
			Word word = wordIterator.next();

			if (word == null) {
				System.out.println("Hey, je to null");
			}

			String w = word.getSimplified();

			if (w != null) {
				System.out.println("Original word: " + w);
				for (int i = 0; i < w.length(); i++) {
					for (int j = i + 1; j <= w.length(); j++) {
						String wordToFind = w.substring(i, j);

						if (wordToFind.length() > 1 && wordToFind.length() != w.length()) {
							Index<Node> simplifiedIndex = neo4j.getIndex(Word.class, "WORD");
							Node node = simplifiedIndex.get("simplified", wordToFind).getSingle();

							if (node != null ) {
								System.out.println("Word " + w + " is consists of word: " + wordToFind);
							} else {
								System.out.println("Word " + w+ " is NOT consists of anything");
							}
							
						}
					}
				}
				System.out.println();
			}

			counter++;
			if (counter > 100) {
				break;
			}
		}
	}

	@Transactional
	private void initDictionary() {
		// Read the file
		try {
			BufferedReader br = new BufferedReader(new FileReader("/tmp/cedict_ts.u8"));

			String line = null;
			int i = 0;
			while (br.ready()) {
				line = br.readLine();

				// Comments
				if (line.trim().charAt(0) == '#') {
					continue;
				}

				// Character part
				String[] split1 = line.trim().split("\\[");
				String charsBigPart = split1[0];
				String[] charsPart = charsBigPart.trim().split(" ");
				String traditionalWord = charsPart[0];
				String simplifiedWord = charsPart[1];

				// Pronunciation part
				String[] split2 = split1[1].trim().split("\\]");
				String pronunciationPart = split2[0];
				String[] pronunciationSylabs = pronunciationPart.trim().split(" ");

				// Meaning part //TODO: muze se to chovat podivne
				String meaningPart = split2[1].trim();

				System.out.println("meaningPart1: " + meaningPart);
				if (meaningPart.charAt(0) == '/') {
					meaningPart = meaningPart.substring(1);
				}
				System.out.println("meaningPart2: " + meaningPart);

				if (meaningPart.length() > 0 && meaningPart.charAt(meaningPart.length() - 1) == '/') {
					meaningPart = meaningPart.substring(0, meaningPart.length() - 1);
				}
				System.out.println("meaningPart3: " + meaningPart);

				// Split
				String[] meanings;
				if (meaningPart.contains("/")) {
					meanings = meaningPart.trim().split("/");
				} else {
					meanings = new String[] { meaningPart };
				}

				// Print out
				System.out.println("Record traditional: " + traditionalWord + ", simplified: " + simplifiedWord + ", pronunciationPart: " + pronunciationPart);
				for (String m : meanings) {
					System.out.println("  meaningPart: " + m);
				}

				System.out.println();

				// TODO: write character

				if (traditionalWord.length() == simplifiedWord.length() && simplifiedWord.length() == pronunciationSylabs.length) {
					Transaction tx = neo4j.getGraphDatabase().beginTx();
					try {

						// Word first
						if (!exist(IndexName.SIMPLIFIED_WORD, Word.SIMPLIFIED, simplifiedWord)) {
							Word word = neo4j.save(new Word(traditionalWord, simplifiedWord));

							for (String meaningString : meanings) {
								if (!exist(IndexName.MEANING, Meaning.MEANING, meaningString)) {
									Meaning meaning = neo4j.save(new Meaning(meaningString));

									// Create relationship
									neo4j.createRelationshipBetween(word, meaning, HasMeaning.class, HasMeaning.HAS_MEANING, false);
								} else {
									// TODO: zkontrolovat, jestli uz
									// vazba neexistuje a potom zalozit
								}
							}

							for (int j = 0; j < simplifiedWord.length(); j++) {
								if (!exist(IndexName.SIMPLIFIED_CHARACTER, Char.SIMPLIFIED, simplifiedWord.charAt(j))) {
									Char ch = neo4j.save(new Char(traditionalWord.charAt(j), simplifiedWord.charAt(j), pronunciationSylabs[j]));

									// Create relationship with word
									neo4j.createRelationshipBetween(ch, word, IsPartOf.class, IsPartOf.IS_PART_OF, false);

								} else {
									System.out.println("Char " + simplifiedWord.charAt(j) + " already exists in the database");
								}
							}
						} else {
							System.out.println("Word already exists in dictionary");
						}

						tx.success();
					} finally {
						tx.finish();
					}
				} else {
					System.out.println("Error in dictionary");
					continue;
				}

				// Rest of the loop
				i++;
				// if (i > 100) {
				// break;
				// }
			}

			System.out.println("Records processed: " + i);

			br.close();

			// End Test
			// EndResult<Char> result = neo4j.findAll(Char.class);
			// Iterator<Char> it = result.iterator();
			// while (it.hasNext()) {
			// System.out.println("Record: " + it.next().toString());
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO: doc it
	 * 
	 * @param indexName
	 * @param object
	 * @return
	 */
	private boolean exist(String indexName, String fieldName, Object object) {
		Index<Node> simplifiedIndex = neo4j.getIndex(Char.class, indexName);
		Node node = simplifiedIndex.get(fieldName, object).getSingle();

		if (node != null) {
			return true;
		} else {
			return false;
		}
	}
}
