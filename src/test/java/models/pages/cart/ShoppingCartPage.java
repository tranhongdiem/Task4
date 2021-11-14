package models.pages.cart;

import models.components.cart.CardFooterComponent;
import models.components.cart.CartComponent;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {

    private final WebDriver driver;
    private CartComponent cartComponent;
    private CardFooterComponent cardFooterComponent;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartComponent shoppingCartItemComp(){
        return new CartComponent(driver);
    }

    public CardFooterComponent cardFooterComponent() {

        if(cardFooterComponent == null){
            cardFooterComponent = new CardFooterComponent(driver);
        }
        return new CardFooterComponent(driver);
    }

    public CartComponent cartComponent(){
        if (cartComponent == null){
            cartComponent = new CartComponent(driver);
        }
        return new CartComponent(driver);
    }
}
