package alanspricigo.com.br.almoxarifado.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Dados
{
    //atributos
    private String nomeProd;
    private String codProd;
    private String dataEntr;
    private String fabrProd;
    //métodos
    public String getNomeProd()
    {
        return this.nomeProd;
    }
    public void setNomeProd(String Np)
    {
        if (Np.length() > 3) //Limitada para que o nome do produto tenha pelo menos 4 letras.
        {
            this.nomeProd = Np;
        }
            else
            {
                this.nomeProd = "Nome Inválido";
            }
    }
    public String getCodProd()
    {
        return this.codProd;
    }
    public void setCodProd(String Cp)
    {
        if (Cp.length() > 3) //Limitada para que o código do produto tenha pelo menos 4 caracteres.
        {
            this.codProd = Cp;
        }
        else
        {
            this.codProd = "Código Inválido";
        }
    }
    public String getDataEntr()
    {
        return this.dataEntr;
    }
    public void setDataEntr(String Dn)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date data = (Date) formato.parse(Dn);
            this.dataEntr = Dn;
        } catch (ParseException e)
        {
            this.dataEntr = "1900-01-01";
        }
    }
    public String getFabrProd()
    {
        return this.fabrProd;
    }
    public void setFabrProd(String Fp)
    {
        fabrProd = Fp;
    }
    public Dados (JSONObject jp) {
        try {
            this.setNomeProd(jp.getString("nome_prod"));
            this.setCodProd(jp.getString("cod_prod"));
            this.setDataEntr(jp.getString("data_prod"));
            this.setFabrProd(jp.getString("fabr_prod"));
        } catch (Exception e)
        {
            Log.e("Dados", Objects.requireNonNull(e.getMessage()));
        }
    }
    public Dados () {
        this.setNomeProd("");
        this.setCodProd("");
        this.setDataEntr("1970-01-01");
        this.setFabrProd("");
    }
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("NomeProd", this.nomeProd);
            json.put("CodProd", this.codProd);
            json.put("DataEntr", this.dataEntr);
            json.put("FabrProd", this.fabrProd);
        } catch (JSONException e)
        {
            Log.e("Dados", Objects.requireNonNull(e.getMessage()));
        }
        return json;
    }

}
