package backend.web;

import backend.service.ListsService;
import backend.service.LookupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    private final LookupService lookupService = new LookupService();
    private final ListsService listsService = new ListsService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        LOGGER.info("index()");
        return "Greetings from ReadLearn backend service! Hahaha";
    }

    @RequestMapping(value = "/lookup/{word}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String lookupByWord(@PathVariable("word") String word) {
        LOGGER.info("lookupByWord(" + word + ")");
        final String meaning = lookupService.lookup(word);
        return word + ": " + meaning;
    }

    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> allLists() {
        LOGGER.info("allLists()");
        return listsService.getAllLists();
    }

    @RequestMapping(value = "/lists/{list}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String listInfoByName(@PathVariable("list") String list) {
        LOGGER.info("listInfoByName(" + list + ")");
        return "Info for the list '" + list + "' is ...";
    }

    @RequestMapping(value = "/lists/{list}/words", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String wordsOfListByName(@PathVariable("list") String list) {
        LOGGER.info("wordsOfListByName(" + list + ")");
        return "Words of the list '" + list + "' are ...";
    }

    @RequestMapping(value = "/lists/{list}/words/{word}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String wordOfListByName(@PathVariable("list") String list, @PathVariable("word") String word) {
        LOGGER.info("wordOfListByName(" + list + ", " + word + ")");
        return "Meaning of the word '" + word + "' from the list '" + list + "' is ...";
    }

    @RequestMapping(value = "/lists/new/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String createNewListWithName(@PathVariable("name") String name) {
        LOGGER.info("createNewListWithName(" + name + ")");
        final boolean created = listsService.createNewList(name);
        return (created ? "New list with the name '" + name + "' was created." : "List with the name '" + name + "' already exists!");
    }

    @RequestMapping(value = "/lists/currentlist", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getCurrentList() {
        LOGGER.info("getCurrentList()");
        return "Current list is ...";
    }

    @RequestMapping(value = "/lists/currentlist/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String setCurrentListByName(@PathVariable("name") String name) {
        LOGGER.info("setCurrentListByName(" + name + ")");
        return "List " + name + " was set as current";
    }
}
