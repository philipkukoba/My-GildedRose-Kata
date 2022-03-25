package com.gildedrose;

public class Brie extends ItemDecorator {

    public Brie(Item item) {
        super(item);
        this.hasStandardBehavior = false;
    }

    @Override
    public void updateQualityNonStandardBehavior() {
        this.item.quality++;
    }

}
