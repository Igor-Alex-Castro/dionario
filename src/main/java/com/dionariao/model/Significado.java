package com.dionariao.model;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

import jakarta.persistence.Entity;

@Entity(name = "tb_significado")
public class Significado extends baseDescricao {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String entrada = scanner.nextLine();
		String saida = organizarEstoque(entrada);

		System.out.println(saida);

		scanner.close();
	}

	public static String organizarEstoque(String entrada) {
		// TODO: Crie um mapa (LinkedHashMap) para armazenar o total de cada código
		// mantendo a ordem de aparição
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

		String[] lojas = entrada.split(",");

		for (String loja : lojas) {
			String[] partes = loja.split(":");
			String codigo = partes[0].trim();
			int quantidade = Integer.parseInt(partes[1].trim());

			if (map.containsKey(codigo)) {

				map.put(codigo, map.get(codigo) + quantidade);
			} else {
				map.put(codigo, quantidade);

			}
			// TODO: Atualize a quantidade total no mapa (soma com o valor atual, se já
			// existir)
		}

		StringBuilder sb = new StringBuilder();

		// TODO: Itere sobre os pares do mapa e monta a string no formato
		// "codigo:quantidade"
		Set<String> chaves = map.keySet();
	
		

		for (String chave : chaves) {
			Integer valor = map.get(chave);
			sb.append(chave + ":" + valor + ",");
			
			
			
		}
		
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

}
