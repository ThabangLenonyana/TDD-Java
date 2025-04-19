package gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseRefactorTest {

	public static final int NOT_EXPIRED_SELLIN = 15;
	public static final int DEFAULT_QUALITY = 3;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELLIN = -1;

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