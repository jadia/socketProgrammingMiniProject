import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class Myservice {

	public int generatePrime(String data) throws Exception {
		int input = Integer.parseInt(data.trim());

		return sieveOfEratosthenes(input);
	}

	public String getCpuUsages() {
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		return Double.toString(osBean.getSystemLoadAverage());
	}

	int sieveOfEratosthenes(int n) {
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i < n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}

		// Print all prime numbers
		for (int i = n; i >= 2; i--) {
			if (prime[i] == true)
				return i;

		}
		return 1;

	}
}
