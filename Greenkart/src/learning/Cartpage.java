package learning;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Cartpage {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		System.setProperty("web.driver.chromedriver", "F:/Courses/selenium/chromedriver");
		String[] itemsNeeded= {"Cucumber","Tomato","Apple"};
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		addItems(driver,itemsNeeded);
		placeOrder(driver,136);
		}
		
	public static  void addItems(WebDriver driver,String[] itemsNeeded)
		{
		int j=0;
		List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
		for(int i=0;i<products.size();i++) {
			String[] name=products.get(i).getText().split("-");
			String formattedName=name[0].trim();
			List<String> itemsNeededList = Arrays.asList(itemsNeeded);
			if(itemsNeededList.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if(j==itemsNeeded.length)
					break;
				}
			}
		}
		public static void placeOrder(WebDriver driver,int exp) {
		driver.findElement(By.cssSelector(".cart-icon")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String actual=driver.findElement(By.cssSelector("div .discountAmt")).getText();
		int act=Integer.parseInt(actual);
		Assert.assertEquals(exp, act);
		System.out.println("Placing order...");
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.cssSelector(".chkAgree")).click();
		WebElement c=driver.findElement(By.cssSelector("div select"));
		Select s=new Select(c);
		s.selectByValue("India");
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		String msg=driver.findElement(By.cssSelector(".wrapperTwo")).getText();
		System.out.println(msg);
		}
	}

