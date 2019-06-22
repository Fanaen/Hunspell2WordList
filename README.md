# Hunspell2WordList

H2WL is a Java library/tool generating every possible word from Hunspell dictionaries.

Currently, the tool is designed to handle [the french Dictionary by Dicollecte.org](http://www.dicollecte.org/download.php?prj=fr) but it should handle other dictionaries as well.

## Use it as a tool
You can run it directly in the shell like this:
```
$ java -jar hunspell2wordlist
```

Or you can specify input and output files.
```
$ java -jar hunspell2wordlist data/dictionaryName outputFile.txt
```

## Use it as a library
First, add the Jar to the library path of your Java project.
Then, you should be able to use it like this:
```java
WordListGenerator generator = new WordListGenerator();
WordListGeneratorListener listener = new WordListGeneratorListener() {
  @Override
  public void onNewWord(Word newWord) {
    System.out.println(newWord.getContent());
  }

  @Override
  public void onGenerationEnd() { }
};

generator.setFileName("path/to/dictionary");
generator.addListener(listener);
generator.readFile();
generator.displayStatistics();
```

## Contributors
- Elouan Poupard-Cosquer aka Fanaen ([contact@fanaen.fr](mailto:contact@fanaen.fr))
