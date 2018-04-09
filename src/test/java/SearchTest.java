import org.junit.Test;
import org.openqa.selenium.By;
public class SearchTest extends SauceTestBase
{
	@Test
	public void should_find_an_item()
	{
		driver.get(SearchPage.url);
		driver.findElement(SearchPage.searchBox).sendKeys("rocket");
		driver.findElement(SearchPage.categoriesDropDown).findElements(By.tagName("option")).get(SearchPage.Categories.ToysAndHobbies).click();
		driver.findElement(SearchPage.searchButton).click();
	}
}
