package by.sardyka.parsing.parser;

import java.util.HashSet;
import java.util.Set;

import by.sardyka.parsing.entity.TariffType;

public abstract class AbstractTariffBuilder {
	
	protected Set<TariffType> tariffs;

	public AbstractTariffBuilder() {
		tariffs = new HashSet<TariffType>();
	}

	public AbstractTariffBuilder(Set<TariffType> tariffs) {
		this.tariffs = tariffs;
	}

	public Set<TariffType> getTariffs() {
		return tariffs;
	}

	abstract public void buildSetTariffs(String fileName);
}
