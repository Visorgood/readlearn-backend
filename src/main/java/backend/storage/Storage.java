package backend.storage;

import backend.domain.ListInfo;

import java.util.List;

public interface Storage {

    String lookupMeaning(String word);

    void saveWord(String word, String meaning, SaveMode saveMode);

    List<String> getAllLists();

    boolean addWordToCurrentList(String word);

    boolean createList(String list);

    ListInfo getListInfo(String list);

    List<String> getWordsOfList(String list);

    String getCurrentList();

    boolean setCurrentList(String list);
}
