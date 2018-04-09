import org.junit.Test;
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
	}
}
