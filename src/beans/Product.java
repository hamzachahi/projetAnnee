package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Product extends MotherProduct implements Salable {
	protected String type = "Produit";

	@Override
	public Boolean setId(Long newId, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setId(newId);
		return true;
	}

	@Override
	public Boolean setCode(String newCode, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setCode(newCode);
		return null;
	}

	@Override
	public Boolean setName(String newName, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setName(newName);
		return null;
	}

	@Override
	public Boolean setPrice(Double d, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setPrice(d);
		return null;
	}

	@Override
	public Boolean setDescription(String newDescription, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setDescription(newDescription);
		return null;
	}

	@Override
	public Boolean setMainImage(String newImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setMainImage(newImage);
		return null;
	}

	@Override
	public ArrayList<String> getProductListImage() {
		// TODO Auto-generated method stub
		return this.getProductListImage();
	}

	@Override
	public Boolean setProductListImage(ArrayList<String> listImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setProductListImage(listImage, false);
		return null;
	}

	@Override
	public Boolean setListProduct(ArrayList<Salable> listSubProduct, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setListProduct(listSubProduct, false);
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public Date getAddDate() {
		// TODO Auto-generated method stub
		return this.addDate;
	}

	@Override
	public Boolean setAddDate(Date date) {
		// TODO Auto-generated method stub
		this.addDate=date;
		return true;
	}

}
