package music.system.SystemClasses;
 
public class RepairItem {
	private int repairId;
	private String name;
	private String description;
	private double fixPrice;

	//Constructor
	public RepairItem (String name, String description, double fixPrice) {
		repairId = generateRepairId();
		this.name = name;
		this.description = description;
		this.fixPrice = fixPrice;
	}

	//generate a RepairId when technician enters item into system
	//return repairId
	private int generateRepairId() {
		return 0;
	}

	//get the repairId
	public int getRepairId() {
		return repairId;
	}

	//set the name of the repair
	public void setName(String name) {
		this.name = name;
	}

	//get the name of the repair
	public String getName() {
		return name;
	}

	//set the description
	public void setDescription(String description) {
		this.description = description;
	}

	//get the description
	public String getDescription() {
		return description;
	}

	//set the price of the fix
	public void setPrice(double fixPrice) {
		//check if the price is below zero, if it is then reprompt user
		this.fixPrice = fixPrice;
	}

	//get the price
	public double getPrice() {
		return fixPrice;
	}

}
