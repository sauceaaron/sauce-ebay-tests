import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchTest
{
	String SAUCE_URL = "https://aaron-evans:82fda6e5-c020-40a3-8532-f817475ce55a@ondemand.saucelabs.com:443/wd/hub";

	@Test
	public void should_find_an_item() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("version", "65");

		WebDriver driver = new RemoteWebDriver(url, capabilities);

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
