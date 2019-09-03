package hackerhank.dequeue;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
	private static final int MIN_LIM = 0;
	private static final int MAX_PARAM_LIM = 100000;
	private static final int MAX_NUM_LIM = 10000000;

	/**
	 * Validate the Entries
	 */
	private static void validateEntry(int n, int m) {

		if ((n < MIN_LIM && n > MAX_PARAM_LIM) || (m < MIN_LIM && m > MAX_PARAM_LIM)) {
			throw new IllegalArgumentException("Invalid values for params");
		}
	}

	/**
	 * Validate the elements
	 */
	private static void validateArrayElement(int num) {

		if (num < MIN_LIM && num > MAX_NUM_LIM) {
			throw new IllegalArgumentException("Invalid values for elements");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();
		// amount element
		int n = in.nextInt();
		// subarray size
		int m = in.nextInt();

		validateEntry (n,m);
		Map<Integer,Integer> mapa = new HashMap<>();
		int maxValue = -1;

		for (int i = 0; i < n; i++) {
			int num = in.nextInt();
			validateArrayElement (num);
			if (deque.size() == m) {
				// O elemento removed sempre estará no Mapa
				//Processa a remoção
				Integer removed = deque.removeFirst();
				Integer ocorrenciaRemoved = mapa.get(removed);
				if (ocorrenciaRemoved > 1) {
					mapa.put (removed, mapa.get(removed)-1);
				} else {
					mapa.remove(removed);
				}
				//Fim remoção
				
				//Processa inclusão
				deque.addLast(num);
				updateMap(mapa, num);
				//Fim inclusão
			} else {
				deque.addLast(num);
				updateMap(mapa, num);
			}
			
			if (mapa.size() > maxValue) {
				maxValue = mapa.size();
			}

		}

		System.out.println(maxValue);

		in.close();
	}

	private static void updateMap(Map<Integer, Integer> mapa, int num) {
		Integer ocorrencia = mapa.get(num);
		if (ocorrencia == null) {
			mapa.put(num,1);
		} else {
			mapa.put (num, mapa.get(num)+1 );
		}
	}
}