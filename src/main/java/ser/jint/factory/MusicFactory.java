package ser.jint.factory;

import ser.jint.bo.Books;
import ser.jint.bo.Electronic;
import ser.jint.bo.Music;

/**
 * Created by Razor15 on 15/07/2015.
 */
public class MusicFactory extends ItemFactory {
	@Override
	public Books getBooks() {
		return null;
	}
	
	@Override
	public Music getMusic() {
		return new Music();
	}
	
	@Override
	public Electronic getElectronic() {
		return null;
	}
}
