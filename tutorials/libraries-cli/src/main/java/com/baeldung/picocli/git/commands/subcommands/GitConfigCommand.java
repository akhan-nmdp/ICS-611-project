package com.baeldung.picocli.git.commands.subcommands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import org.springframework.stereotype.Component;

import com.baeldung.picocli.git.model.ConfigElement;

@Command(
  name = "config"
)
@Component
public class GitConfigCommand implements Runnable {
    @Parameters(index = "0")
    private ConfigElement element;

    @Parameters(index = "1")
    private String value;

    @Override
    public void run() {
        System.out.println("Setting " + element.value() + " to " + value);
    }
}
