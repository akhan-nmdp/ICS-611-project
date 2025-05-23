package com.baeldung.serenity.spring;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aiet
 */
@RequestMapping(value = "/adder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class PlainAdderController {

    private final int currentNumber = RandomUtils.nextInt();

    @GetMapping("/current")
    public int currentNum() {
        return currentNumber;
    }

    @PostMapping
    public int add(@RequestParam int num) {
        return currentNumber + num;
    }

}
