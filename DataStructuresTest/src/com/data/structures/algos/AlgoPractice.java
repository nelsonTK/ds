package com.data.structures.algos;

import com.data.structures.utils.PrintHelper;

/**
 * 1) ver como o algoritmo funciona em termos teóricos
 * 2) tentar implementar sem ver qualquer implementação a não ser teórica
 * 3) se não perceber ver teoria sobre o assunto
 * 4) se não conseguir ir ver como outros implementar por código de alto nível
 * 5) fazer a minha própria solução sem ir ver, rejeitar pensamentos biased e fazer o meu próprio raciocinio
 * 6) não ficar preso às soluções que vi alguém fazer
 * 7) tentar compreender mais do que qualquer coisa, não me deixar enganar pela falsa impressão de que estou a perceber porque estou a seguir a forma de fazer de outra pessoa
 * 8) Tirar notas
 * 9) implementar os algoritmos pelo menos uma vez
 * 10) usar tabelas para perceber como atacar os algoritmos (Documentar essas tabelas)
 * @author Nelson Costa
 *
 */
public class AlgoPractice {

	/**
	 * O Bubble sort vai percorrer o array de tamanho n, n vezes com dois apontadores i e i+1, ou i e j
	 * O Bubble sort precisa sempre percorrer o array uma vez sem ter alterações efetuadas para saber que terminou
	 * 
	 * @param array
	 */
	public static void bubblesort(int [] array) {
		int i = 0;
		int j = i + 1;
		int temp = 0;
		int changeCounter = 1;

		if (array.length > 1) {
			while (changeCounter > 0) {
				changeCounter = 0;

				while (j < array.length)
				{
					if(array[i] > array[j]) {

						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
						changeCounter++;
					}

					i++;
					j++;
				}
				i = 0;
				j = i + 1;
			}

		}
		else
		{
			System.out.println("The array is sorted.");
		}
	}

	public static void permutation(String string) {
		StringBuilder s = new StringBuilder(string);
		permutation(s, 0, string.length() - 1);
	}

	private static void permutation(StringBuilder string, int start, int end) {

		if (start - end == 0) {
			System.out.println(string);
			return;
		}

		for (int i = start; i <= end; i++) {

			swap(string, start, i); //branching
			permutation(string, start + 1, end); //permuting branch
			swap(string, start, i);
		}


	}


	private static void swap(StringBuilder string, int a, int b) {
		char tmp = string.charAt(a);
		string.setCharAt(a, string.charAt(b));
		string.setCharAt(b, tmp);
	}

	
}
