package es.uca.gii.iw.crusaito.clases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CiudadTest {
	
	@Test
    public void Test(){
		
        Ciudad c = new Ciudad("Cádiz", "De Cádiz, el mar, su gente, su bahía, su historia y sus ganas de vivir.\n" + 
        		"\n" + 
        		"La provincia de Cádiz es muy diversa, con comarcas que merecen ser visitadas, desde la campiña de Jerez de la Frontera a sus pueblos del Campo de Gibraltar o un recorrido por los pueblos blancos y descansar en algún lugar de la costa entre Tarifa hasta Sanlúcar de Barrameda.\n" + 
        		"\n" + 
        		"Todo ello sin olvidar su capital, que guarda en sus murallas la cultura y las tradiciones que la hacen distinta, como a sus gentes.\n" + 
        		"\n" + 
        		"No lo dudes, Cádiz es el destino ideal para tus vacaciones.");

        assertEquals("Cádiz", c.getcNombre());
        assertEquals("De Cádiz, el mar, su gente, su bahía, su historia y sus ganas de vivir.\\n\" + \n" + 
        		"        		\"\\n\" + \n" + 
        		"        		\"La provincia de Cádiz es muy diversa, con comarcas que merecen ser visitadas, desde la campiña de Jerez de la Frontera a sus pueblos del Campo de Gibraltar o un recorrido por los pueblos blancos y descansar en algún lugar de la costa entre Tarifa hasta Sanlúcar de Barrameda.\\n\" + \n" + 
        		"        		\"\\n\" + \n" + 
        		"        		\"Todo ello sin olvidar su capital, que guarda en sus murallas la cultura y las tradiciones que la hacen distinta, como a sus gentes.\\n\" + \n" + 
        		"        		\"\\n\" + \n" + 
        		"        		\"No lo dudes, Cádiz es el destino ideal para tus vacaciones.", c.getcDescripcion());
   
    }
}
