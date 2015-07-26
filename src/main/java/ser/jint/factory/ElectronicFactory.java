package ser.jint.factory;

import ser.jint.bo.Books;
import ser.jint.bo.Clothes;
import ser.jint.bo.Electronic;

/**
 * Created by Razor15 on 15/07/2015.
 */
public class ElectronicFactory extends ItemFactory {
	@Override
	public Books getBooks() {
		return null;
	}
	
	@Override
	public Clothes getClothes() {
		return null;
	}
	
	@Override
	public Electronic getElectronic() {
		return new Electronic();
	}
}
