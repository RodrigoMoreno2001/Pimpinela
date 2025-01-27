package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
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
                System.out.println(respuesta);
                if(respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) activo=false;

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String listarOpciones(String[] arr, Scanner ints){
        int opcion=0;
        do{
            System.out.println(opcion<0||opcion>=arr.length?"ELIGE UNA OPCION VÁLIDA:":"Elige una opción:");
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