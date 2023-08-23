package ru.ibs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FoodPageTest {
    public static FoodPage foodPage;
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        foodPage = new FoodPage(driver);

        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("page_url"));
    }

    // Проверяю наличие столбцов на странице
    @Test
    public void columnsTest(){
        assertTrue(foodPage.colName.isDisplayed());
        assertTrue(foodPage.colType.isDisplayed());
        assertTrue(foodPage.colExotic.isDisplayed());
        assertTrue(foodPage.addBtn.isDisplayed());
    }

    @Test
    public void newProductTest(){
        int initialTableSize = foodPage.getTableSize();
        foodPage.clickAddBtn();
        wait.until(ExpectedConditions.visibilityOf(foodPage.productForm));
        foodPage.inputName("Помидор");
        foodPage.selectType("VEGETABLE");
        foodPage.clickCheckBox();
        foodPage.clickSaveBtn();

        wait.until(ExpectedConditions.not(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//tbody/tr"), initialTableSize)));

        int updatedTableSize = foodPage.getTableSize();
        assertEquals(initialTableSize + 1, updatedTableSize); // Проверяю, что товар действительно добавился
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}

