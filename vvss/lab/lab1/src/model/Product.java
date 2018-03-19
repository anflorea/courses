package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Product {
	private int code=0;
	private String name=null;
	private String category=null;
	private String supplier=null;
	private int quantity=0;
	
	public Product(int code, String name, String category, int quantity) {
		super();
		this.code = code;
		this.name = name;
		this.category = category;
		this.quantity = quantity;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean compareTo(Product q){
		if(name.compareTo(q.getName())!=0) return false;
		if(category.compareTo(q.getCategory())!=0) return true;
		if(quantity!=q.getQuantity()) return false;
		if(code==q.getCode()) return true;
		return true;
	}
	public void read() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("code=");
		String scode = br.readLine();
		System.out.println("name=");
		this.name = br.readLine();
		System.out.println("category=");
		this.category = br.readLine();
		System.out.println("quantity=");
		String squantity = br.readLine();
		try{ 
			this.quantity=Integer.parseInt(squantity);
		}
		catch(Exception e){
			this.quantity=-1;
			System.err.print("Quantity not >=0");
			
		}
		
		try{ 
			this.code=Integer.parseInt(scode);
		}
		catch(Exception e){			
			System.err.print("error Code not >0");
			
		}
		
	}
	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", category="
				+ category + ", quantity=" + quantity + "]";
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	

}
