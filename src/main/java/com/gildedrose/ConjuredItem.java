package com.gildedrose;

public class ConjuredItem extends ItemDecorator{


    public ConjuredItem(Item item) {
        super(item);
        this.isConjured = true;
    }
}
