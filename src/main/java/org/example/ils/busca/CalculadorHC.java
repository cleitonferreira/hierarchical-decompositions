package org.example.ils.busca;


import org.example.ils.core.Calculador;
import org.example.ils.core.Problema;

/**
 * DEFINIC?ES:
 * 
 * - acoplamento = n?mero de depend?ncias que as classes de um pacote possuem
 * com classes de fora do pacote. Deve ser minimizado.
 * 
 * - coes?o = n?mero de depend?ncias que as classes de um pacote possuem com
 * outras classes do mesmo pacote. Deve ser maximizado (ou seja, minimizamos seu
 * valor com sinal invertido)
 * 
 * - spread = partindo de zero e percorrendo cada pacote, acumula o quadrado da
 * diferen?a entre o n?mero de classes do pacote e o n?mero de classes do menor
 * pacote
 * 
 * - diferenca = diferen?a entre o n?mero m?ximo de classes em um pacote e o
 * n?mero m?nimo de classes em um pacote
 * 
 */
public class CalculadorHC extends Calculador {
	
	public CalculadorHC(Problema problema) {
		super(problema);
	}
	
}