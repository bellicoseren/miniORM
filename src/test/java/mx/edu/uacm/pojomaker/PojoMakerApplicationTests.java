package mx.edu.uacm.pojomaker;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.xml.XmlMapper;

import net.projectmonkey.object.mapper.ObjectMapper;

@SpringBootTest
class PojoMakerApplicationTests {
	
	private static final Logger log = 
			LogManager.getLogger(PojoMakerApplicationTests.class);
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void contextLoads() throws SQLException {
		log.debug("Entrando al metodo");
		DatabaseMetaData md = 
				dataSource.getConnection().getMetaData();
		String[] tipos = {"TABLE"};

		ResultSet rs = md.getTables("postgres",  null, "%", tipos);//(null, null, "%", null);
		CreadorPojo c = new CreadorPojo();
		TiposDatos t = new TiposDatos();
		while(rs.next()) {
			log.debug(rs.getString(3));
			ResultSet rs2 = md.getColumns("postgres", null,rs.getString(3), null);
			ResultSet rsp = md.getPrimaryKeys("postgres", null, rs.getString(3));
			String llaveP = null;
			while (rsp.next()) {
				System.out.println("Primary Key: "+rsp.getString(4));
				llaveP = rsp.getObject(4).toString();
			}
			ArrayList columnas = new ArrayList();
			while (rs2.next()) {
				columnas.add(rs2.getString("COLUMN_NAME"));
				columnas.add(t.convertirTipoDato(rs2.getString("TYPE_NAME")));
			   String nombreColumna = rs2.getString("COLUMN_NAME");
			   String tipoColumna = rs2.getString("TYPE_NAME");
			   System.out.println(" COLUMNA, nombre=" + nombreColumna
			      + " tipo = " + tipoColumna);
			}
			c.crearPojo(rs.getString(3), columnas, llaveP);
		}
		
		
	}
}


