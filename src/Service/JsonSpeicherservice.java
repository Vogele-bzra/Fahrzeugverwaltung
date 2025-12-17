package Service;

import model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSpeicherservice {

    private final ObjectMapper mapper;
    private final File FAHRZEUG_FILE = new File("fahrzeuge.json");
    private final File KUNDEN_FILE = new File("kunden.json");

    public JsonSpeicherservice() {
        this.mapper = new ObjectMapper();

        this.mapper.registerModule(new JavaTimeModule());

        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void speichereFahrzeuge(List<Fahrzeug> alleFahrzeuge) {
        List<Auto> autos = new ArrayList<>();
        List<Transporter> transporter = new ArrayList<>();

        for (Fahrzeug f : alleFahrzeuge) {
            if (f instanceof Auto) autos.add((Auto) f);
            else if (f instanceof Transporter) transporter.add((Transporter) f);
        }

        DatenContainer container = new DatenContainer(autos, transporter);

        try {
            mapper.writeValue(FAHRZEUG_FILE, container);
            System.out.println("Fahrzeuge (Jackson) gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Fahrzeug> ladeFahrzeuge() {
        List<Fahrzeug> ergebnis = new ArrayList<>();

        if (FAHRZEUG_FILE.exists()) {
            try {
                DatenContainer container = mapper.readValue(FAHRZEUG_FILE, DatenContainer.class);
                if (container != null) {
                    if (container.getAutos() != null) ergebnis.addAll(container.getAutos());
                    if (container.getTransporter() != null) ergebnis.addAll(container.getTransporter());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ergebnis;
    }


    public void speichereKunden(List<Kunde> kunden) {
        try {
            mapper.writeValue(KUNDEN_FILE, kunden);
            System.out.println("Kunden (Jackson) gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Kunde> ladeKunden() {
        if (!KUNDEN_FILE.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(KUNDEN_FILE, mapper.getTypeFactory().constructCollectionType(List.class, Kunde.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}