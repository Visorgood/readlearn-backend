package backend.remotedictionary;

public interface RemoteDictionary {

    String lookupMeaning(String word);

    String getDictionaryName();
}
