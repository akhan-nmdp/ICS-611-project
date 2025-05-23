package com.baeldung.cache;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Controller
public class CacheControlController {

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS)
          .noTransform()
          .mustRevalidate();
        return ResponseEntity.ok()
          .cacheControl(cacheControl)
          .body("Hello " + name);
    }

    @GetMapping(value = "/home/{name}")
    public String home(@PathVariable String name, final HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=60, must-revalidate, no-transform");
        return "home";
    }

    @GetMapping(value = "/login/{name}")
    public ResponseEntity<String> intercept(@PathVariable String name) {
        return ResponseEntity.ok().body("Hello " + name);
    }

    @GetMapping(value = "/productInfo/{name}")
    public ResponseEntity<String> validate(@PathVariable String name, WebRequest request) {

        ZoneId zoneId = ZoneId.of("GMT");
        long lastModifiedTimestamp = LocalDateTime.of(2020, 02, 4, 19, 57, 45)
          .atZone(zoneId).toInstant().toEpochMilli();

        if (request.checkNotModified(lastModifiedTimestamp)) {
            return ResponseEntity.status(304).build();
        }

        return ResponseEntity.ok().body("Hello " + name);
    }


}
