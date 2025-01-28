package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * Clase Cliente que implementa un cliente basado en sockets para interactuar con el servidor.
 */
public class Cliente {

    /**
     * Punto de entrada principal para iniciar el cliente.
     * El cliente se conecta a un servidor en un host y puerto específicos,
     * envía mensajes seleccionados por el usuario y recibe respuestas del servidor.
     */

    public static void main(String[] args) {
        String HOST="localhost";
        int PUERTO=25000;

        final String[] pimpinellaCliente ={"¿Quién es?","¿Qué vienes a buscar?","Ya es tarde","Porque ahora soy yo la que quiere estar sin ti"};
        boolean activo=true;
        Scanner ints=new Scanner(System.in);

        try (Socket sc=new Socket(HOST,PUERTO);){
            DataInputStream entrada = new DataInputStream(sc.getInputStream());
            DataOutputStream salida = new DataOutputStream(sc.getOutputStream());

            while(activo){

                salida.writeUTF(listarOpciones(pimpinellaCliente,ints));
                String respuesta=entrada.readUTF();
                System.out.println("Servidor: "+respuesta);
                if(respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) activo=false;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Muestra al usuario distintas opciones dadas por un array
     * y pide por teclado que elija una de las opciones
     *
     * @param arr  array de opciones disponibles.
     * @param ints objeto Scanner para leer la entrada del usuario.
     * @return la opción seleccionada por el usuario.
     */
    static String listarOpciones(String[] arr, Scanner ints){
        int opcion=0;
        do{
            System.out.println(opcion<0||opcion>=arr.length?"ELIGE UNA OPCION VALIDA:":"Elige una opción:");
            for(int i=0;i<arr.length;i++) System.out.println((i+1)+".-"+arr[i]);
            try{
                opcion=ints.nextInt()-1;
            }catch(Exception e){
                ints.nextLine();
                opcion=-1;
            }
        }while(opcion<0||opcion>=arr.length);

        return arr[opcion];
    }
}