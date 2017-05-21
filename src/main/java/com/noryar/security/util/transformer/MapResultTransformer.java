package com.noryar.security.util.transformer;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import com.noryar.security.util.CamelCaseUtils;

/**
 * 功能描述：自定义hibernate查询结果转换器，将结果转换为集合，集合中的元素为map.
 * @author Leon.
 */
public final class MapResultTransformer extends AliasedTupleSubsetResultTransformer {

    /**
     * .
     */
    private static final long serialVersionUID = 1L;
    /**
     * .
     */
    public static final MapResultTransformer INSTANCE = new MapResultTransformer();

    /**
     * Disallow instantiation of AliasToEntityMapResultTransformer.
     */
    private MapResultTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public Object transformTuple(final Object[] tuple, final String[] aliases) {
        Map<String, Object> result = new HashMap<String, Object>(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                alias = CamelCaseUtils.toCamelCase(alias);
                result.put(alias, tuple[i]);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTransformedValueATupleElement(final String[] aliases, final int tupleLength) {
        return false;
    }

    /**
     * Serialization hook for ensuring singleton uniqueing.
     * @return The singleton instance : {@link #INSTANCE}
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
