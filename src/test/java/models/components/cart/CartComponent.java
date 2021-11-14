package models.components.cart;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartComponent {

    private final WebDriver driver;
    private final By itemTotalPriceSel = By.className("product-subtotal");
    private final By cartItemRowSel = By.cssSelector("table[class='cart'] .cart-item-row");
    private final By productImgSel = By.cssSelector(".product-picture img");
    private final By productNameLinkSel = By.className("product-name");
    private final By productAttributeSel = By.className("attributes");
    private final By productEditLinkSel = By.cssSelector(".edit-item");
    private final By productPriceSel = By.className("product-unit-price");
    private final By productQuantitySel = By.className("qty-input");
    private final By productSubTotalSel = By.className("product-subtotal");


    public CartComponent(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get current total price")
    public Double itemTotalPrice(){
        String itemTotalPriceStr = driver.findElement(itemTotalPriceSel).getText();
        return Double.parseDouble(itemTotalPriceStr);
    }

    public List<CartItemRowData> cartItemRowData(){
        List<CartItemRowData> cartItemRowData = new ArrayList<>();
        List<WebElement> cartItemRowElems = driver.findElements(cartItemRowSel);
        for (WebElement cartItemRowElem : cartItemRowElems){
            String productImgScr = cartItemRowElem.findElement(productImgSel).getAttribute("scr");
            String productName = cartItemRowElem.findElement(productNameLinkSel).getText();
            String productNameLink = cartItemRowElem.findElement(productNameLinkSel).getAttribute("href");

            //
            List<WebElement> productAttributeElems = cartItemRowElem.findElements(productAttributeSel);
            String productAttributes = productAttributeElems.isEmpty()? null : productAttributeElems.get(0).getText();

            double productPrice = Double.parseDouble(cartItemRowElem.findElement(productPriceSel).getText());
            double productQuantity = Double.parseDouble(cartItemRowElem.findElement(productQuantitySel).getAttribute("value"));
            double productSubTotal = Double.parseDouble(cartItemRowElem.findElement(productSubTotalSel).getText());

            //
            List<WebElement> productEditLinkElems = cartItemRowElem.findElements(productEditLinkSel);
            String productEditLink = productEditLinkElems.isEmpty()? null : productEditLinkElems.get(0).getText();
            CartItemRowData carItemData = new CartItemRowData(
                    productImgScr,productName,productNameLink,productAttributes,productEditLink,
                    productPrice,productQuantity,productSubTotal
            );
            cartItemRowData.add(carItemData);

        }
        return cartItemRowData;
    }

    public static class CartItemRowData{
        private final String imgScr;
        private final String productName;
        private final String productName_link;
        private final String productAttributes;
        private final String productEditLink;
        private final double price;
        private final double quantity;
        private final double subTotal;

        public CartItemRowData(String imgScr, String productName, String productName_link, String productAttributes,
                               String productEditLink, Double price, Double quantity, Double subTotal) {
            this.imgScr = imgScr;
            this.productName = productName;
            this.productName_link = productName_link;
            this.productAttributes = productAttributes;
            this.productEditLink = productEditLink;
            this.price = price;
            this.quantity = quantity;
            this.subTotal = subTotal;
        }
        public String getImgScr() {
            return imgScr;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductName_link() {
            return productName_link;
        }

        public String getProductAttributes() {
            return productAttributes;
        }

        public String getProductEditLink() {
            return productEditLink;
        }

        public double getPrice() {
            return price;
        }

        public double getQuantity() {
            return quantity;
        }

        public double getSubTotal() {
            return subTotal;
        }

    }
}
