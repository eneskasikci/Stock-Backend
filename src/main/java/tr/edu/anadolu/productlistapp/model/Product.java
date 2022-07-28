package tr.edu.anadolu.productlistapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ApiModel(value = "Product Model", description = "Product Class Variables")
public class Product {

    @Id
    @ApiModelProperty(value = "ID of the Product")
    private String productId;
    @ApiModelProperty(value = "Name of the Product")
    private String productName;
    @ApiModelProperty(value = "Price of the Product")
    private double productPrice;
    @ApiModelProperty(value = "Type of the Product")
    private String productType;
    @ApiModelProperty(value = "Category of the Product")
    private String productCategory;
    @ApiModelProperty(value = "Image of the Product")
    private String productImage;
    @ApiModelProperty(value = "Availability of the Product")
    private boolean availability;

}
