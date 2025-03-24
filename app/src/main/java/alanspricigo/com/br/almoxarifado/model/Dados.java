package alanspricigo.com.br.almoxarifado.model;

import android.util.Log;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Dados
{
    //atributos
    private String NomeProd;
    private String CodProd;
    private String DataEntr;
    private String FabrProd;

    //métodos
    public String getNomeProd()
    {
        return this.NomeProd;
    }
    public void setNomeProd(String Np)
    {
        if (Np.length() > 3) //Limitada para que o nome do produto tenha pelo menos 4 letras.
        {
            this.NomeProd = Np;
        }
            else
            {
                this.NomeProd = "Nome Inválido";
            }
    }

    public String getCodProd()
    {
        return this.CodProd;
    }
    public void setCodProd(String Cp)
    {
        if (Cp.length() > 3) //Limitada para que o código do produto tenha pelo menos 4 caracteres.
        {
            this.CodProd = Cp;
        }
        else
        {
            this.CodProd = "Código Inválido";
        }
    }
    public String getDataEntr()
    {
        return this.DataEntr;
    }
    public void setDataEntr(String Dn)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date data = (Date) formato.parse(Dn);
            this.DataEntr = Dn;
        } catch (ParseException e)
        {
            this.DataEntr = "1900-01-01";
        }
    }

    public String getFabrProd()
    {
        return this.FabrProd;
    }
    public void setFabrProd(String Fp)
    {
        FabrProd = Fp;
    }
    public Dados (JSONObject jp) {
        try {
            this.setNomeProd(jp.getString("NomeProd"));
            this.setCodProd(jp.getString("CodProd"));
            this.setDataEntr(jp.getString("DataEntr"));
            this.setFabrProd(jp.getString("FabrProd"));
        } catch (Exception e)
        {
            Log.e("Dados", Objects.requireNonNull(e.getMessage()));
        }
    }
}
