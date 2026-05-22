package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DimensionWatch implements Comparable<DimensionWatch>, Serializable{
	private static final long serialVersionUID = -5623852263761772105L;
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	protected String dimension;
	@Getter
	protected Watch watch;

	public static final String LastSchemaUpdate = "LastSchemaUpdate";
	public static final String LastDataUpdate = "LastDataUpdate";
	public DimensionWatch(final String dimension, final String lastSchemaUpdate, final String lastDataUpdate) {
		setDimension(dimension);
		watch = new Watch(lastSchemaUpdate, lastDataUpdate);
	}

	@Override
	public int compareTo(final @NonNull DimensionWatch dimensionWatch) {
		final int result = getDimension().compareTo(dimensionWatch.getDimension());
		if ( result == 0 ) {
			return watch.compareTo(dimensionWatch.getWatch());
		}
		return result;
	}


	@Override
	public boolean equals(final Object o) {
		if (o == this) {
			log.debug("testing");
			return true;
		}
		if (!(o instanceof DimensionWatch)) {
			return false;
		}
		final DimensionWatch dimensionWatch = (DimensionWatch) o;
		return Objects.equals(dimension, dimensionWatch.getDimension()) &&
				Objects.equals(watch, dimensionWatch.getWatch());

	}
	public class Watch implements Comparable<Watch>, Serializable{
		private static final long serialVersionUID = 8999384104626765447L;
		@Getter
		String lastSchemaUpdate;
		@Getter
		String lastDataUpdate;
		protected Watch(final String lastSchemaUpdate, final String lastDataUpdate) {
			this.lastDataUpdate = lastDataUpdate;
			this.lastSchemaUpdate = lastSchemaUpdate;
		}
		@Override
		public int compareTo(final Watch watch) {
			final int result = (getLastDataUpdate().compareTo(watch.getLastDataUpdate()));
			if ( result == 0 ) {
				return getLastSchemaUpdate().compareTo(watch.getLastSchemaUpdate());
			}
			return result;
		}

		@Override
		public boolean equals(final Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Watch)) {
				return false;
			}
			final Watch watch = (Watch) o;
			return Objects.equals(getLastSchemaUpdate(), watch.getLastSchemaUpdate()) &&
					Objects.equals(getLastDataUpdate(), watch.getLastDataUpdate());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getLastSchemaUpdate(), getLastDataUpdate());
		}
	}


}
