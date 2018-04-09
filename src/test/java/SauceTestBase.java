import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.saucerest.SauceREST;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

@Ignore
@RunWith(ConcurrentParameterized.class)
public class SauceTestBase implements SauceOnDemandSessionIdProvider
{
	String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

	String SAUCE_URL = "https://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:443/wd/hub"
			.replace("SAUCE_USERNAME", SAUCE_USERNAME)
			.replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

	SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(SAUCE_USERNAME, SAUCE_ACCESS_KEY);

	@Rule
	public TestName testName = new TestName();

	@Rule
	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	RemoteWebDriver driver;
	String sessionId;
	SauceREST api;

	String platform;
	String browserName;
	String browserVersion;

	String BUILD_TAG = System.getenv("BUILD_TAG");

	@ConcurrentParameterized.Parameters
	public static LinkedList getPlatforms()
	{
		LinkedList browsers = new LinkedList();

		browsers.add(new String[]{"Windows 10", "internet explorer", "11"});
		browsers.add(new String[]{"MacOS 10.12", "Chrome", "latest"});
		browsers.add(new String[]{"Linux", "Firefox", "latest"});

		/* this doesn't work with Safari or Edge Driver */
//		browsers.add(new String[]{"Windows 10", "MicrosoftEdge", "latest"});
//		browsers.add(new String[]{"MacOS 10.13", "Safari", "11"});

		return browsers;
	}

	public SauceTestBase(String platform, String browserName, String browserVersion) {
		super();
		this.platform = platform;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}

	@Before
	public void setup() throws MalformedURLException
	{
		URL url = new URL(SAUCE_URL);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("version", browserVersion);
		capabilities.setCapability("name", "eBay search test on " + browserName);

		if (BUILD_TAG != null)
		{
			capabilities.setCapability("build", BUILD_TAG);
		}

		System.out.println("capabilities: " + capabilities);

		driver = new RemoteWebDriver(url, capabilities);
		sessionId = driver.getSessionId().toString();

		api = new SauceREST(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
	}

	@After
	public void teardown()
	{
		if (driver == null)
		{
			System.out.println("driver is null, can't complete session");
			return;
		}

		driver.quit();
	}

	public String getSessionId()
	{
		return sessionId;
	}
}
