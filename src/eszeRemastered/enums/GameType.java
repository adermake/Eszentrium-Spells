package eszeRemastered.enums;

import eszeRemastered.types.Type;

public class GameType {
	
	private static Type type;
	
	public static Type getType() {
		return type;
	}
	
	public static void setType(Type type){
		type = GameType.type;
	}
	
	public static boolean setTypeByEnum(TypeEnum typeEnum){
		try{
			type = (Type) Class.forName("eszeRemastered.types.Type" + typeEnum.toString().toUpperCase()).newInstance();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean setTypeByName(String typeName){
		try{
			type = (Type) Class.forName("eszeRemastered.types.Type" + typeName.toUpperCase()).newInstance();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static enum TypeEnum {
		SOLO;
	}

}
