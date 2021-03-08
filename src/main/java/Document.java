

import lombok.Data;

import java.util.List;
@Data
public class Document {
    String paymentUrl;
    String productName;
    List<String> imageUrls;
    String productDetails;
    String oldPrice;
    String newPrice;
    String vendorProductCode;
    String brandName;
    List<Category> categories;
    Store store;
    Colour color;
    boolean available=true;

    public Document(String paymentUrl, String productName, List<String> imageUrls, String productDetails, String newPrice,String brandName, List<Category> categories, Store store) {
        this.paymentUrl = paymentUrl;
        this.productName = productName;
        this.imageUrls = imageUrls;
        this.productDetails = productDetails;
        this.newPrice = newPrice;
        this.brandName = brandName;
        this.categories = categories;
        this.store = store;
    }
}
