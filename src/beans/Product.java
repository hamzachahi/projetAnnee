package beans;

import java.awt.Image;
import java.util.ArrayList;

public interface Product {
public String getCode();
public Boolean setCode(String newCode, Boolean sqlornot);
public String getName();
public Boolean setName(String newName, Boolean sqlornot);
public Double getPrice();
public Boolean setPrice(Integer newPrice, Boolean sqlornot);
public String getDescription();
public Boolean setDescription(String newDescription, Boolean sqlornot);
public Image getProductMainImage();
public Boolean setProductMainImage(Image newImage, Boolean sqlornot);
public ArrayList<Image> getProductListImage();
public Boolean setProductListImage(ArrayList<Image> listImage, Boolean sqlornot);
public ArrayList<Product> getListSubProduct();
public Boolean setListProduct(ArrayList<Product> listSubProduct, Boolean sqlornot);
}