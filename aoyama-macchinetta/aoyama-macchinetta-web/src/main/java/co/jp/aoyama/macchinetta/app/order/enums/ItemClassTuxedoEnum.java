package co.jp.aoyama.macchinetta.app.order.enums;

public enum ItemClassTuxedoEnum {
	
	// key: item_code
	// value: class
	
	ITEM_CODE_SUIT("01",""),
	ITEM_CODE_JACKET("02","co.jp.aoyama.macchinetta.app.order.info.OptionJacketTuxedoInfo"),
	ITEM_CODE_PANTS("03","co.jp.aoyama.macchinetta.app.order.info.OptionPantsTuxedoInfo"),
	ITEM_CODE_GILET("04","co.jp.aoyama.macchinetta.app.order.info.OptionGiletTuxedoInfo"),
	ITEM_CODE_SHIRTS("05",""),
	ITEM_CODE_COAT("07","");
	
	private final String key;
	private final String value;
    
	private ItemClassTuxedoEnum(String k ,String v ) {
        this.value = v;
        this.key = k;
    }  

	public String toString() {
        return this.key.concat(",").concat(this.value);
    }  
	
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static String getValue(String key) {
		for (ItemClassTuxedoEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        return "";
	}
  
    public static ItemClassTuxedoEnum get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }
  
    public static ItemClassTuxedoEnum get(String str) {
        for (ItemClassTuxedoEnum e : values()) {
            if(e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }
}
