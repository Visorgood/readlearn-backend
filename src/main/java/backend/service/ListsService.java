package backend.service;

import backend.storage.PostgresStorage;
import backend.storage.Storage;

import java.util.List;

public class ListsService {

    private final Storage storage = new PostgresStorage();

    public List<String> getAllLists() {
        return storage.getAllLists();
    }

    public boolean createNewList(final String name) {
        return storage.createList(name);
    }
}
