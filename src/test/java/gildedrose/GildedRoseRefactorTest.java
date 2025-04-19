package gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseRefactorTest {

	public static final int NOT_EXPIRED_SELLIN = 15;
	public static final int EXPIRED_SELLIN = -1;
	public static final int SELLIN_LESS_THAN_5 = 2;
	public static final int SELLIN_BETWEEN_5_AND_10 = 7;
	public static final int SELLIN_GREATER_THAN_10 = 16;
	public static final int DEFAULT_QUALITY = 3;
	public static final int MAXIMUM_QUALITY = 50;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";


	@Test
	public void unexpiredDefaultItem_qualityDecreasesByOne() {
		// Setup
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
        Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredDefaultItem_qualityDecreasesByTwo() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY -2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void notExpiredAgedBrie_qualityIncreasesByOne() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredAgedBrie_qualityIncreasesByTwo() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void notExpiredAgedBrie_maximumQuality() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, MAXIMUM_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, MAXIMUM_QUALITY);
		assertItem(expected, app.items[0]);

	}

	@Test
	public void backstagePassGreaterThan10_qualityIncreasesByOne() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASS, SELLIN_GREATER_THAN_10, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(BACKSTAGE_PASS, SELLIN_GREATER_THAN_10 - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassBetween5And10_qualityIncreasesByTwo() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASS, SELLIN_BETWEEN_5_AND_10, DEFAULT_QUALITY);
		//Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(BACKSTAGE_PASS, SELLIN_BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassLessThan5_qualityIncreasesByThree() {
		// Setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASS, SELLIN_LESS_THAN_5, DEFAULT_QUALITY);
		// Invoke
		app.updateQuality();
		// Verify
		Item expected = new Item(BACKSTAGE_PASS, SELLIN_LESS_THAN_5 - 1, DEFAULT_QUALITY + 3);
		assertItem(expected, app.items[0]);
	}

	private static void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals( expected.quality, actual.quality);
	}

	private static GildedRose createGildedRoseWithOneItem(String itemType, int sellIn, int quality) {
		Item item = new Item(itemType, sellIn, quality);
		Item[] items = new Item[] { item };
        return new GildedRose(items);
	}

}