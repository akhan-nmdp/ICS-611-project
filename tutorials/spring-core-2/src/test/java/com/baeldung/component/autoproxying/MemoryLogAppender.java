package com.baeldung.component.autoproxying;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryLogAppender extends ListAppender<ILoggingEvent> {
    public void reset() {
        this.list.clear();
    }

    public boolean contains(String string, Level level) {
        return this.list.stream()
          .anyMatch(event -> event.getMessage().toString().contains(string)
            && event.getLevel().equals(level));
    }

    public int countEventsForLogger(String loggerName) {
        return (int) this.list.stream()
          .filter(event -> event.getLoggerName().contains(loggerName))
          .count();
    }

    public List<ILoggingEvent> search(String string) {
        return this.list.stream()
          .filter(event -> event.getMessage().toString().contains(string))
          .collect(Collectors.toList());
    }

    public List<ILoggingEvent> search(String string, Level level) {
        return this.list.stream()
          .filter(event -> event.getMessage().toString().contains(string)
            && event.getLevel().equals(level))
          .collect(Collectors.toList());
    }

    public int getSize() {
        return this.list.size();
    }

    public List<ILoggingEvent> getLoggedEvents() {
        return Collections.unmodifiableList(this.list);
    }
}
