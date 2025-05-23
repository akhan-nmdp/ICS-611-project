package com.baeldung.thymeleaf.controller;

import com.baeldung.thymeleaf.model.Book;
import com.baeldung.thymeleaf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
    public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listBooks.html";
    }

    @GetMapping("/listBooksWithModal")
    public String listBooks(Model model) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book 1"));
        books.add(new Book(1, "Book 2"));
        books.add(new Book(1, "Book 2"));
        model.addAttribute("books", books);
        return "listBooksWithModal.html";
    }
}
