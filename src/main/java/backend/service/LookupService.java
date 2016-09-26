package backend.service;

import backend.remotedictionary.RandomMeaningsDictionary;
import backend.remotedictionary.RemoteDictionary;
import backend.storage.PostgresStorage;
import backend.storage.SaveMode;
import backend.storage.Storage;

public class LookupService {

    private final Storage storage = new PostgresStorage();
    private final RemoteDictionary remoteDictionary = new RandomMeaningsDictionary();

    public String lookup(final String word) {
        // 1. check dictionary in db
        // 2. if not present - request from oxford dictionary api
        // 3. add to current dictionary

        String meaning = storage.lookupMeaning(word);
        if (meaning.isEmpty()) {
            meaning = remoteDictionary.lookupMeaning(word);
            if (meaning.isEmpty()) {
                return "Meaning wasn't found in the remote dictionary (" + remoteDictionary.getDictionaryName() + ")";
            }
            storage.saveWord(word, meaning, SaveMode.OVERRIDE);
        }

        storage.addWordToCurrentList(word);

        return meaning;
    }
}
