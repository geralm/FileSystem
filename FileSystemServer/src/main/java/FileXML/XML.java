package FileXML;

import Tree.ANode;
import Tree.NodeDirectory;
import Tree.RootUser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XML implements IFile {

    public static void save(RootUser drive){
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        DOMImplementation implementation;
        Document document;
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder =factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null,drive.getUserName(),null);
            document.setXmlVersion("1.0");
            Element allTree = addSubordinates(document, drive.getRoot());
            document.getDocumentElement().appendChild(allTree);
            //Si es un directorio creo un Elemento y si es un archivo, es solo el nombre
            Source sourse = new DOMSource(document);
            Result result = new StreamResult(new File(drive.getUserName() + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(sourse, result);
            System.out.println("Documento "+drive.getUserName()+".xml "+" guardado exitosamente!");
        }catch(ParserConfigurationException | TransformerConfigurationException ex){
            System.out.println("\n\nError al crear archivo");
        } catch (TransformerException ex) {
            System.out.println("+Error al trasformar");
        }

    }
    private static Element addSubordinates(Document document, NodeDirectory node){
        Element element = document.createElement(node.getName());
        Text nombArchivo;
        for(ANode n: node.getNodes()){
            if(n instanceof NodeDirectory){ //Si es un directorio; entonces agrega los de adentro
                element.appendChild(addSubordinates(document, (NodeDirectory) n)) ; //Lo convierte en recursivo
            }else{ //Si no entonces coloca el nombre del archivo solamente
                Element archivo = document.createElement("Archivo");
                nombArchivo = document.createTextNode(n.getName());
                archivo.appendChild(nombArchivo);
                element.appendChild(archivo);
            }
        }
        return element;

    }
    public static RootUser read(String username) throws ParserConfigurationException, IOException, SAXException {
        String fileName = username+".xml";
        RootUser root = new RootUser(username);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File xml  = new File(fileName);
        Document documento = builder.parse(xml);
        NodeList partidas = documento.getElementsByTagName(username);



        return root;
    }


}
