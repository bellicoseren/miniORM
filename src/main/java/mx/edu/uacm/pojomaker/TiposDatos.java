package mx.edu.uacm.pojomaker;

public class TiposDatos {
	 
	//Convierte los tipos de datos de postgres a tipos de datos java
	String convertirTipoDato(String x) {
		String c=null;
		if (x.equals("varchar") || x.equals("text"))
			c="String";
		else if (x.equals("int4") || x.equals("int2") || x.equals("int28") || x.equals("int8") )
			c="int";
		else if (x.equals("date") || x.equals("datetime"))
			c="Date";
		else if (x.equals("float8") || x.equals("float4") || x.equals("numeric"))
			c="Double";
		else if (x.equals("char"))
			c="char";
		else if (x.equals("bool"))
			c="boolean";
		else
			c="String";
		return c;
	}
}
