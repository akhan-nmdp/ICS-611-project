package com.baeldung.mapdb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NavigableSet;

import org.junit.jupiter.api.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

public class TransactionsUnitTest {

    @Test
    public void givenValidDBSetup_whenTransactionCommittedAndRolledBack_checkPreviousStateAchieved() {

        DB db = DBMaker.memoryDB().transactionEnable().make();

        NavigableSet<String> set = db
          .treeSet("mySet")
          .serializer(Serializer.STRING)
          .createOrOpen();

        set.add("One");
        set.add("Two");

        db.commit();

        assertEquals(2, set.size());

        set.add("Three");

        assertEquals(3, set.size());

        db.rollback();

        assertEquals(2, set.size());

        db.close();
    }
}
