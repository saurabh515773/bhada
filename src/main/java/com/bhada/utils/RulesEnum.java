package com.bhada.utils;

public enum RulesEnum {
	SMOKING_ALLOWED(1),
	DRINKING_ALLOWED(2),
	UNMARRIED_COUPLES(3),
	ONLY_FAMILY_ALLOWED(4),
	PETS_ALLOWED(5),
	BACHLELORS_ALLOWED(6),
	NO_SMOKING(7),
	NO_DRINING(8);
	
	private int id;

	private RulesEnum(int id) {
		this.id = id;
	}

	public static RulesEnum getRule(Integer id) {
		if(id == null) {
			return null;
		}
		for(RulesEnum rule : RulesEnum.values()) {
			if(id.equals(rule.getId())) {
				return rule;
			}
		}
		throw new IllegalArgumentException("No matching RulesEnum type for id "+id);
	}

	public int getId() {
		return id;
	}
}
