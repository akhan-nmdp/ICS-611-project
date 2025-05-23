package com.baeldung.reactor.generate.create;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;

public class CharacterCreator {
    public Consumer<List<Character>> consumer;

    public Flux<Character> createCharacterSequence() {
        return Flux.create(sink -> CharacterCreator.this.consumer = items -> items.forEach(sink::next));
    }
}
