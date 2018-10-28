import java.util.ArrayList;
import java.util.Random;

public class FarmProvider {
	public static ArrayList<FarmId> listoffarms;
public FarmProvider() {
	// TODO Auto-generated constructor stub
	listoffarms = new ArrayList<>();
	
}
	public static FarmId getFreeFarm() {
		
		Random random= new Random();
		
		return listoffarms.get(random.nextInt(listoffarms.size()));
		
		 
	}
	public void updateFarmList() {
		
	}
	
	

}
