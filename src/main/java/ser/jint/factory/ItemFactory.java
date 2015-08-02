package ser.jint.factory;

import ser.jint.bo.Books;
import ser.jint.bo.Electronic;
import ser.jint.bo.Music;

/**
 * Created by Razor15 on 15/07/2015.
 */
public abstract class ItemFactory {
	
	public static final int	BOOKS		= 1;
	public static final int	CLOTHES		= 2;
	public static final int	ELECTRONIC	= 3;
	
	public static ItemFactory getItemFactory(int factoryType) {
		switch (factoryType) {
			case BOOKS:
				return new BooksFactory();
				
			case CLOTHES:
				return new ClothesFactory();
				
			case ELECTRONIC:
				return new ElectronicFactory();
				
			default:
				return null;
		}
	}
	
	public abstract Books getBooks();
	
	public abstract Music getClothes();
	
	public abstract Electronic getElectronic();
}
