package openweathermap.search;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class testSearchWeather {
	
	WebDriver driver;
	
	@Test
	public void searchWeatherInYourCity() throws InterruptedException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+"/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://openweathermap.org/");
		
		WebElement pageContainer = driver.findElement(By.cssSelector("div.page-container"));
		WebElement searchText = pageContainer.findElement(By.cssSelector("div.search-container input"));
		
		
		searchText.sendKeys("Ha Noi");
		Thread.sleep(500);
		WebElement buttonSearch = pageContainer.findElement(By.cssSelector("button"));
		
		buttonSearch.click();
		Thread.sleep(500);
		
		WebElement selectResults = pageContainer.findElement(By.cssSelector("div.search-container ul"));
		selectResults.click();
	}
	
	public boolean isLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		final JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try{
					return ((Long)jsExecutor.executeScript("return jQuery.active")==0);
				}catch(Exception e) {
					return true;
				}
			}
		};
		
//		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				try{
//					return ((Long)jsExecutor.executeScript("return jQuery.active")==0);
//				}catch(Exception e) {
//					return true;
//				}
//			}
//		};
		
		return false;
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
