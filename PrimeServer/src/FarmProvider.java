import java.util.ArrayList;
import java.util.Random;

public class FarmProvider {
	public static ArrayList<FarmId> listoffarms;
static int counter = 0;
	public FarmProvider() {
		// TODO Auto-generated constructor stub
		listoffarms = new ArrayList<>();

	}

	public synchronized static FarmId getFreeFarm() {
/*		double currentLeastUtilization = 0.0;
		int currentIndex = 0;
		for (int i = 0; i < listoffarms.size(); i++) {
			if (listoffarms.get(i).cpuUtilisation < currentLeastUtilization) {
				currentLeastUtilization = listoffarms.get(i).cpuUtilisation;
				currentIndex = i;
			}
		}
*/
		counter = (counter+1)%listoffarms.size();
		return listoffarms.get(counter);

	}

}
