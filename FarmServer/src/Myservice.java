import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class Myservice {

	public int generatePrime(int input) throws Exception {
		int i, m = 0, flag = 0, lastPrime = 0;
		for (int i1 = 1; i1 < input; i1++) {
			int n = i1;// it is the number to be checked
			m = n / 2;
			if (n == 0 || n == 1) {
				System.out.println(n + " is not prime number");
			} else {
				for (i1 = 2; i1 <= m; i1++) {
					if (n % i1 == 0) {
						System.out.println(n + " is not prime number");
						flag = 1;
						lastPrime = n;
						break;
					}
				}
				if (flag == 0) {
					System.out.println(n + " is prime number");
				}
			} // end of else
		}
		return (lastPrime);
	}

	public double getCpuUsages() {
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

		return osBean.getSystemLoadAverage();
	}

}
