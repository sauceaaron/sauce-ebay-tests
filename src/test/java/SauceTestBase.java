import com.saucelabs.saucerest.SauceREST;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceTestBase
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	String SAUCE_URL = "https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:443/wd/hub"
			.replace("SAUCE_USERNAME", SAUCE_USERNAME)
			.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

	RemoteWebDriver driver;
	SauceREST api;

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("version", "65");
		capabilities.setCapability("name", "eBay search test");

		driver = new RemoteWebDriver(url, capabilities);
		api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
	}


	@After
	public void teardown()
	{
		api.jobPassed(driver.getSessionId().toString());
		driver.quit();
	}

	public void pause(int seconds)
	{
		try { Thread.sleep(seconds * 1000); }
		catch (InterruptedException e) { e.printStackTrace(); }
	}
}