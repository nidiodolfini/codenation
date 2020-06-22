package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		ArrayList<Integer> fibos = new ArrayList<>();
		int retorno = 0;
		for (int i = 0; i < 30; i++) {
			retorno = fibo(i);
			fibos.add(retorno);
			if (retorno >= 350){
				break;
			}
		}

		return fibos;
	}
	static int fibo(int n) {
		if (n < 2) {
			return n;
		} else {
			return fibo(n - 1) + fibo(n - 2);
		}
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> retorno = fibonacci();
		for(Integer ret: retorno){
			if (ret.equals(a)){
				return true;
			}
		}
		return false;
	}


}