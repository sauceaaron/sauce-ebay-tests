import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest
{
	@Test
	public void should_find_an_item()
	{
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.ebay.com");

		pause(1);

		driver.findElement(By.id("gh-ac")).click();

		pause(1);

		driver.findElement(By.id("gh-ac")).sendKeys("rocket");

		pause(1);

		driver.findElements(By.tagName("select")).get(0)
				.findElements(By.tagName("option")).get(32).click();

		pause(1);

		driver.findElement(By.xpath("/html[1]/body[1]/header[1]/table[1]/tbody[1]/tr[1]/td[3]/form[1]/table[1]/tbody[1]/tr[1]/td[3]/input[1]")).click();

		pause(1);
	}

	public void pause(int seconds)
	{
		try { Thread.sleep(seconds * 1000); }
		catch (InterruptedException e) { e.printStackTrace(); }
	}
}
