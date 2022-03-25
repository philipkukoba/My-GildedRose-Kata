package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    Item[] items;

    private static ItemDecoratorFactory factory;

    public GildedRose(Item[] items) {
        this.items = items;
        this.factory = new ItemDecoratorFactory();
    }

    public void updateQuality()  {
        List<ItemDecorator> itemDecorators = new ArrayList<>();

        //add all
        for (Item item: items){
            itemDecorators.add(factory.getItemDecorator(item));
        }

        for (ItemDecorator itemDec: itemDecorators) {
            itemDec.updateQuality();
            itemDec.updateSellIn();
        }
    }

    /*
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            //not brie or ETC
            if (!items[i].name.equals(BRIE) && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) { //quality cannot be negative
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { //not sulfuras
                        items[i].quality--;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
    */

    public Item getItem(String foo) {
        for (Item item: items){
            if (item.name.equals(foo)) return item;
        }
        throw new RuntimeException(); //if item is not present
    }
}
