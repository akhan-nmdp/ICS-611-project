package com.baeldung.serenity.screenplay;

import java.util.List;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class GoogleSearchResults implements Question<List<String>> {

    public static Question<List<String>> displayed() {
        return new GoogleSearchResults();
    }

    public List<String> answeredBy(Actor actor) {
        return Text.of(GoogleSearchPage.SEARCH_RESULT_TITLES).viewedBy(actor).asList();
    }
}
