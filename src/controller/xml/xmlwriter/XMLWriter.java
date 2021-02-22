package controller.xml.xmlwriter;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML writer class. Used to output the current state of the simulation
 *
 * @author Tinglong Zhu
 */
public class XMLWriter {
  private static final String SIMULATION_ATTRIBUTE ="simulation";

  /**
   * output the current simulation states to the XML file
   *
   * @param states          states of the current grid
   * @param params          all parameters of the simulation including title author etc.
   * @param simulationType  type of the simulation
   */
  public  void XML2File(List<List<Integer> > states, Map<String, Object> params, String simulationType, String filename){
    try {
      DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
      DocumentBuilder builder;
      builder = factory.newDocumentBuilder();
      Document document = builder.newDocument();

      Element root = document.createElement("data");
      root.setAttribute(SIMULATION_ATTRIBUTE, simulationType);
      statesWriter(states, root, document);
      gridWriter(states,root,document);
      paramWriter(params,root,document);
      document.appendChild(root);
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty("encoding", "UTF-8");

      StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(document), new StreamResult(writer));
      //          System.out.println(writer.toString());

      transformer.transform(new DOMSource(document), new StreamResult(new File("data/"+filename)));

    } catch (ParserConfigurationException | TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
    return;
  }

  private void statesWriter(List<List<Integer> > states, Element root,Document document){
    for(int i=0;i<states.size();i++){
      for(int j=0;j<states.get(0).size();j++){
        root.appendChild(cellWriter(i,j,states.get(i).get(j ),document));
      }
    }
  }

  private Element cellWriter(int row, int col, int state, Document document){
    Element cell =  document.createElement("cell");
    Element rowTag = document.createElement("row");
    rowTag.setTextContent(""+row);
    Element colTag = document.createElement("col");
    colTag.setTextContent(""+col);
    Element stateTag = document.createElement("state");
    stateTag.setTextContent(""+state);
    cell.appendChild(rowTag);
    cell.appendChild(colTag);
    cell.appendChild(stateTag);
    return cell;
  }

  private void gridWriter(List<List<Integer> > states, Element root,Document document){
    Element grid = document.createElement("grid");
    Element sizex= document.createElement("sizex");
    Element sizey= document.createElement("sizey");
    sizex.setTextContent(""+states.size());
    sizey.setTextContent(""+states.get(0).size());
    grid.appendChild(sizex);
    grid.appendChild(sizey);
    root.appendChild(grid);
  }

  private void paramWriter(Map<String,Object> params, Element root,Document document){
    for(String key: params.keySet()){
      Element tmpParam= document.createElement(key);
      tmpParam.setTextContent(""+params.get(key));
      root.appendChild(tmpParam);
    }
    return;
  }

}
