import java.io.IOException;
import java.net.Socket;

public class UpdateFarmList  {


	public void runMethod() {
		// TODO Auto-generated method stub
	
			for (int i = 0; i < FarmProvider.listoffarms.size(); i++) {
				FarmId farmId = FarmProvider.listoffarms.get(i);
				try {
					FarmSocket farmSocket = new FarmSocket(new Socket(farmId.ipaddress, farmId.port + 1));
	//				System.out.println("request send to farm for cpu usages");
					farmSocket.sendNumberToFarmtoFindPrimeNumber("cpu");
	///				System.out.println("request send to farm for cpu usages");
					String msg = farmSocket.receivePrimeFromFarm();
					System.out.println("Farm IP: "+farmId.ipaddress.getHostAddress()+", Port: "+ farmId.port +", CPU: " + msg);
					farmSocket.closeFarm();
					farmId.cpuUtilisation = Double.parseDouble(msg.trim());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

