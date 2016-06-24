package com.example.andr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private EditText nomeEditText;
	private TextView saudacaoTextView;
	private String saudacao;
	private EditText enderecoServidor;
	private EditText portaServidor;
	private SocketTask st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        this.nomeEditText = (EditText) findViewById(R.id.enderecoServidor);
        this.saudacaoTextView =
        (TextView) findViewById(R.id.saudacaoTextView);
        this.saudacao = getResources().getString(R.string.saudacao);
        
        
        
        

    }
    
    public void surpreenderUsuario(View v) {
    	Intent intent = new Intent(SaudacaoActivity.ACAO_EXIBIR_SAUDACAO);
    	
    	intent.addCategory(SaudacaoActivity.CATEGORIA_SAUDACAO);
    	String texto = nomeEditText.getText().toString();
    	intent.putExtra(SaudacaoActivity.EXTRA_NOME_USUARIO, texto);
    	startActivity(intent);
    	}
    
    public void login(View v){
    	 this.enderecoServidor = (EditText) findViewById(R.id.enderecoServidor);
    	 this.portaServidor = (EditText) findViewById(R.id.portaServidor);
    	 
    	 String servidor = enderecoServidor.getText().toString();
    	 int porta = Integer.parseInt(portaServidor.getText().toString());
    	 
    	 st = new SocketTask(servidor, porta, 10000);
    	 Toast.makeText(MainActivity.this, ":" + st.doInBackground().booleanValue(), Toast.LENGTH_LONG).show();
    	 st.execute();
    	
    	
    }
    
/* 1 */    
//    public void surpreenderUsuario(View v) {
//    	Editable texto = this.nomeEditText.getText();
//    	String msg = saudacao + " " + texto.toString();
//    	this.saudacaoTextView.setText(msg);
//    	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
