package com.example.andr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.os.AsyncTask;

public class SocketTask extends AsyncTask<String, String, Boolean> {

	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private String enderecoServidor;
	private int portaServidor;
	private int timeout;

	public SocketTask(String enderecoServidor, int portaServidor, int timeout){
		this.enderecoServidor = enderecoServidor;
		this.portaServidor = portaServidor;
		this.timeout = timeout;
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

			if(socket.isConnected()){
				estadoConexao = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return estadoConexao;
	}

}
