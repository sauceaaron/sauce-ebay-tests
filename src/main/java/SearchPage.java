import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
		WebElement option = find(categoriesDropDown).findElements(By.tagName("option")).get(category);

		return selectCategory(option);
	}


	public SearchPage selectCategory(String category)
	{
		List<WebElement> options = find(categoriesDropDown).findElements(By.tagName("option"));

		WebElement option = options.stream()
				.filter(o -> o.getText().equals(category))
				.findFirst().get();


		return selectCategory(option);
	}

	public SearchPage selectCategory(WebElement option)
	{
		if (! option.isSelected())
		{
			option.click();
		}

		return this;
	}



	public SearchPage clickSearchButton()
	{
		click(searchButton);
		return this;
	}
}
