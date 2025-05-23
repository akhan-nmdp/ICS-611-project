package com.baeldung.differences.rdd;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.baeldung.differences.dataframe.dataset.rdd.TouristData;

public class DatasetUnitTest {
    private static SparkSession session;
    private static Dataset<TouristData> typedDataset;

    @BeforeClass
    public static void init() {
        session = SparkSession.builder()
            .appName("TouristDatasetExample")
            .master("local[*]")
            .getOrCreate();
        DataFrameReader dataFrameReader = session.read();
        Dataset<Row> data = dataFrameReader.option("header", "true")
            .csv("data/Tourist.csv");
        Dataset<Row> responseWithSelectedColumns = data.select(col("region"),
            col("country"), col("year"), col("series"), col("value").cast("double"),
            col("footnotes"), col("source"));
        typedDataset = responseWithSelectedColumns.as(Encoders.bean(TouristData.class));
    }

    @AfterClass
    public static void cleanup() {
        session.stop();
    }

    @Test
    public void whenFilteringByCountry_thenCountryRecordsSelected() {
        Dataset<TouristData> selectedData = typedDataset
            .filter((FilterFunction<TouristData>) record -> record.getCountry()
            .equals("Norway"));

        // uncomment to see output
        // selectedData.show();

        selectedData.foreach(record -> {
            assertEquals("Norway", record.getCountry());
        });
    }

    @Test
    public void whenGroupCountByCountry_thenContryTotalRecords() {
        Dataset<Row> countriesCount = typedDataset.groupBy(typedDataset.col("country"))
            .count();

        // uncomment to see output
        // countriesCount.show();

        assertEquals(220, countriesCount.count());
    }

    @Test
    public void whenFilteredByPropertyRange_thenRetreiveValidRecords() {
        // Filter records with existing data for years between 2010 and 2017
        Dataset<TouristData> filteredData = typedDataset.filter(
          (FilterFunction<TouristData>) record -> record.getYear() != null
            && (Long.parseLong(record.getYear()) > 2010 && Long.parseLong(record.getYear()) < 2017));

        // uncomment to see output
        // filteredData.show();

        assertEquals(394, filteredData.count());
        filteredData.foreach(record -> {
            assertTrue(Integer.parseInt(record.getYear()) > 2010 && Integer.parseInt(record.getYear()) < 2017);
        });
    }

    @Test
    public void whenSumValue_thenRetreiveTotalValue() {
        // Total tourist expenditure by country
        Dataset<Row> filteredData = typedDataset.filter((FilterFunction<TouristData>) record -> record.getValue() != null
            && record.getSeries().contains("expenditure"))
          .groupBy("country")
          .agg(sum("value"));

        // uncomment to see output
        // filteredData.show();

        assertEquals(212, filteredData.count());
    }

}
