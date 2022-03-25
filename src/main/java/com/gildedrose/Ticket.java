package com.gildedrose;

public class Ticket extends ItemDecorator {
    public Ticket(Item item) {
        super(item);
        this.hasStandardBehavior = false;
    }

    @Override
    public void updateQualityNonStandardBehavior(){
        if (this.item.sellIn <= 0) {
            this.item.quality = 0;
        }
        else if (this.item.sellIn <= 5) {
            this.item.quality += 3;
        }
        else if (this.item.sellIn > 6 && this.item.sellIn <= 10) {
            this.item.quality += 2;
        }
        else {
            this.item.quality++;
        }
    }
}
