package application.model;

import javafx.beans.property.*;

public class KundeModel {
	private SimpleStringProperty vorname = new SimpleStringProperty();
	private SimpleStringProperty nachname = new SimpleStringProperty();
	private SimpleStringProperty strasse = new SimpleStringProperty();
	private SimpleIntegerProperty plz = new SimpleIntegerProperty();
	private SimpleStringProperty ort = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty kundeSeit = new SimpleStringProperty();
	private SimpleStringProperty art = new SimpleStringProperty();
	private SimpleBooleanProperty newsletter = new SimpleBooleanProperty();
	private SimpleObjectProperty<Art> artEnum = new SimpleObjectProperty<>();
	
	public enum Art {
		Standard, Gold, Premium;
	}
	
	public KundeModel() {
	}
	
	public KundeModel(String vorname, String nachname, String strasse, Integer plz, String ort, String email, 
			String kundeSeit, String art, Boolean newsletter) {
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.strasse = new SimpleStringProperty(strasse);
		this.plz = new SimpleIntegerProperty(plz);
		this.ort = new SimpleStringProperty(ort);
		this.email = new SimpleStringProperty(email);
		this.kundeSeit = new SimpleStringProperty(kundeSeit);
		this.art = new SimpleStringProperty(art);
		this.newsletter = new SimpleBooleanProperty(newsletter);
	}

	public SimpleStringProperty getVorname() {
		return vorname;
	}

	public SimpleStringProperty getNachname() {
		return nachname;
	}

	public SimpleStringProperty getStrasse() {
		return strasse;
	}

	public SimpleIntegerProperty getPlz() {
		return plz;
	}

	public SimpleStringProperty getOrt() {
		return ort;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public SimpleStringProperty getKundeSeit() {
		return kundeSeit;
	}

	public SimpleStringProperty getArt() {
		return art;
	}

	public SimpleBooleanProperty getNewsletter() {
		return newsletter;
	}

	public SimpleObjectProperty<Art> getArtEnum() {
		if (art.getValue().equals(Art.Gold.name())) {
			artEnum.set(Art.Gold);
		} else if (art.getValue().equals(Art.Premium.name())) {
			artEnum.set(Art.Premium);
		}  else {
			artEnum.set(Art.Standard);
		}
		return artEnum;
	}
}
