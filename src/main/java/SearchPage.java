import org.openqa.selenium.By;

public class SearchPage
{
	public static String url = "https://www.ebay.com";

	public static By searchBox = By.id("gh-ac");
	public static By categoriesDropDown = By.id("gh-cat");

	public static class Categories {
		public static int ToysAndHobbies = 32;
	};

	public static By searchButton = By.id("gh-btn");
}
