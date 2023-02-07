package _01;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {

		FileInputStream input = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringComments(true);
			dbf.setCoalescing(true);

			DocumentBuilder db = dbf.newDocumentBuilder();

			input = new FileInputStream("src/_01/alumnos.xml");
			Document xmlTree = db.parse(input);

			Element root = xmlTree.getDocumentElement();
			root.normalize();

			NodeList alumnos = root.getElementsByTagName("alumno");
			int numAlumnos = alumnos.getLength();
			
			double sumaNotas = 0;
			
			for (int i = 0; i < numAlumnos; i++) {
				Element alumno = (Element) alumnos.item(i);
				Element nombreElement = (Element) alumno.getElementsByTagName("nombre").item(0);
				Element programacionElement = (Element) alumno.getElementsByTagName("programacion").item(0);
				Element accesoElement = (Element) alumno.getElementsByTagName("acceso").item(0);
				
				String nombre = nombreElement.getTextContent();
				double notaProgramacion = Double.parseDouble(programacionElement.getTextContent());
				double notaAcceso = Double.parseDouble(accesoElement.getTextContent());
				
				double notaMedia = (notaProgramacion + notaAcceso) / 2;
				
				sumaNotas += notaMedia;
				
				System.out.println("Alumno " + nombre + " = " + notaMedia);
			}
			
			System.out.println("Media de la clase = " + (sumaNotas / numAlumnos));

		} catch (Exception e) {
			System.out.println("ERROR: Al parsear el documento XML: " + e.toString());
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (Exception e) {
			}
		}
	}

}
