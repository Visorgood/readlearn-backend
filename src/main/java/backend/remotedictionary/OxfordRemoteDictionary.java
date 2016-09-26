package backend.remotedictionary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OxfordRemoteDictionary implements RemoteDictionary {

    private final Map<String, String> oxfordDictionaryMock = new ConcurrentHashMap<>();

    public OxfordRemoteDictionary() {
        oxfordDictionaryMock.put("blue", "color that is blue");
        oxfordDictionaryMock.put("red", "another color that is red");
        oxfordDictionaryMock.put("canter", "slow gallop");
        oxfordDictionaryMock.put("convocation", "A large formal assembly of people");
        oxfordDictionaryMock.put("mop", "A thick mass of disordered hair");
        oxfordDictionaryMock.put("bod", "A body; a person");
        oxfordDictionaryMock.put("mustard", "A hot-tasting yellow or brown paste made from the crushed seeds of certain plants, typically eaten with meat or used as a cooking ingredient");
    }

    @Override
    public String lookupMeaning(String word) {
        // use of Oxford Dictionary API
        final String meaning = oxfordDictionaryMock.get(word);
        return meaning == null ? "" : meaning;
    }

    @Override
    public String getDictionaryName() {
        return "Oxford Online Dictionary";
    }
}
