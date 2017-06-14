package com.funtik.mbp.util.xml;

import com.funtik.mbp.element.ConnectPoint;
import com.funtik.mbp.element.Element;
import com.funtik.mbp.model.ElementModel;
import com.funtik.mbp.model.Project;
import com.funtik.mbp.model.Properties;
import com.funtik.mbp.util.Converters;
import com.funtik.mbp.util.ref.ClassRef;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by funtik on 08.05.17.
 */
public class XmlWorker {

    public static boolean  loadProject(Project project) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document doc = saxBuilder.build(project.getFile());
        org.jdom2.Element root = doc.getRootElement();
        return loadElement(root, project);
    }

    private static boolean loadElement(org.jdom2.Element element, XmlData data){
        for(Attribute a:element.getAttributes())
            if(a != null)
                data.setValueAttributes(a.getName(), a.getValue());

        for(org.jdom2.Element el:element.getChildren()){
            Class clazz = data.getClass(el.getName());
            if(ClassRef.isInterface2(clazz, XmlData.class)) {
                if(!ClassRef.isInterface2(clazz, Properties.class)){  continue; /* #пишем_в_лог*/ } // ??????

                XmlData elData = (XmlData) ClassRef.createObject(clazz);
                loadElement(el, elData);
                data.setValueContent(el.getName(), elData);
            } else if(ClassRef.isInterface2(clazz, Element.class)){
                ElementModel model = ElementModel.getElementModel(clazz, null);
                if(model == null){  continue; /* #пишем_в_лог*/ }
            } else if(ClassRef.isInterface2(clazz, ConnectPoint.class)){
                //////////////////s///////
            } else if(ClassRef.isInterface2(clazz, Collection.class)){
                Object v = data.getValueContent(el.getName());
                System.out.println(v);
                for(org.jdom2.Element e:el.getChildren()){

                    loadOthers(Converters.getClass(e.getName()), e).getObject();
                }

            }
        }
        return true;
    }

    private static void addCollection(XmlData data, Class clazz){

    }

    private static XmlData loadOthers(Class clazz, org.jdom2.Element el){
        if(ClassRef.isInterface(clazz, XmlData.class)){
            XmlData data = (XmlData) ClassRef.createObject(clazz);
            loadElement(el, data);
            System.out.println(data);
            return data;
        } else {
            XmlData m = Converters.getXmlDataElement(clazz);
            loadElement(el, m);
            return m;
        }
    }

    public static boolean saveProject(Project project) throws IOException {
        org.jdom2.Element el = new org.jdom2.Element(Project.class.getSimpleName());
        if(!writeElement(el, project)) return false;
        Document doc = new Document(el);
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        FileWriter fw = new FileWriter(project.getFile());
        out.output(doc, fw);
        out.output(doc, System.out);
        return true;
    }

    public static boolean writeElement(org.jdom2.Element el, XmlData data){
        for(String attr:(List<String>)data.getAttributes()){
            Object val = data.getValueAttributes(attr);
            if(val != null) el.setAttribute(attr, val.toString());
        }

        for(String con:(List<String>)data.getContents()){
            Object obj = data.getValueContent(con);
            if(obj == null) continue;
            Class clazz = obj.getClass();
            if(!ClassRef.isInterface(clazz, XmlData.class)) continue;
            org.jdom2.Element dataEl = new org.jdom2.Element(con);
            writeElement(dataEl, (XmlData) obj);
        }
        return true;
    }

}
