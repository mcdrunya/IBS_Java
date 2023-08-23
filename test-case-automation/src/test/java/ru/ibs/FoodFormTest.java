package ru.ibs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

// Проверка формы добавления товара (наличие элементов в форме)

public class FoodFormTest {
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

        foodPage.clickAddBtn();
        wait.until(ExpectedConditions.visibilityOf(foodPage.productForm));
    }


    @Test
    public void fieldsTest(){
        assertTrue(foodPage.nameField.isDisplayed());
        assertTrue(foodPage.typeField.isDisplayed());
        assertTrue(foodPage.exoticCheckBox.isDisplayed());
        assertTrue(foodPage.saveBtn.isDisplayed());
    }

    @Test
    public void nameInputTest(){
        foodPage.inputName("Банан");
    }

    @Test
    public void selectTypeTest(){
        foodPage.selectType("FRUIT");
    }

    @Test
    public void checkBoxTest(){
        if(!foodPage.exoticCheckBox.isSelected())
        {
            foodPage.clickCheckBox();
        }
    }

    @Test
    public void saveBtnTest(){
        foodPage.clickSaveBtn();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}

