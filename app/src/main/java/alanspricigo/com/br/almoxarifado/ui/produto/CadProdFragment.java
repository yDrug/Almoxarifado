package alanspricigo.com.br.almoxarifado.ui.produto;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import alanspricigo.com.br.almoxarifado.R;
import alanspricigo.com.br.almoxarifado.model.Dados;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadProdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadProdFragment extends Fragment implements View.OnClickListener, Response.ErrorListener, Response.Listener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    //Entradas da tela

    private EditText etName;
    private EditText etCod;
    private CalendarView cvDataEntrada;
    private EditText etFabr;
    private Button buttonCad;

    //volley
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    public CadProdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadProdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadProdFragment newInstance(String param1, String param2) {
        CadProdFragment fragment = new CadProdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_prod, container, false);
        //Binding - Conectando as entradas dos objetos da tela com os componentes XML
        this.etName = view.findViewById(R.id.etName);
        this.etCod = view.findViewById(R.id.etCod);
        this.cvDataEntrada = view.findViewById(R.id.cvDataEntrada);
        this.etFabr = view.findViewById(R.id.etFabr);
        this.buttonCad = view.findViewById(R.id.buttonCad);
        this.buttonCad.setOnClickListener(this);



        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
//inicializando a fila de requests do SO
        this.requestQueue.start();

        return view;
    }
    @Override
    public void onClick(View v) {
        if (view.getId() == R.id.buttonCad)
        {
            try {
                //Instacía objeto de negócio (Dados sendo coletados das minhas entradas de informações)
                Dados dados = new Dados();
                //Coloca dados da tela no objeto
                dados.setNomeProd(this.etName.getText().toString());
                dados.setCodProd(this.etCod.getText().toString());
                // Formato para transformar data do CalendarView em String
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataSelecionada = sdf.format(new Date(cvDataEntrada.getDate()));
                dados.setFabrProd(this.etFabr.getText().toString());
                jsonObjectReq = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2:8080/seg/cadusuario.php",
                        dados.toJsonObject(), this, this);
                requestQueue.add(jsonObjectReq);
                Toast.makeText(view.getContext(), "Cadastro com sucesso.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void onErrorResponse(VolleyError error)
    {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();

    }
    @Override
    public void onResponse(Object response) {
        try {
            //instanciando objeto para manejar o JSON que recebemos
            JSONObject json = new JSONObject(response.toString());
            Context context = view.getContext();
            //pegando mensagem que veio do json
            CharSequence mensagem = json.getString("message");
            //duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
            //verificando se salvou sem erro para limpar campos da tela
            if (json.getBoolean("success")){
//limpar campos da tela
                this.etName.setText("");
                this.etCod.setText("");
                this.etFabr.setText("");
                Calendar calendar = Calendar.getInstance();
                long today = calendar.getTimeInMillis();
                this.cvDataEntrada.setDate(today, false, true);
            }
//mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }