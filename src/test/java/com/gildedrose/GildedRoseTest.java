package com.gildedrose;

import static com.gildedrose.ItemNames.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void updateQuality_decrementsItemsQualityAndSellInBy1() throws Exception {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem("foo");
        assertEquals("foo", item.name);
        assertEquals(0, item.quality);
        assertEquals(0, item.sellIn);
    }

    @Test
    public void onceTheSellByDateHasPassed_QualityDegradesTwiceAsFast() throws Exception {
        Item[] items = new Item[] { new Item("bar", -1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem("bar");
        assertEquals("bar", item.name);
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }

    @Test
    public void qualityOfAnItemIsNeverNegative() throws Exception {
        Item[] items = new Item[] { new Item("foo", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        Item item = app.getItem("foo");
        assertEquals("foo", item.name);
        assertEquals(0, item.quality);
        assertEquals(-1, item.sellIn);
    }

    @Test
    public void theQualityOfAnItemIsNeverMoreThan50() throws Exception {
        Item[] items = new Item[] { new Item(BRIE, -1, 50), new Item(BACKSTAGE_PASSES_ITEM, 3, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(BRIE);
        assertEquals(BRIE, item.name);
        assertEquals(50, item.quality);
        assertEquals(-2, item.sellIn);

        Item item2 = app.getItem(BACKSTAGE_PASSES_ITEM);
        assertEquals(BACKSTAGE_PASSES_ITEM, item2.name);
        assertEquals(50, item2.quality);
        assertEquals(2, item2.sellIn);
    }

    @Test
    public void SulfurasBeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() throws Exception {
        Item[] items = new Item[] { new Item(SULFURAS, -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(SULFURAS);
        assertEquals(SULFURAS, item.name);
        assertEquals(80, item.quality);
        assertEquals(-1, item.sellIn);
    }

    @Test
    public void backstagePassesQualityIncrementsByOneWithEachDayPassing() throws Exception {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_ITEM, 11, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(BACKSTAGE_PASSES_ITEM);
        assertEquals(BACKSTAGE_PASSES_ITEM, item.name);
        assertEquals(31, item.quality);
        assertEquals(10, item.sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy2WhenThereAre10DaysOrLessRemaining() throws Exception {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_ITEM, 10, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(BACKSTAGE_PASSES_ITEM);
        assertEquals(BACKSTAGE_PASSES_ITEM, item.name);
        assertEquals(32, item.quality);
        assertEquals(9, item.sellIn);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy3WhenThereAre5DaysOrLessRemaining() throws Exception {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_ITEM, 5, 33) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(BACKSTAGE_PASSES_ITEM);
        assertEquals(BACKSTAGE_PASSES_ITEM, item.name);
        assertEquals(36, item.quality);
        assertEquals(4, item.sellIn);
    }

    @Test
    public void backstagePassesQualityDropsTo0AfterTheConcert() throws Exception {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_ITEM, -1, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(BACKSTAGE_PASSES_ITEM);
        assertEquals(BACKSTAGE_PASSES_ITEM, item.name);
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }

    @Test
    public void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() throws Exception {
        Item[] items = new Item[] { new Item(CONJURED_ITEM, 2, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.getItem(CONJURED_ITEM);
        assertEquals(CONJURED_ITEM, item.name);
        assertEquals(28, item.quality);
        assertEquals(1, item.sellIn);
    }

    @Test(expected=RuntimeException.class)
    public void getItem_throwsRuntimeExceptionIfRequestedItemDoesntExist() throws Exception {
        GildedRose app = new GildedRose(new Item[0]);
        app.getItem("foobar");
    }
}
