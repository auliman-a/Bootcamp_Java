import java.math.BigDecimal;

public class Product {
	private int ProductID;
	private String Name;
	private String Description;
	private String Category;
	private BigDecimal Price;
	
	public Product(){}
	
	public Product(String name, String desc, String category, BigDecimal price)
	{
		this.setName(name);
		this.setDescription(desc);
		this.setPrice(price);
		this.setCategory(category);
	}
	
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
}
