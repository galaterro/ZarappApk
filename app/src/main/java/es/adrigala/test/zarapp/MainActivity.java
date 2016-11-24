package es.adrigala.test.zarapp;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   private Contador cont = null;
   private TextView text_grandes = null;
   private TextView text_peques = null;
    private Button boton_end = null;
    private Button boton_grandes = null;
    private Button boton_peques = null;
    private Button boton_reset = null;
    private Button boton_volver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Resources res = getResources();
        final String grandes = res.getString(R.string.textview_grandes);
        final String peques = res.getString(R.string.total_cajas_peque_as);

        boton_reset = (Button) findViewById(R.id.button_reset);
        text_grandes = (TextView) findViewById(R.id.textView);
        text_peques = (TextView) findViewById(R.id.textView2);
        cont = new Contador();
        boton_grandes = (Button) findViewById(R.id.button_grandes);
        boton_peques = (Button) findViewById(R.id.button_peque);
        boton_end = (Button) findViewById(R.id.button_end);
        boton_volver = (Button) findViewById(R.id.button_back);
        boton_volver.setEnabled(false);

        boton_volver.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (cont.getLast() == 1){
                    cont.setGrandes(cont.getGrandes() - 1);
                    cont.clearLastBox();
                    text_grandes.setText(grandes);
                    text_grandes.append(Integer.toString(cont.getGrandes()));
                }else{
                    cont.setPeques(cont.getPeques() - 1);
                    cont.clearLastBox();
                    text_peques.setText(peques);
                    text_peques.append(Integer.toString(cont.getPeques()));
                }
                boton_volver.setEnabled(cont.canUseHistory());
            }
        });

        boton_grandes.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                cont.setGrandes(cont.getGrandes() + 1);
                text_grandes.setText(grandes);
                text_grandes.append(Integer.toString(cont.getGrandes()));
                cont.setLast(1);
                boton_volver.setEnabled(true);
            }
        });

        boton_peques.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                cont.setPeques(cont.getPeques() + 1);
                text_peques.setText(peques);
                text_peques.append(Integer.toString(cont.getPeques()));
                cont.setLast(0);
                boton_volver.setEnabled(true);
            }
        });

        boton_reset.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Borrar")
                        .setMessage("¿Estás seguro que deseas reiniciar contadores?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                cont.setPeques(0);
                                cont.setGrandes(0);
                                text_peques.setText(peques);
                                text_peques.append(Integer.toString(cont.getPeques()));
                                text_grandes.setText(grandes);
                                text_grandes.append(Integer.toString(cont.getGrandes()));
                                cont.clearHistory();
                                boton_volver.setEnabled(false);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        boton_end.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                new AlertDialog.Builder(MainActivity.this)
                .setTitle("Resultados")
                .setMessage("Total de Cajas Grandes :" + cont.getGrandes() + "\n" + "Total de Cajas Pequeñas " + cont.getPeques() + "\n" + "Total Cajas: " + (cont.getPeques() + cont.getGrandes()))
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .create().show();

            }
        });




    }





}
