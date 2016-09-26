package backend.domain;

public class ListInfo {
    private final int numberOfWords;
    private final String listName;

    public ListInfo(final int numberOfWords, final String listName) {
        this.numberOfWords = numberOfWords;
        this.listName = listName;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public String getListName() {
        return listName;
    }
}
