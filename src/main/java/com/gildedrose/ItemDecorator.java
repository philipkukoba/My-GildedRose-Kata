package com.gildedrose;

public class ItemDecorator {

    protected Item item;

    protected boolean hasStandardBehavior = true;
    protected boolean isConjured = false;
    protected boolean isLegendary = false;

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public ItemDecorator(Item item){
        this.item = item;
    }

    public void updateQuality() {
        if (!isLegendary) {
            if (hasStandardBehavior) {

                //set conjuredFactor
                int factor;
                if (isConjured) {
                    factor = 2;
                } else {
                    factor = 1;
                }

                //update quality
                if (this.item.sellIn < 0 && this.item.quality > (MIN_QUALITY + 1)) {
                    this.item.quality -= (2 * factor);
                } else if (this.item.quality > MIN_QUALITY) {
                    this.item.quality -= (1 * factor);
                }
            } else {

                updateQualityNonStandardBehavior();

                if (this.item.quality > MAX_QUALITY) {
                    this.item.quality = MAX_QUALITY;
                }
            }
        }
    }

    public void updateQualityNonStandardBehavior(){

    }

    public void updateSellIn(){
        if (!isLegendary) this.item.sellIn--;
    }
}
