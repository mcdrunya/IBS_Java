package ru.ibs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodPageTest extends BaseSetup {

    @Test
    public void foodPageTest(){
        assertTrue(foodPage.colName.isDisplayed());
        assertTrue(foodPage.colType.isDisplayed());
        assertTrue(foodPage.colExotic.isDisplayed());
        assertTrue(foodPage.addBtn.isDisplayed());

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
}

