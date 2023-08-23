package ru.ibs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class FoodPage {

    public WebDriver driver;
    public Select typeSelect;

    public FoodPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//th[text()='Наименование']")
    public WebElement colName;

    @FindBy(xpath = "//th[text()='Тип']")
    public WebElement colType;

    @FindBy(xpath = "//th[text()='Экзотический']")
    public WebElement colExotic;

    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    public WebElement addBtn;


    public int getTableSize(){
        WebElement tbodyElement = driver.findElement(By.xpath("//tbody"));

        return tbodyElement.findElements(By.tagName("tr")).size();
    }


    // Все, что ниже относится к форме
    @FindBy(xpath = "//div[@class='modal-content']")
    public WebElement productForm;

    @FindBy(xpath = "//*[contains(@id, 'name')]")
    public WebElement nameField;

    @FindBy(xpath = "//*[contains(@id, 'type')]")
    public WebElement typeField;

    @FindBy(xpath = "//*[contains(@id, 'exotic')]")
    public WebElement exoticCheckBox;

    @FindBy(xpath = "//*[contains(@id, 'save')]")
    public WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"editModal\"]/div/div/div[1]/button")
    public WebElement closeBtn;

    public void clickAddBtn() {
        addBtn.click();
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

    public void clickCloseBtn() { closeBtn.click(); }

    public void inputName(String value) { nameField.sendKeys(value); }

    public void clickCheckBox() { exoticCheckBox.click(); }

    public void selectType(String value) {
        typeSelect = new Select(typeField);
        typeSelect.selectByValue(value);
    }

}



