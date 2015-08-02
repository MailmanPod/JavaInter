package ser.jint.bo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import ser.jint.persistence.CsvPersistence;

/**
 * Created by Razor15 on 08/07/2015.
 */
public class Music extends Items implements Serializable {
	
	private String	label;
	private String	artist;
	private int		songs;
	
	public Music(int itemId, String itemDescription, double price, double tax,
			int stock, String label, String artist, int songs) {
		super(itemId, itemDescription, price, tax, stock);
		this.label = label;
		this.artist = artist;
		this.songs = songs;
	}
	
	public Music(int itemId, String itemDescription, double price, double tax,
			String label, String artist, int songs) {
		super(itemId, itemDescription, price, tax);
		this.label = label;
		this.songs = songs;
		this.artist = artist;
	}
	
	public Music(String label, String artist, int songs) {
		this.label = label;
		this.songs = songs;
		this.artist = artist;
	}
	
	public Music() {
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getSongs() {
		return songs;
	}
	
	public void setSongs(int songs) {
		this.songs = songs;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	@Override
	public double getPriceWithTax() {
		if (this.getTax() == 0) {
			return this.getPrice() + (this.getPrice() * 0.5);
		} else {
			return this.getPrice() + ((this.getPrice() * this.getTax()) * 0.5);
		}
	}
	
	@Override
	public String getItemType() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String persistenceString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.persistenceString());

		builder.append(this.getLabel());
		builder.append(CsvPersistence.SEPARATOR);

		builder.append(this.getArtist());
		builder.append(CsvPersistence.SEPARATOR);

		builder.append(this.getSongs());
		
		return builder.toString();
	}

	public void rebuildObject(Stack<String> tokens)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {

		super.rebuildObject(tokens);

		this.setLabel(tokens.pop());
		this.setArtist(tokens.pop());
		this.setSongs(new Integer(tokens.pop()));
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString());
		builder.append("Artist: " + this.getArtist() + "\n");
		builder.append("Label: " + this.getLabel() + "\n");
		builder.append("Songs: " + this.getSongs() + "\n");
		
		return builder.toString();
	}
}
