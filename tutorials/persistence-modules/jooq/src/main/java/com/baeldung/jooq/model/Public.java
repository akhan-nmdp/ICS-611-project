/*
 * This file is generated by jOOQ.
 */
package com.baeldung.jooq.model;

import com.baeldung.jooq.model.tables.Article;
import com.baeldung.jooq.model.tables.Author;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Public extends SchemaImpl {

    private static final long serialVersionUID = -2049410122;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.article</code>.
     */
    public final Article ARTICLE = Article.ARTICLE;

    /**
     * The table <code>public.author</code>.
     */
    public final Author AUTHOR = Author.AUTHOR;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                Article.ARTICLE,
                Author.AUTHOR
        );
    }
}
