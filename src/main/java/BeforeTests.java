import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BeforeTests {

    private WebDriver ChDriver;

    @Before
    public void DriverStart()
    {
        ChDriver = new ChromeDriver();
    }

    @After
    public void DriverQuit()
    {
        ChDriver.quit();
    }

    @Test
    public void ComparePageSourcesTest() {

        ChDriver.get("http://google.com");
        String ChromePageSource = ChDriver.getPageSource();

        WebDriver FFDriver = new FirefoxDriver();
        FFDriver.get("http://google.com");
        String FirefoxPageSource = FFDriver.getPageSource();
        FFDriver.quit();

        Assert.assertEquals("Page Sources are not equel", ChromePageSource, FirefoxPageSource);
    }

    @Test (expected = Exception.class)
    public void ElemetNotFoundTest(){
        ChDriver.get("https://google.com");
        ChDriver.findElement(By.className("gsfiMISSPELLING")).sendKeys("HelloWorld!");
    }

    @Test (timeout=3500)
    public void TimeoutTest1(){
        ChDriver.get("https://google.com");
        WebElement field =  ChDriver.findElement(By.className("gsfi"));
        Assert.assertEquals("", field.getText());
    }

    //WHY IT DOES NOT WORK????
    @Test
    public void TimeoutTest2(){
        ChDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        ChDriver.get("http://upyachka.ru/");
    }
}
