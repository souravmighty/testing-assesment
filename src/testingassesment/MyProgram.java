package testingassesment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyProgram {
	public static void takepicture(WebDriver driver, String filename) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(".//screenshots//" + filename + ".png"));
	}

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http:www.amazon.in");
		driver.manage().window().maximize();
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("watches");
		Thread.sleep(2000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		driver.findElement(By.cssSelector("img[data-image-index=\"3\"]")).click();
		Set<String> windows = driver.getWindowHandles();

		for (String win : windows) {
			if (!mainWindow.contentEquals(win)) {
				driver.switchTo().window(win);
				takepicture(driver, "watch4");
				Thread.sleep(3000);
				driver.close();
			}
			driver.close();

		}
	}

}
