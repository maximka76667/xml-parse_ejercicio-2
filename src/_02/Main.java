package _02;

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

			input = new FileInputStream("src/_02/vendedores.xml");
			Document xmlTree = db.parse(input);

			Element root = xmlTree.getDocumentElement();
			root.normalize();

			NodeList vendedores = root.getElementsByTagName("vendedor");			
			for (int i = 0; i < vendedores.getLength(); i++) {
				Element vendedor = (Element) vendedores.item(i);
				Element nombreElement = (Element) vendedor.getElementsByTagName("nombre").item(0);
				Element apellidosElement = (Element) vendedor.getElementsByTagName("apellidos").item(0);
				Element ventasElement = (Element) vendedor.getElementsByTagName("ventas").item(0);
				
				String nombre = nombreElement.getTextContent();
				String apellidos = apellidosElement.getTextContent();
				double ventas = Double.parseDouble(ventasElement.getTextContent());
				
				double comisiones = ventas * 0.07;
				
				System.out.println("Comercial " + nombre + " " + apellidos + ". Ventas = " + ventas + " Comision = " + comisiones);
			}
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
