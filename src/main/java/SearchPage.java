import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage
{
	public static String url = "https://www.ebay.com";

	public static By searchBox = By.id("gh-ac");
	public static By categoriesDropDown = By.id("gh-cat");

	public static class Categories {
		public static int ToysAndHobbies = 32;
	}

	public static By searchButton = By.id("gh-btn");

	private WebDriver driver;

	public static SearchPage Open(WebDriver driver)
	{
		return new SearchPage(driver).load();
	}

	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public SearchPage load()
	{
		driver.get(url);
		return this;
	}

	public SearchPage enterSearchText(String text)
	{
		driver.findElement(searchBox).sendKeys(text);
		return this;
	}

	public SearchPage selectCategory(int category)
	{
		driver.findElement(categoriesDropDown).findElements(By.tagName("option")).get(category);
		return this;
	}

	public SearchPage clickSearchButton()
	{
		driver.findElement(searchButton).click();
		return this;
	}
}
