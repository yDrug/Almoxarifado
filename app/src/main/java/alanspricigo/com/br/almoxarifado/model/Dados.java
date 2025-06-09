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
    private int qtdProd;
    private String codProd;
    private String dataEntr;
    private double vlProd;
    //métodos
    public int getQtdProd()
    {
        return this.qtdProd;
    }
    public void setQtdProd(int Np)
    {
        this.qtdProd = Np;
    }
    public String getCodProd()
    {
        return this.codProd;
    }
    public void setCodProd(String Cp)
    {
            this.codProd = Cp;

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
    public double getVlProd()
    {
        return this.vlProd;
    }
    public void setVlProd(double Fp)
    {
        vlProd = Fp;
    }
    public Dados (JSONObject jp) {
        try {
            this.setQtdProd(jp.getInt("qtEntrada"));
            this.setCodProd(jp.getString("idProduto"));
            this.setDataEntr(jp.getString("dtEntrada"));
            this.setVlProd(jp.getDouble("vlEntrada"));
        } catch (Exception e)
        {
            Log.e("Dados", Objects.requireNonNull(e.getMessage()));
        }
    }
    public Dados () {
        this.setQtdProd(0);
        this.setCodProd("");
        this.setDataEntr("1970-01-01");
        this.setVlProd(0);
    }
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("qtEntrada", this.qtdProd);
            json.put("idProduto", this.codProd);
            json.put("dtEntrada", this.dataEntr);
            json.put("vlEntrada", this.vlProd);
        } catch (JSONException e)
        {
            Log.e("Dados", Objects.requireNonNull(e.getMessage()));
        }
        return json;
    }

}
