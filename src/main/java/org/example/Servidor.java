package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        final String[] pimpinellaCliente ={"¿Quién es?","¿Qué vienes a buscar?","Ya es tarde","Porque ahora soy yo la que quiere estar sin ti"};
        final String[] pimpinellaServidor ={"Soy yo","A ti","¿Por qué?","Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta"};
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        boolean activo = true;
        final int PUERTO = 25000;
        int cont=0;

        try (ServerSocket servidor=new ServerSocket(PUERTO)){

            while(activo){
                cliente=servidor.accept();
                entrada = new DataInputStream(cliente.getInputStream());
                salida = new DataOutputStream(cliente.getOutputStream());

                while(activo){

                    String mensajeCliente= entrada.readUTF();

                    System.out.println(mensajeCliente+"----"+pimpinellaCliente[cont]);

                    if(mensajeCliente.equals(pimpinellaCliente[cont])){

                        if(cont== pimpinellaServidor.length) activo=false;
                        salida.writeUTF(pimpinellaServidor[cont]);
                        cont++;

                    }else{
                        salida.writeUTF("Error >:c... ¡" + (cont>0?pimpinellaServidor[cont-1]:"Empezamos bien...")+"!");
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}