package mx.edu.uacm.pojomaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreadorPojo {
	
	
	void crearPojo(String tabla,ArrayList columnas, String llaveP) {
	try {
		 //Generar el archivo para el POJO
		String ruta = "/home/bellicose/eclipse-workspace/PojoMaker-main/src/main/java/mx/edu/uacm/pojomaker/"+tabla+".java";
        File archivo = new File(ruta);
		if(archivo.exists()) {
			archivo.delete();
		}
 
		  BufferedWriter out = new BufferedWriter(new FileWriter(archivo));
		  out.write("import javax.persistence.Entity;\n"
       		   +"import javax.persistence.Column;\n"
       		   +"import javax.persistence.Id;\n"
          		+ "\n@Entity\npublic class " + tabla+ "{\n\n");
		  
		  
		  for (int i=0;i<columnas.size();i=i+2) {
			  if(llaveP.equals(columnas.get(i)))
				  out.write("@Id\n");
			  out.write("@Column\n"+columnas.get(i+1)+" "+columnas.get(i)+";\n\n");
		  }
		  out.write("\n\n");
		  
		  for (int i=0;i<columnas.size();i=i+2) {
			  out.write("public "+columnas.get(i+1)+" get"+columnas.get(i)+"(){\n"
			  		+ "return "+columnas.get(i)+";\n}\n");
			  out.write("public void set"+columnas.get(i)+"("+columnas.get(i+1)+" "+columnas.get(i)+"){\n"
			  		+ "this."+columnas.get(i)+"="+columnas.get(i)+";\n}\n");
		  }
		  out.write("\n\n}");
		  out.close();
		                       
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
	}
	
	
}
