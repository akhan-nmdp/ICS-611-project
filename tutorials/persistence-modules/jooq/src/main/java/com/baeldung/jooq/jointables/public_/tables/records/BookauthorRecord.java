/*
 * This file is generated by jOOQ.
 */
package com.baeldung.jooq.jointables.public_.tables.records;


import com.baeldung.jooq.jointables.public_.tables.Bookauthor;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BookauthorRecord extends UpdatableRecordImpl<BookauthorRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.BookAuthor.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.BookAuthor.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.BookAuthor.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.BookAuthor.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.BookAuthor.country</code>.
     */
    public void setCountry(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.BookAuthor.country</code>.
     */
    public String getCountry() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookauthorRecord
     */
    public BookauthorRecord() {
        super(Bookauthor.BOOKAUTHOR);
    }

    /**
     * Create a detached, initialised BookauthorRecord
     */
    public BookauthorRecord(Integer id, String name, String country) {
        super(Bookauthor.BOOKAUTHOR);

        setId(id);
        setName(name);
        setCountry(country);
        resetChangedOnNotNull();
    }
}
