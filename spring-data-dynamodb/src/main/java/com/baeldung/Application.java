package com.baeldung;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.baeldung.spring.data.dynamodb.model.*;
import com.baeldung.spring.data.dynamodb.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "com.baeldung" })
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(AmazonDynamoDB amazonDynamoDB, BooksRepository booksRepository,
                                  MembersRepository membersRepository, LibrariansRepository librariansRepository) {
        return (args -> {
            DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
            ListTablesResult allTables = amazonDynamoDB.listTables();

            if (!allTables.getTableNames().contains("Books")) {
                log.info("create the table Books");
                CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Books.class);

                tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

                amazonDynamoDB.createTable(tableRequest);
                log.info("create data");
                Books books = new Books("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", Boolean.TRUE);
                log.info("save data for books1");
                booksRepository.save(books);
                Books books2 = new Books("9780061120084", "To Kill a Mockingbird", "Harper Lee", "Classic", Boolean.TRUE);
                log.info("save data for books2");
                booksRepository.save(books2);
                Books books3 = new Books("9780451524935", "1984", "George Orwell", "Dystopian", Boolean.TRUE);
                log.info("save data for books3");
                booksRepository.save(books3);
                Books books4 = new Books("9781503280786", "Moby Dick", "Herman Melville", "Adventure", Boolean.TRUE);
                log.info("save data for books4");
                booksRepository.save(books4);
                Books books5 = new Books("9780316769488", "The Catcher in the Rye", "J. D. Salinger", "Fiction", Boolean.TRUE);
                log.info("save data for books5");
                booksRepository.save(books5);
            } else {
                log.info("Table Books already exists. skipping creating and insert data part.");
            }

            if (!allTables.getTableNames().contains("Members")) {
                log.info("create the table Members");
                CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Members.class);

                tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

                amazonDynamoDB.createTable(tableRequest);
                log.info("add data for members1");
                Members members = new Members("John Doe", "johndoe@email.com", "2023-01-15");
                log.info("save data for members1");
                membersRepository.save(members);
                log.info("add data for members2");
                Members members2 = new Members("Jane Smith", "janesmith@email.com", "2022-11-23");
                log.info("save data for members2");
                membersRepository.save(members2);
                log.info("add data for members3");
                Members members3 = new Members("Robert Brown", "robertbrown@email.com", "2023-03-10");
                log.info("save data for members3");
                membersRepository.save(members3);
                log.info("add data for members4");
                Members members4 = new Members("Emily Johnson", "emilyjohnson@email.com", "2021-09-07");
                log.info("save data for members4");
                membersRepository.save(members4);
                log.info("add data for members5");
                Members members5 = new Members("Michael Davis", "michaeldavis@email.com", "2022-12-01");
                log.info("save data for members5");
                membersRepository.save(members5);
            } else {
                log.info("Table Members already exists. skipping creating and insert data part.");
            }

            if (!allTables.getTableNames().contains("Borrowed_Books")) {
                log.info("create the table Borrowed_Books");
                CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(BorrowedBooks.class);

                tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

                amazonDynamoDB.createTable(tableRequest);
                log.info("table created");
            } else {
                log.info("Table Borrowed_Books already exists. skipping creating and insert data part.");
            }

        if (!allTables.getTableNames().contains("Librarians")) {
            log.info("create the table Librarians");
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Librarians.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
            log.info("add data for librarians1");
            Librarians librarians = new Librarians("Alice Green", "123-456-789", "Head Librarian");
            log.info("save data for librarians1");
            librariansRepository.save(librarians);
            log.info("add data for librarians2");
            Librarians librarians2 = new Librarians("Bob White", "987-654-3210", "Assistant Librarian");
            log.info("save data for librarians2");
            librariansRepository.save(librarians2);
            log.info("add data for librarians3");
            Librarians librarians3 = new Librarians("Charlie Black", "555-123-456", "Library Clerk");
            log.info("save data for librarians3");
            librariansRepository.save(librarians3);
        } else {
            log.info("Table Librarians already exists. skipping creating and insert data part.");
        }

    if (!allTables.getTableNames().contains("Fines")) {
            log.info("create the table Fines");
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Fines.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } else {
            log.info("Table Fines already exists. skipping creating and insert data part.");
        }

    });

    }
}
