package com.filrouge.projet_filrouge.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class XmlSerializer {

    private JAXBContext jc;

    public XmlSerializer() throws JAXBException {
        jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
    }

    public Bibliotheque deserialize(File file) {
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            Bibliotheque bibliotheque = (Bibliotheque) unmarshaller.unmarshal(file);

            return bibliotheque;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String serialize(Bibliotheque bibliotheque) {
        try {
            Marshaller marshaller = jc.createMarshaller();

            StringWriter stringWriter = new StringWriter();

            marshaller.marshal(bibliotheque, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
