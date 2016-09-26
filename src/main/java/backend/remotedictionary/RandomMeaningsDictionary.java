package backend.remotedictionary;

import java.util.Random;

public class RandomMeaningsDictionary implements RemoteDictionary {
    private final Random random = new Random();

    @Override
    public String lookupMeaning(String word) {
        final int hash = word.hashCode();
        return (hash % 10 == 0 ? "" : "Meaning of this word is " + random.nextInt(1000000));
    }

    @Override
    public String getDictionaryName() {
        return "Random Meanings Dictionary";
    }
}
