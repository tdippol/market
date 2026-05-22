package com.axiante.tm1.json.objects;

import com.axiante.mui.common.utility.CellNameMapper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ForceSuppressColumnsHelper implements Serializable{
	private static final long serialVersionUID = -5400232558504941933L;
	private static final String SUPPRESSED_COLUMNS = ",\"suppressedCols\": [\"%s\"]";
	@Getter
	private final Map<String, Boolean> keepCols = new HashMap<String, Boolean>();
	@Setter
	@Getter
	private boolean applyForceSuppression = false;
	
	private static Predicate<Entry<String, Boolean>> keep = new Predicate<Entry<String, Boolean>>() {
		@Override
		public boolean test(Entry<String, Boolean> entry) {
			return entry.getValue();
		}

	};

	public void put(String key, Boolean value) {
		keepCols.put(key, value);
	}
	
	public String produceSuppressJsonArray() {
		return String.format(SUPPRESSED_COLUMNS, keepCols.entrySet().parallelStream().filter(keep.negate())
				.map(Entry::getKey)
				.filter(Objects::nonNull)
				.map(CellNameMapper::map2agGrid)
				.collect(Collectors.joining("\",\"")));
	}

	protected Cell updateEntry(@NonNull final Cell cell) {
		updateEntry(cell.getName(), !cell.getValue().isEmpty());
		return cell;
	}

	protected void updateEntry(String key, Boolean value) {
		Boolean check = getKeepCols().get(key);
		/**
		 * se non c'é il valore
		 * oppure
		 * il valore é false e il nuovo valore é true 
		 * allora inserisci.
		 */

		if (isApplyForceSuppression() && ( check == null || (!check && value) ) ) put(key, value);
	}
	
	public String updateRow(@NonNull final Row<Cell> row){
		String ret = null;
		if ( isApplyForceSuppression() ) {
			ret =  row.stream().map(cell->{
						return updateEntry(cell).getAgGridCell();
					}).collect(Collectors.joining(","));
		} else { 
			ret = row.stream().map(Cell::getAgGridCell).collect(Collectors.joining(","));
		}
		return ret;
	}
}
