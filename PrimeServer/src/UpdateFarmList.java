import java.io.IOException;
import java.net.Socket;

public class UpdateFarmList implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			for (int i = 0; i < FarmProvider.listoffarms.size(); i++) {
				FarmId farmId = FarmProvider.listoffarms.get(i);
				try {
					FarmSocket farmSocket = new FarmSocket(new Socket(farmId.ipaddress, farmId.port + 1));
	//				System.out.println("request send to farm for cpu usages");
					farmSocket.sendNumberToFarmtoFindPrimeNumber("cpu");
	///				System.out.println("request send to farm for cpu usages");
					String msg = farmSocket.receivePrimeFromFarm();
					System.out.println("Farm with ip = "+farmId.ipaddress.getHostAddress()+"and port numbrer = "+ farmId.port +" has cpu utilization = " + msg);
					
					farmSocket.closeFarm();
					farmId.cpuUtilisation = Double.parseDouble(msg.trim());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

}