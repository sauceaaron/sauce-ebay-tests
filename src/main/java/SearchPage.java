import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends PageObject
{
	public static String url = "https://www.ebay.com";

	public static By searchBox = By.id("gh-ac");
	public static By categoriesDropDown = By.id("gh-cat");

	public static class Categories {
		public static int ToysAndHobbies = 32;
	}

	public static By searchButton = By.id("gh-btn");

	public static SearchPage Open(WebDriver driver)
	{
		return new SearchPage(driver).open();
	}

	public SearchPage(WebDriver driver)
	{
		super(driver);
	}

	public SearchPage open()
	{
		driver.get(url);
		return this;
	}

	public SearchPage enterSearchText(String text)
	{
		find(searchBox).sendKeys(text);
		return this;
	}

	public SearchPage selectCategory(int category)
	{
		find(categoriesDropDown).findElements(By.tagName("option")).get(category);
		return this;
	}

	public SearchPage clickSearchButton()
	{
		click(searchButton);
		return this;
	}
}
