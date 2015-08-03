package ser.jint.factory;

import ser.jint.bo.Books;
import ser.jint.bo.Electronic;
import ser.jint.bo.Music;

/**
 * Created by Razor15 on 15/07/2015.
 */
public class BooksFactory extends ItemFactory {
	
	@Override
	public Books getBooks() {
		return new Books();
	}
	
	@Override
	public Music getMusic() {
		return null;
	}
	
	@Override
	public Electronic getElectronic() {
		return null;
	}
}
