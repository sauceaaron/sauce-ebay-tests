import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends PageObject
{
	public SearchResultsPage(WebDriver driver)
	{
		super(driver);
	}

	public static SearchResultsPage getInstance(WebDriver driver)
	{
		return new SearchResultsPage(driver);
	}
}
