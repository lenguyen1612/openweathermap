package openweathermap.search;

import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class testSearchWeather {

	WebDriver driver;

	@Test
	public void searchWeatherInYourCity() throws InterruptedException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://openweathermap.org/");

		WebElement pageContainer = driver.findElement(By.cssSelector("div.page-container"));
		WebElement searchText = pageContainer.findElement(By.cssSelector("div.search-container input"));

		searchText.sendKeys("Ha Noi");
		Thread.sleep(5000);
		WebElement buttonSearch = pageContainer.findElement(By.cssSelector("button"));

		buttonSearch.click();
		Thread.sleep(5000);

		WebElement selectResults = pageContainer.findElement(By.cssSelector("div.search-container ul"));
		selectResults.click();

		Thread.sleep(5000);
		WebElement widgetWeather = driver.findElement(By.cssSelector("div[class='grid-container grid-4-5']"));
		WebElement cityName = widgetWeather.findElement(By.cssSelector("div h2"));
		System.out.println(cityName.getText() );
		assertTrue(cityName.getText().contains("Ha Noi"), "Should be displayed correctly city name");

		WebElement temp = widgetWeather.findElement(By.cssSelector("div.current-temp span"));
		String tempNumber = "";
		
		System.out.println("temp:" + temp.getText() ); 
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(temp.getText());
		while (m.find()) {
			tempNumber = m.group();
		}

		assertTrue(isNumeric(tempNumber), "");
	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
