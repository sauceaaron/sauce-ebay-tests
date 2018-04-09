import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends SauceTestBase
{
	public SearchTest(String platform, String browserName, String browserVersion)
	{
		super(platform, browserName, browserVersion);
	}

	@Test
	public void should_find_an_item()
	{
		SearchPage
				.Open(driver)
				.enterSearchText("rocket")
				.selectCategory("Toys & Hobbies")
				.clickSearchButton();

		String title = driver.getTitle();
		assertThat(title).isEqualTo("rocket in Toys and Hobbies | eBay");
	}
}
