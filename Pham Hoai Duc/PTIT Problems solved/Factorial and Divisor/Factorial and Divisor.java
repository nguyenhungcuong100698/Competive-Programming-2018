
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


public class PTITProblemJ {

    public static int[] generatePrimes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, 2, n + 1, true);
        for (int i = 2; i * i <= n; i++)
            if (prime[i])
                for (int j = i * i; j <= n; j += i) 
                    prime[j] = false;           
        int[] primes = new int[n + 1];
        int cnt = 0;
        for (int i = 0; i < prime.length; i++)
            if (prime[i])
                primes[cnt++] = i;
        return Arrays.copyOf(primes, cnt);
    }
    public static void main(String[] args) {
        InputReader ip = new InputReader(System.in);
        int t = ip.nextInt();
        int[]a = new int[t];
        int[]b = new int[t];
        HashSet<Integer> factorialList = new HashSet<>();
        int maxFactorial = 0;
        //tìm giai th?a l?n nh?t trong b? testcases. 
        for(int i = 0; i < t; i++)
        {
            a[i] = ip.nextInt();
            b[i] = ip.nextInt();
            factorialList.add(a[i]);
            factorialList.add(b[i]);
            if(b[i] > maxFactorial)
                maxFactorial = b[i]; 
        }
        //n?u giai th?a l?n nh?t là 0, thì toàn b? result là 1, return.
        if(maxFactorial == 0) 
        {
            for (int i = 0; i < t; i++)
                System.out.println(1);
            return;
        }     
        //tìm t?t c? các s? nguyên t?, mà giai th?a l?n nh?t có th? phân tích ra dc.
        int[] Primes = generatePrimes(maxFactorial);       
        //t?o m?ng 2 chi?u, luu tr? k?t qu? phân tích th?a s? ng t? cho t?ng giai th?a.
        int[][]dynamicProgramming = new int[maxFactorial + 1][maxFactorial + 1];
        //phân tích th?a s? ng t? cho t?ng giai th?a.
        int primesLength = Primes.length;
        for(int factorial = 1; factorial <= maxFactorial; factorial++)
            if (factorialList.contains(factorial)) 
                for (int prime = 0; prime < primesLength; prime++) {
                    int current = Primes[prime];
                    while (true) {
                        int NumbersOfCurrentPrimeFactor = factorial / current;
                        if (NumbersOfCurrentPrimeFactor == 0)
                            break;
                        dynamicProgramming[factorial][Primes[prime]] += NumbersOfCurrentPrimeFactor;
                        current *= Primes[prime];
                    }
                }
        StringBuilder builder = new StringBuilder();
        //tinh t?ng s? u?c cho t?ng b!/a!
        for(int i = 0; i < t; i++)
        {
            if(b[i] == 0)
            {
                System.out.println(1);
                continue;
            }
            long totalNumbersOfDivisor = 1;
            for(int prime = 0; prime < primesLength; prime++)
            {
                if(dynamicProgramming[b[i]][Primes[prime]] == 0)
                    break;
                int numbersOfCurrentPrimeDivisor = dynamicProgramming[b[i]][Primes[prime]] - dynamicProgramming[a[i]][Primes[prime]]; 
                totalNumbersOfDivisor *= (numbersOfCurrentPrimeDivisor + 1);
            }
            builder.append(totalNumbersOfDivisor+"\n");
        }          
        System.out.print(builder);
    }
	static class InputReader {
		StringTokenizer tokenizer;
		BufferedReader reader;
		String token;
		String temp;

		public InputReader(InputStream stream) {
			tokenizer = null;
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public InputReader(FileInputStream stream) {
			tokenizer = null;
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public String nextLine() throws IOException {
			return reader.readLine();
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					if (temp != null) {
						tokenizer = new StringTokenizer(temp);
						temp = null;
					} else {
						tokenizer = new StringTokenizer(reader.readLine());
					}

				} catch (IOException e) {
				}
			}
			return tokenizer.nextToken();
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}

