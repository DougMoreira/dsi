package com.example.andr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class SaudacaoActivity extends Activity {
	public static final String EXTRA_NOME_USUARIO = "helloandroid.EXTRA_NOME_USUARIO";
	public static final String ACAO_EXIBIR_SAUDACAO = "helloandroid.ACAO_EXIBIR_SAUDACAO";
	public static final String CATEGORIA_SAUDACAO = "helloandroid.CATEGORIA_SAUDACAO";
	private String mac;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saudacao);
		
		 WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	        WifiInfo wInfo = wifiManager.getConnectionInfo();
	        mac = wInfo.getMacAddress();

		TextView saudacaoTextView = (TextView) findViewById(R.id.saudacaoTextView);
		Intent intent = getIntent();
		if (intent.hasExtra(EXTRA_NOME_USUARIO)) {
			String saudacao = getResources().getString(R.string.saudacao);
			saudacaoTextView.setText(saudacao + " "
					+ intent.getStringExtra(EXTRA_NOME_USUARIO) + "\n"
							+ "Mac Adress: " + mac);
		} else {
			saudacaoTextView.setText("O nome do usuário não foi informado");
		}
	}
}
