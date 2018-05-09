package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductDetails {

	 private final StringProperty Productname;
	    private final StringProperty Count;
	    private final StringProperty Price;
	    private final StringProperty TypeID;
	    private final StringProperty Income;

	    //Default constructor
	    public ProductDetails(String ProductProductname, String Count, String Price,String TypeID, String Income) {
	        this.Productname = new SimpleStringProperty(ProductProductname);
	        this.Count = new SimpleStringProperty(Count);
	        this.Price = new SimpleStringProperty(Price);
	        this.TypeID = new SimpleStringProperty(TypeID);
	        this.Income = new SimpleStringProperty(Income);
	    }

	    //Getters
	    public String getProductname() {
	        return Productname.get();
	    }

	    public String getCount() {
	        return Count.get();
	    }

	    public String getPrice() {
	        return Price.get();
	    }
	    
	    public String getTypeID() {
	    	return TypeID.get();
	    }
	    public String getIncome() {
	        return Income.get();
	    }

	    //Setters
	    public void setProductname(String value) {
	        Productname.set(value);
	    }

	    public void setCount(String value) {
	        Count.set(value);
	    }

	    public void setPrice(String value) {
	        Price.set(value);
	    }
	    public void setTypeID(String value) {
	    	TypeID.set(value);
	    }
	    public void setIncome(String value) {
	    	Income.set(value);
	    }

	    //Property values
	    public StringProperty ProductnameProperty() {
	        return Productname;
	    }

	    public StringProperty CountProperty() {
	        return Count;
	    }

	    public StringProperty PriceProperty() {
	        return Price;
	    }
	    public StringProperty TypeID() {
	    	return TypeID;
	    }
	    public StringProperty Income() {
	    	return TypeID;
	    }
	
	
}
