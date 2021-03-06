package problembw2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemBW2 {

           public static String trimEnd(StringBuilder[] picture){
               StringBuilder result = new StringBuilder();
               for (int i = 0; i < picture.length; i++) 
                   result.append(picture[i].toString().replaceAll("\\s+$", "") + "\n");
               return result.toString();
           }
	   public static void main(String[] args) throws IOException {
               InputReader ip = new InputReader(System.in);
               StringBuilder result = new StringBuilder();
               int index = 0;
               int n = ip.nextInt();
               while (n != 0) {
                   if(index != 0)
                       result.append("\n");
                   String[] originalPicture = new String[n];
                   int maxOriginalRowLength = Integer.MIN_VALUE;
                   for (int i = 0; i < n; i++) {
                       originalPicture[i] = ip.nextLine();
                       maxOriginalRowLength = maxOriginalRowLength < originalPicture[i].length() ? originalPicture[i].length() : maxOriginalRowLength;
                   }
                   StringBuilder[] picture90Degrees = new StringBuilder[maxOriginalRowLength];
                   for(int i = 0; i < maxOriginalRowLength; i++)
                       picture90Degrees[i] = new StringBuilder();
                   for (int i = 0; i < maxOriginalRowLength; i++)
                       for (int j = n - 1; j >= 0; j--) 
                           picture90Degrees[i].append(i >= originalPicture[j].length()?' ':originalPicture[j].charAt(i) == '-' ? '|' : originalPicture[j].charAt(i) == '|' ? '-' : originalPicture[j].charAt(i));
                   result.append(trimEnd(picture90Degrees));
                   index++;
                   n = ip.nextInt();
               }
               System.out.print(result);
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
