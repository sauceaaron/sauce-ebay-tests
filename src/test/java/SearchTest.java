import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends SauceTestBase
{
	@Test
	public void should_find_an_item()
	{
		SearchPage
				.Open(driver)
				.enterSearchText("rocket")
				.selectCategory(SearchPage.Categories.ToysAndHobbies)
				.clickSearchButton();

		// check whether your test succeeds
		String title = driver.getTitle();
		assertThat(title).isEqualTo("rocket in Toys and Hobbies | eBay");
	}
}
