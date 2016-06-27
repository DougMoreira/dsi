package com.example.andr;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import modelo.entidade.Comando;
import modelo.entidade.Dispositivo;


import android.os.AsyncTask;

public class SocketTask extends AsyncTask<String, String, Boolean> {

	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private String enderecoServidor;
	private int portaServidor;
	private int timeout;
	private String mac;

	public SocketTask(String enderecoServidor, int portaServidor, int timeout, String mac){
		this.enderecoServidor = enderecoServidor;
		this.portaServidor = portaServidor;
		this.timeout = timeout;
		this.mac = mac;
	}

	public void sendData(String data) throws IOException {
		if (socket != null && socket.isConnected()) {
			os.write(data.getBytes());
		}
	}

	@Override
	protected Boolean doInBackground(String... params) {
		boolean estadoConexao = false;
		try {
			SocketAddress sockaddr = new InetSocketAddress(enderecoServidor, portaServidor);
			socket = new Socket();
			socket.connect(sockaddr, timeout);

			Dispositivo dispositivo = new Dispositivo(this.mac, 12345);

			Comando comando = new Comando();
			comando.setParametros("firefox");

			if(socket.isConnected()){
				estadoConexao = true;
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(dispositivo);
					oos.flush();
					
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(comando);
					oos.flush();

				} catch(Exception e) {
					
				} finally{ 
					oos.close(); 
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return estadoConexao;
	}

}
