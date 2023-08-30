package ru.ibs;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;


// Проверка формы добавления товара (наличие элементов в форме)

public class FoodFormTest extends BaseSetup {

    @Test
    public void foodFormTest(){
        foodPage.clickAddBtn();
        wait.until(ExpectedConditions.visibilityOf(foodPage.productForm));

        assertTrue(foodPage.nameField.isDisplayed());
        assertTrue(foodPage.typeField.isDisplayed());
        assertTrue(foodPage.exoticCheckBox.isDisplayed());
        assertTrue(foodPage.saveBtn.isDisplayed());

        foodPage.inputName("Банан");
        foodPage.selectType("FRUIT");

        if(!foodPage.exoticCheckBox.isSelected())
        {
            foodPage.clickCheckBox();
        }

        foodPage.clickSaveBtn();
    }
}

