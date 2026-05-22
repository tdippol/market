package com.axiante.mui.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CatalogReducerImpl implements CatalogReducer {
    private static final long serialVersionUID = 1907642360939636422L;
    @Inject
    private ConfigurationFilterCatalog configurationFilterCatalog;

    @Override
    public ConfigurationFilterCatalog getCatalog() {
        return configurationFilterCatalog;
    }

    @Override
    public synchronized List<ConfigurationElement> filterCatalog(final List<CatalogFilter> filters) {
        final List<ConfigurationElement> ret = Collections.synchronizedList(new ArrayList<ConfigurationElement>());
        if ((filters != null) && (filters.size() > 0)) {
            final Map<String, List<CatalogFilter>> filterMap = filters.stream()
                    .collect(Collectors.groupingBy(CatalogFilter::getName));

            getCatalog().getCatalog().stream().filter(element -> {
                return filterMap.containsKey(element.getCode());
            }).forEach(element -> {
                //				final ConfigurationElement ele = element.clone();
                element.getAttributes().removeIf(att -> {
                    final List<CatalogFilter> list = filterMap.get(element.getCode());
                    if (list != null) {
                        final CatalogFilter c = list.get(0);
                        if ((c != null) && (c.getAttributes() != null)) {
                            return !c.getAttributes().contains(att.getCode());
                        }
                    }
                    return false;
                });
                //				ele.setServer(element.getServer());
                ret.add(element);
            });
        }
        return ret;
    }

}
